package kr.ac.kopo.dao.link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.dao.HistoryDAO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BankAccountVO;
import kr.ac.kopo.vo.HistoryVO;

public class CY_TransactionHistoryDAO implements HistoryDAO {
	
	@Override
	public List<HistoryVO> getAccountHistory(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM TRANSACTION @CY_BANK_LINK ");
		sql.append("WHERE ACCOUNTNUM1 = ? ");
		sql.append("ORDER BY T_DATE DESC ");
		
		List<HistoryVO> hList = new ArrayList<>();
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, acNum);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				HistoryVO vo = new HistoryVO();
				
				//ACCOUNTNUM1	1
				//ACCOUNTNUM2	2
				//T_TYPE		3
				//T_AMOUNT		4
				//T_DATE		5
				//T_CONTENT		6
				//NUM1_NAME		8
				//NUM2_NAME		9
				
				//vo.setTrNum(rs.getInt()); 거래번호(아이디)
				vo.setAcNumber(rs.getString(1));
				vo.setTrAcNumber(rs.getString(2));
				vo.setTrName(rs.getString(9));
				
				long amount = rs.getLong(4);
				String tType = rs.getString(3) != null ? rs.getString(3) : "+";
				amount = tType.equals("-") ? amount * -1 : amount;
				vo.setAmount(amount);
				
				
				vo.setTrTime(rs.getString(6));
				
				hList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hList;
	}
	@Override
	public void insertRecord(BankAccountVO vo, Connection conn) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TRANSACTION @CY_BANK_LINK (ACCOUNTNUM1, ACCOUNTNUM2,  ");
		sql.append("T_TYPE, T_AMOUNT, T_BALANCE, T_CONTENT, NUM2_NAME) ");
		sql.append("VALUES(?,?,?,?,?,?,?) ");

		
		System.out.println(vo.getBalance());
		System.out.println(vo.getAcNumber());
		try (
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, vo.getAcNumber());
			pstmt.setString(2, vo.getTransferAcNumber());
			
			// T_TYPE
			long balance = vo.getBalance();
			String tType = balance < 0 ? "-" : "+";
			pstmt.setString(3, tType);
			
			// t_amount
			long balance2 = balance < 0 ? balance * -1 : balance;
			pstmt.setLong(4, balance2); // 정수. 자연수로 넣어야 함
			
			// t_balance
			long savBal = new CYbankLinkDAO().getSavingBalanceWithAcNumber(vo.getAcNumber()) + balance;
			pstmt.setLong(5, savBal);
			
			// t_date : 자동완성
			
			// t_content
			String content = vo.getBalance() < 0 ? "출금" : "입금";
			pstmt.setString(6, content);
			
			pstmt.setString(7, vo.getAcName());
			
			
			
			
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
