package kr.ac.kopo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.dao.AccountDAO;
import kr.ac.kopo.dao.HistoryDAO;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.dao.TransactionHistoryDAO;
import kr.ac.kopo.dao.link.CY_TransactionHistoryDAO;
import kr.ac.kopo.dao.link.CYbankLinkDAO;
import kr.ac.kopo.dao.link.JH_TransactionHistoryDAO;
import kr.ac.kopo.dao.link.JHbankDAO;
import kr.ac.kopo.dao.link.JOY_TransactionHistoryDAO;
import kr.ac.kopo.dao.link.JOYbankDAO;
import kr.ac.kopo.vo.BankAccountVO;

public class AccountTransaction {
	
	Map<String, AccountDAO> accountMap = new HashMap<String, AccountDAO>();
	Map<String, HistoryDAO> acHistoryMap = new HashMap<String, HistoryDAO>();
	
	public AccountTransaction() {
		accountMap.put("37", new SavingAccountDAO()); //my
		accountMap.put("41", new CYbankLinkDAO()); // song
		accountMap.put("14", new JHbankDAO()); //lim
		accountMap.put("10", new JOYbankDAO()); // kim
		
		acHistoryMap.put("37", new TransactionHistoryDAO()); //my
		acHistoryMap.put("41", new CY_TransactionHistoryDAO()); // song
		acHistoryMap.put("14", new JH_TransactionHistoryDAO()); //lim
		acHistoryMap.put("10", new JOY_TransactionHistoryDAO()); // kim
	}
	
	public AccountDAO getDAOWithAccountNum(String AccountNumber) {
		String bankCode = AccountNumber.substring(0, 2);
		return accountMap.getOrDefault(bankCode, new SavingAccountDAO());
	}
	
	public HistoryDAO getHistoryDAOWithAccountNum(String AccountNumber) {
		String bankCode = AccountNumber.substring(0, 2);
		return acHistoryMap.getOrDefault(bankCode, new TransactionHistoryDAO());
	}
	
	public boolean vildeCheck(String myAcNum, String trAcNum, long amount) {
		
		AccountDAO myDao = null;
		AccountDAO trDao = null;
		boolean acNumInput = (myAcNum.length() > 2) && (trAcNum.length() > 2);
		if(acNumInput) {
			myDao = getDAOWithAccountNum(myAcNum);
			trDao = getDAOWithAccountNum(trAcNum);
		} else {
			return false;
		}

		boolean flag = true;
		
		boolean isAcExist = !trDao.accountNumberCheck(trAcNum); // 있으면 false로 나옴 (로그인 전용으로 설계해서)
		boolean isAmount = 1 < amount;
		boolean notOverBalance = amount <= myDao.getSavingBalanceWithAcNumber(myAcNum);

		if(isAcExist && isAmount && notOverBalance) {
			flag = true;
		} else {
			flag = false;
		}
		
		
		return flag;
	}
	
	public List<BankAccountVO> getVoListForTransaction(String myAcNum, String trAcNum, long amount) {
		List<BankAccountVO> voList = new ArrayList<>();
		AccountDAO myDao = getDAOWithAccountNum(myAcNum);
		AccountDAO trDao = getDAOWithAccountNum(trAcNum);
		
		// 출금기록
		BankAccountVO vo1 = new BankAccountVO();
		
		vo1.setAcNumber(myAcNum);
		vo1.setTransferAcNumber(trAcNum);
		String trAcName = trDao.getAccountNameWithAcNum(trAcNum);
		vo1.setAcName(trAcName);
		vo1.setBalance(-amount);
		voList.add(vo1);
		
		// 입금기록
		BankAccountVO vo2 = new BankAccountVO();
		
		vo2.setAcNumber(trAcNum);
		vo2.setTransferAcNumber(myAcNum);
		String myAcName = myDao.getAccountNameWithAcNum(myAcNum);
		vo2.setAcName(myAcName);
		vo2.setBalance(amount);
		voList.add(vo2);
		
		// 송금
		BankAccountVO vo3 = new BankAccountVO();
		
		vo3.setBalance(amount);
		vo3.setAcNumber(myAcNum);
		voList.add(vo3);
		
		// 입금
		BankAccountVO vo4 = new BankAccountVO();
		
		vo4.setBalance(amount);
		vo4.setAcNumber(trAcNum);
		voList.add(vo4);
		
		return voList;
	}
	
	/**
	 * 보내는 계좌의 거래기록, 받는 계좌의 거래기록, 출금, 송금
	 * @param hvo
	 * @param trHvo
	 * @param vo
	 * @param trVo
	 */
	public void startTransaction(String myAcNum, String trAcNum, long amount) {
		List<BankAccountVO> voList = getVoListForTransaction(myAcNum, trAcNum, amount);
		BankAccountVO hvo = voList.get(0);
		BankAccountVO trHvo = voList.get(1);
		BankAccountVO vo = voList.get(2);
		BankAccountVO trVo = voList.get(3);
		
		Connection connection = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";

		try {
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false); // 오토커밋 비활성화
			
			/////////////////// < 시작 >
			//TransactionHistoryDAO hdao = new TransactionHistoryDAO();
			HistoryDAO sendHisDao = getHistoryDAOWithAccountNum(myAcNum);
			HistoryDAO receiveHisDao = getHistoryDAOWithAccountNum(trAcNum);
			AccountDAO dao = getDAOWithAccountNum(myAcNum);
			//SavingAccountDAO dao = new SavingAccountDAO();

			// 기록
			sendHisDao.insertRecord(hvo, connection);
			receiveHisDao.insertRecord(trHvo, connection);
			
			dao.withdrawAmount(vo, connection);
			
			// 입금계좌 입금
			AccountDAO trDao = getDAOWithAccountNum(trVo.getAcNumber());
			System.out.println("보내는 계좌번호"+trVo.getAcNumber());
			trDao.addAmount(trVo, connection);
			
			/////////////////// < 종료 >

			connection.commit();

		} catch (SQLException e) {
			// 예외 발생 시 롤백 수행
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			// 연결 종료
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
