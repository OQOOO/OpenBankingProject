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
		
		// ��ȿ�� �˻�
		////////////////////////////////////////////////////////////////////////////////////
		String nextPage = "/refresh.do";
		request.setAttribute("toPage", "/pages/IndexMain.do");
		
		long myBalance = dao.balanceCheck(myAcNum);
		System.out.println("AccountTransferController:"+ Long.toString(myBalance));
		
		// ��ȿ�� �˻� ���
		boolean isClear = transaction.vildeCheck(myAcNum, trAcNum, amount);
		System.out.println(isClear);
		
		// 
		if(!isClear) {
			AccountDAO trDao = transaction.getDAOWithAccountNum("37000");
			if (trAcNum.length() > 2) {
				trDao = transaction.getDAOWithAccountNum(trAcNum);				
			}
			if (trDao.accountNumberCheck(trAcNum)) {
				request.setAttribute("acNumErrer", "�ش� ���°� �������� �ʽ��ϴ�.");
			}
			
			if (amount < 1) {
				request.setAttribute("amountErrer", "�ݾ��� �Է����ּ���");
			}
			
			System.out.println("������ ��:"+myBalance);
			if (myBalance < amount) {
				request.setAttribute("amountErrer", "�ش� �ݾ��� ���¿� �������� �ʽ��ϴ�");
			} 	
		}
		// 	�ŷ� ����(Ʈ����� ����)
		////////////////////////////////////////////////////////////////////////////////////
		
		// Ʈ������ ����
		if(isClear) {
			transaction.startTransaction(myAcNum, trAcNum, amount);
			request.getSession().setAttribute("isDataUpdate", "transfer");
		} else {
			nextPage = "/pages/toAccountTransfer.do?acNumber="+myAcNum;
		}
			
		
		return nextPage;
	}

}
