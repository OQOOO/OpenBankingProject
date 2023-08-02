package kr.ac.kopo.controller.bankAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.AccountDAO;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.service.AccountTransaction;

public class AccountTransferController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		AccountTransaction transaction = new AccountTransaction();
		SavingAccountDAO dao = new SavingAccountDAO();
		
		String myAcNum = request.getParameter("giveAcNumber");
		String trAcNum = request.getParameter("takeAcNumber");
		
		String iptAmount = request.getParameter("amount");
		if(iptAmount.equals("")) {
			iptAmount = "0";
		}
		long amount = Long.parseLong(iptAmount);
		
		// 유효성 검사
		////////////////////////////////////////////////////////////////////////////////////
		String nextPage = "/refresh.do";
		request.setAttribute("toPage", "/pages/IndexMain.do");
		
		long myBalance = dao.balanceCheck(myAcNum);
		System.out.println("AccountTransferController:"+ Long.toString(myBalance));
		
		// 유효성 검사 결과
		boolean isClear = transaction.vildeCheck(myAcNum, trAcNum, amount);
		System.out.println(isClear);
		
		// 
		if(!isClear) {
			AccountDAO trDao = transaction.getDAOWithAccountNum("37000");
			if (trAcNum.length() > 2) {
				trDao = transaction.getDAOWithAccountNum(trAcNum);				
			}
			if (trDao.accountNumberCheck(trAcNum)) {
				request.setAttribute("acNumErrer", "해당 계좌가 존재하지 않습니다.");
			}
			
			if (amount < 1) {
				request.setAttribute("amountErrer", "금액을 입력해주세요");
			}
			
			System.out.println("계좌의 돈:"+myBalance);
			if (myBalance < amount) {
				request.setAttribute("amountErrer", "해당 금액이 계좌에 존재하지 않습니다");
			} 	
		}
		// 	거래 시작(트랜잭션 시작)
		////////////////////////////////////////////////////////////////////////////////////
		
		// 트랜젝션 시작
		if(isClear) {
			transaction.startTransaction(myAcNum, trAcNum, amount);
			request.getSession().setAttribute("isDataUpdate", "transfer");
		} else {
			nextPage = "/pages/toAccountTransfer.do?acNumber="+myAcNum;
		}
			
		
		return nextPage;
	}

}
