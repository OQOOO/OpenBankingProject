package kr.ac.kopo.controller.bankAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.DepositAccountDAO;
import kr.ac.kopo.service.MakeAccountNumber;
import kr.ac.kopo.service.TimeUtil;
import kr.ac.kopo.vo.BankAccountVO;

public class CreateDepositAccountController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		DepositAccountDAO dao = new DepositAccountDAO();
		BankAccountVO vo = new BankAccountVO();
		
		// < �Է� > //
		String acName = request.getParameter("accountName");
		String acPw = request.getParameter("accountPw");
		String acPwCheck = request.getParameter("accountPwCheck");
		// �Աݰ���, ���� ������. ���߿� �߰��ؾ� ��
		String diposit = request.getParameter("diposit");
		String regularTransfer = request.getParameter("regularTransfer");
		String uid = (String)request.getSession().getAttribute("uid");
		
		// < ó�� > //
		int success = 0;
		
		// ���¸�
		boolean isAcNameInput = !acName.equals("") ? true : false;
		if (isAcNameInput) {
			vo.setAcName(acName);
			vo.setUserId(uid);
		}
		
		boolean isIdNotDuplicate = dao.accountNameCheck(vo);
		
		if (isAcNameInput && isIdNotDuplicate) {
			request.setAttribute("acName", acName);
			success++;
		} else {
			if (isAcNameInput == false) {
				request.setAttribute("acNameErrer", "�����̸��� �Է����ּ���");
			} else if (isIdNotDuplicate == false) {
				request.setAttribute("acNameErrer", "�ߺ��� ���¸��Դϴ�.");
			}
		}
		
		// ���º�й�ȣ
		boolean isPasswordMatch = (acPw.equals(acPwCheck)) ? true : false;
		if (isPasswordMatch && acPw.length() == 4) {
			request.setAttribute("acPw", acPw);
			success++;
		} else {
			if (acPw.length() != 4) {
				request.setAttribute("acPwErrer", "4�ڸ� ��й�ȣ�� �Է����ּ���");
			} else {
				request.setAttribute("acPwwErrer", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
			
		}
		
		// �����Աݾ�
		long dipositNum = 0;
		if (!diposit.equals("")) {
			dipositNum = Long.parseLong(diposit);
		}
		if (dipositNum >= 1000) {
			success++;
			request.setAttribute("diposit", diposit);
		} else {
			request.setAttribute("dipositErrer", "1000�� �̻��� ���� �Աݾ��� �ʿ��մϴ�.");
		}
		
		long regularTransferNum = 0;
		if (!diposit.equals("")) {
			regularTransferNum = Long.parseLong(regularTransfer);
		}
		if (regularTransferNum >= 1000) {
			success++;
			request.setAttribute("regularTransfer", regularTransfer);
		} else {
			request.setAttribute("regularTransferErrer", "1000�� �̻���� �����մϴ�.");
		}
		
		// �Աݰ��¹�ȣ
		
		// < �����ͺ��̽� �Է� >
		String nextPage = "/bankAccount/CreateDepositAccount.jsp";
		String BANK_CD = "A";
		
		if (success == 5 - 1) {
			vo.setBankCode(BANK_CD);
			vo.setAcNumber(new MakeAccountNumber().make());
			vo.setUserId(uid);
			vo.setAcPassword(acPw);
			vo.setAcName(acName);
			vo.setBalance(dipositNum);
			vo.setRate(5);
			vo.setTransferType("nomal");
			vo.setRegularTransfer(regularTransferNum);
			// �Աݰ��¹�ȣ
			vo.setAcEndDate(new TimeUtil().getNextYear(1));
			dao.insertAccount(vo);
			nextPage = "/refresh.do";
			request.setAttribute("toPage", "/pages/IndexMain.do");
		}
		
		return nextPage;
		
	}
}
