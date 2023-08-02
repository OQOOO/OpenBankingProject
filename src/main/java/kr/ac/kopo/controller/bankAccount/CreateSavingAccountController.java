package kr.ac.kopo.controller.bankAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.service.MakeAccountNumber;
import kr.ac.kopo.vo.BankAccountVO;

public class CreateSavingAccountController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		UserDAO udao = new UserDAO();
		SavingAccountDAO dao = new SavingAccountDAO();
		BankAccountVO vo = new BankAccountVO();
		
		// < �Է� > //
		String acName = request.getParameter("accountName");
		String acPw = request.getParameter("accountPw");
		String acPwCheck = request.getParameter("accountPwCheck");
		// �Աݰ���, ���� ������. ���߿� �߰��ؾ� ��
		String diposit = request.getParameter("diposit");
		String uid = (String)request.getSession().getAttribute("uid");
		String email = udao.getUserEmailWithId(uid);
		
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
		
		// �Ա�����
		// �Աݰ��¹�ȣ
		
		// < �����ͺ��̽� �Է� >
		String nextPage = "/bankAccount/CreateSavingAccount.jsp";
		String BANK_CD = "CL";
		if (success == 5 - 2) {
			vo.setBankCode(BANK_CD);
			vo.setAcNumber(new MakeAccountNumber().make());
			vo.setUserId(uid);
			vo.setAcPassword(acPw);
			vo.setAcName(acName);
			vo.setBalance(dipositNum);
			vo.setRate(2);
			vo.setUserMemo(email);
			dao.insertAccount(vo);
			nextPage = "/refresh.do";
			request.setAttribute("toPage", "/pages/IndexMain.do");
		}
		
		return nextPage;
	}
	
}
