package kr.ac.kopo.controller.bankAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.vo.BankAccountVO;

public class AccountDataUpdateController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		SavingAccountDAO dao = new SavingAccountDAO();
		BankAccountVO vo = new BankAccountVO();
		
		// < �Է� > //
		String acNumber = request.getParameter("acNumber");
		
		String acName = request.getParameter("accountName");
		String bfAcName = request.getParameter("bfAcName");
		
		String acPw = request.getParameter("accountPw");
		
		String acPwCheck = request.getParameter("accountPwCheck");
		String uid = (String)request.getSession().getAttribute("uid");
		
		// < ó�� > //
		int success = 0;
		vo.setAcNumber(acNumber);
		
		// ���¸�
			// ���¸� �Է� Ȯ��
		boolean isAcNameInput = !acName.equals("") ? true : false;
		if (isAcNameInput) {
			vo.setAcName(acName);
			vo.setUserId(uid);
		}
		
			// �������� ���¸� �ߺ� Ȯ��
		boolean isIdNotDuplicate = dao.accountNameCheck(vo);
		if (isAcNameInput && (isIdNotDuplicate || acName.equals(bfAcName) )) {
			vo.setAcName(acName);
			success++;
		} else {
			if (isAcNameInput == false) {
				request.setAttribute("acNameErrer", "�����̸��� �Է����ּ���");
			} else if ((isIdNotDuplicate || acName.equals(bfAcName) ) == false) {
				request.setAttribute("acNameErrer", "�ߺ��� ���¸��Դϴ�.");
			}
		}
		
		// ���º�й�ȣ
		boolean isPasswordMatch = (acPw.equals(acPwCheck)) ? true : false;
		if (isPasswordMatch && acPw.length() == 4) {
			vo.setAcPassword(acPw);
			success++;
		} else {
			if (acPw.length() != 4) {
				request.setAttribute("acPwErrer", "4�ڸ� ��й�ȣ�� �Է����ּ���");
			} else {
				request.setAttribute("acPwwErrer", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
			
		}
		
		
		// �Ա�����
		// �Աݰ��¹�ȣ
		
		// < �����ͺ��̽� �Է� >
		String nextPage = "/bankAccount/AccountDataUpdate.jsp";

		if (success == 2) {
			dao.accountDataUpdate(vo);
			request.setAttribute("isDataUpdate", "1");
			nextPage = "/bankAccount/pages/toAccountInfo.do?acNumber="+acNumber+"&page=1";
		}
		request.setAttribute("vo", vo);

		return nextPage;
	}
}
