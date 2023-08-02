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
		
		// ���� ���翩�δ� ���ִ�.
		
		
		// �ŷ����ɿ��� Ȯ��
		long trBalance = dao.balanceCheck(trAcNum);
		boolean isClear = true;
		
		if (dao.accountNumberCheck(trAcNum)) {
			request.setAttribute("acNumErrer", "�ش� ���°� �������� �ʽ��ϴ�.");
			isClear = false;
		}
		
		if (amount < 1) {
			request.setAttribute("amountErrer", "�ݾ��� �Է����ּ���");
			isClear = false;
			
		}
		
		System.out.println("������ ��:"+trBalance);
		if (trBalance < amount) {
			request.setAttribute("amountErrer", "�ش� �ݾ��� ���¿� �������� �ʽ��ϴ�");
			isClear = false;
		} 
		
		String nextPage = "/refresh.do";
		request.setAttribute("toPage", "/pages/IndexMain.do");
		
		// �ŷ�����
		if(isClear) {
			// �ŷ����
			BankAccountVO hvo = new BankAccountVO();
			TransactionHistoryDAO hDAO = new TransactionHistoryDAO();
			
			//��ݱ��
			hvo.setAcNumber(trAcNum);
			hvo.setTransferAcNumber(myAcNum);
			hvo.setAcName(dao.getAccountNameWithAcNum(myAcNum));
			hvo.setBalance(-amount);
			hDAO.insertRecord(hvo);
			
			//�Աݱ��
			hvo.setAcNumber(myAcNum);
			hvo.setTransferAcNumber(trAcNum);
			hvo.setAcName(dao.getAccountNameWithAcNum(trAcNum));
			hvo.setBalance(amount);
			hDAO.insertRecord(hvo);
			
			// ���
			vo.setBalance(amount);
			vo.setAcNumber(trAcNum);
			dao.withdrawAmount(vo);
			
			// �ӱ�
			vo.setAcNumber(myAcNum);
			dao.addAmount(vo);
			
		} else {
			nextPage = "/bankAccount/pages/toDeposit.do?acNumber="+myAcNum;
		}
			
		
		return nextPage;
	}

}
