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
		
		// ============ 입력구간 ============ //
		// 아이디
		String id = request.getParameter("id");
		
		// 비밀번호
		String pw = request.getParameter("pw");
		String pwCheck = request.getParameter("pwCheck");
		
		// 이메일
		String email = request.getParameter("email");
		
		// 이름
		String name = request.getParameter("name");
		
		// 생년월일
		String birthDate = request.getParameter("birthDate");
		
		// 전화번호
		String pFront = request.getParameter("phoneNum1");
		String pMiddle = request.getParameter("phoneNum2");
		String pBack = request.getParameter("phoneNum3");
		
		// 주소
		String address = request.getParameter("address");
		String detailAddr = request.getParameter("detailAddr");
		
		// ============ 처리구간 ============ //
		int success = 0;
		// 아이디
		boolean isIdInput = !id.equals("") ? true : false;
		boolean isIdNotDuplicate = dao.idChack(id);
		
		if (isIdInput && isIdNotDuplicate) {
			request.setAttribute("id", id);
			success++;
		} else {
			if (isIdInput == false) {
				request.setAttribute("idErrer", "아이디를 입력해주세요");
			} else if (isIdNotDuplicate == false) {
				request.setAttribute("idErrer", "중복된 아이디입니다.");
			}
		}
		
		// 비밀번호
		boolean isPasswordMatch = (pw.equals(pwCheck)) ? true : false;
		if (isPasswordMatch && pw.length() >= 4) {
			request.setAttribute("pw", pw);
			success++;
		} else {
			if (pw.length() < 4) {
				request.setAttribute("pwErrer", "비밀번호는 4자리 이상 입력해주세요");
			} else {
				request.setAttribute("pwErrer", "비밀번호가 일치하지 않습니다.");
			}
			
		}
		
		// 이름
		boolean isNameInput = !name.equals("") ? true : false;
		if (isNameInput) {
			request.setAttribute("name", name);
			success++;
		} else {
			request.setAttribute("nameErrer", "이름을 입력해주세요");
		}
		
		// 생년월일
		boolean isSelectBirthDate = !birthDate.equals("") ? true : false;
		if (isSelectBirthDate) {
			request.setAttribute("birthDate", birthDate);
			success++;
		} else {
			request.setAttribute("birthDateErrer", "생년월일을 선택해주세요");
		}
		
		// 이메일
		String pattern = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
		boolean isMatchEmail = email.matches(pattern);
		if (isMatchEmail) {
			request.setAttribute("email", email);
			success++;
		} else {
			request.setAttribute("emailErrer", "올바른 이메일 형식이 아닙니다.");		
		}
		
		// 전화번호
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
			request.setAttribute("phoneNumErrer", "올바른 전화번호 형식이 아닙니다.");
		}
		
		// 주소
		boolean isAddressInput = !address.equals("") ? true : false;
		boolean isditailAddrInput = !detailAddr.equals("") ? true : false;
		if(isAddressInput && isditailAddrInput) {
			success++;
		} else {
			request.setAttribute("addressErrer", "주소를 입력해주세요.");
		}
		request.setAttribute("address", address);
		request.setAttribute("detailAddr", detailAddr);
		
		// 회원가입 결과
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
