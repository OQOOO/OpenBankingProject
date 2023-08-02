package kr.ac.kopo.controller.bankAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.dao.TransactionHistoryDAO;
import kr.ac.kopo.vo.BankAccountVO;

public class DipositController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		SavingAccountDAO dao = new SavingAccountDAO();
		BankAccountVO vo = new BankAccountVO();
		
		String myAcNum = request.getParameter("giveAcNumber");
		String trAcNum = request.getParameter("takeAcNumber");
		
		String iptAmount = request.getParameter("amount");
		if(iptAmount.equals("")) {
			iptAmount = "0";
		}
		
		long amount = Long.parseLong(iptAmount);
		System.out.println("myAc: " + myAcNum);
		System.out.println("trAc: " + trAcNum);
		System.out.println("amount: " + amount);
		
		// 계좌 존재여부는 좀있다.
		
		
		// 거래가능여부 확인
		long trBalance = dao.balanceCheck(trAcNum);
		boolean isClear = true;
		
		if (dao.accountNumberCheck(trAcNum)) {
			request.setAttribute("acNumErrer", "해당 계좌가 존재하지 않습니다.");
			isClear = false;
		}
		
		if (amount < 1) {
			request.setAttribute("amountErrer", "금액을 입력해주세요");
			isClear = false;
			
		}
		
		System.out.println("계좌의 돈:"+trBalance);
		if (trBalance < amount) {
			request.setAttribute("amountErrer", "해당 금액이 계좌에 존재하지 않습니다");
			isClear = false;
		} 
		
		String nextPage = "/refresh.do";
		request.setAttribute("toPage", "/pages/IndexMain.do");
		
		// 거래가능
		if(isClear) {
			// 거래기록
			BankAccountVO hvo = new BankAccountVO();
			TransactionHistoryDAO hDAO = new TransactionHistoryDAO();
			
			//출금기록
			hvo.setAcNumber(trAcNum);
			hvo.setTransferAcNumber(myAcNum);
			hvo.setAcName(dao.getAccountNameWithAcNum(myAcNum));
			hvo.setBalance(-amount);
			hDAO.insertRecord(hvo);
			
			//입금기록
			hvo.setAcNumber(myAcNum);
			hvo.setTransferAcNumber(trAcNum);
			hvo.setAcName(dao.getAccountNameWithAcNum(trAcNum));
			hvo.setBalance(amount);
			hDAO.insertRecord(hvo);
			
			// 출금
			vo.setBalance(amount);
			vo.setAcNumber(trAcNum);
			dao.withdrawAmount(vo);
			
			// 임금
			vo.setAcNumber(myAcNum);
			dao.addAmount(vo);
			
		} else {
			nextPage = "/bankAccount/pages/toDeposit.do?acNumber="+myAcNum;
		}
			
		
		return nextPage;
	}

}
