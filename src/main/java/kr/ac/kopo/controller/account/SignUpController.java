package kr.ac.kopo.controller.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.UserVO;

public class SignUpController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("singUpController");
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		
		// ============ �Է±��� ============ //
		// ���̵�
		String id = request.getParameter("id");
		
		// ��й�ȣ
		String pw = request.getParameter("pw");
		String pwCheck = request.getParameter("pwCheck");
		
		// �̸���
		String email = request.getParameter("email");
		
		// �̸�
		String name = request.getParameter("name");
		
		// �������
		String birthDate = request.getParameter("birthDate");
		
		// ��ȭ��ȣ
		String pFront = request.getParameter("phoneNum1");
		String pMiddle = request.getParameter("phoneNum2");
		String pBack = request.getParameter("phoneNum3");
		
		// �ּ�
		String address = request.getParameter("address");
		String detailAddr = request.getParameter("detailAddr");
		
		// ============ ó������ ============ //
		int success = 0;
		// ���̵�
		boolean isIdInput = !id.equals("") ? true : false;
		boolean isIdNotDuplicate = dao.idChack(id);
		
		if (isIdInput && isIdNotDuplicate) {
			request.setAttribute("id", id);
			success++;
		} else {
			if (isIdInput == false) {
				request.setAttribute("idErrer", "���̵� �Է����ּ���");
			} else if (isIdNotDuplicate == false) {
				request.setAttribute("idErrer", "�ߺ��� ���̵��Դϴ�.");
			}
		}
		
		// ��й�ȣ
		boolean isPasswordMatch = (pw.equals(pwCheck)) ? true : false;
		if (isPasswordMatch && pw.length() >= 4) {
			request.setAttribute("pw", pw);
			success++;
		} else {
			if (pw.length() < 4) {
				request.setAttribute("pwErrer", "��й�ȣ�� 4�ڸ� �̻� �Է����ּ���");
			} else {
				request.setAttribute("pwErrer", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
			
		}
		
		// �̸�
		boolean isNameInput = !name.equals("") ? true : false;
		if (isNameInput) {
			request.setAttribute("name", name);
			success++;
		} else {
			request.setAttribute("nameErrer", "�̸��� �Է����ּ���");
		}
		
		// �������
		boolean isSelectBirthDate = !birthDate.equals("") ? true : false;
		if (isSelectBirthDate) {
			request.setAttribute("birthDate", birthDate);
			success++;
		} else {
			request.setAttribute("birthDateErrer", "��������� �������ּ���");
		}
		
		// �̸���
		String pattern = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
		boolean isMatchEmail = email.matches(pattern);
		if (isMatchEmail) {
			request.setAttribute("email", email);
			success++;
		} else {
			request.setAttribute("emailErrer", "�ùٸ� �̸��� ������ �ƴմϴ�.");		
		}
		
		// ��ȭ��ȣ
		boolean isPhoneNumValid = (pMiddle.length() == 4 && pBack.length() == 4) ? true : false;
		if (isPhoneNumValid) {
			//request.setAttribute("phoneNum1", pFront+pMiddle+pBack);
			switch (pFront) {
			case "010":
				request.setAttribute("phoneNum1", 0);
				break;
			case "031":
				request.setAttribute("phoneNum1", 1);
				break;
			} 
			request.setAttribute("phoneNum2", pMiddle);
			request.setAttribute("phoneNum3", pBack);
			
			success++;
		} else {
			request.setAttribute("phoneNumErrer", "�ùٸ� ��ȭ��ȣ ������ �ƴմϴ�.");
		}
		
		// �ּ�
		boolean isAddressInput = !address.equals("") ? true : false;
		boolean isditailAddrInput = !detailAddr.equals("") ? true : false;
		if(isAddressInput && isditailAddrInput) {
			success++;
		} else {
			request.setAttribute("addressErrer", "�ּҸ� �Է����ּ���.");
		}
		request.setAttribute("address", address);
		request.setAttribute("detailAddr", detailAddr);
		
		// ȸ������ ���
		String nextPage = "/account/SignUp.jsp";
		
		if (success == 7) {
			vo.setId(id);
			vo.setPassword(pw);
			vo.setName(name);
			vo.setBirthDate(birthDate);
			vo.setEmail(email);
			vo.setPhoneNum(pFront+"-"+pMiddle+"-"+pBack);
			vo.setAddress(address+":"+detailAddr);
			dao.userDataInsert(vo);
			
			request.getSession().setAttribute("uid", id);
			nextPage = "/account/AccountLink.jsp";
		}
		
		return nextPage;
	}

}
