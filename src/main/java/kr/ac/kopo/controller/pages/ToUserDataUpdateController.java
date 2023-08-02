package kr.ac.kopo.controller.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.UserVO;

public class ToUserDataUpdateController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		
		String uid = (String)request.getSession().getAttribute("uid");
		
		vo = dao.getUserData(uid);
		
		request.setAttribute("id", uid);
		request.setAttribute("pw", vo.getPassword());
		request.setAttribute("name", vo.getName());
		request.setAttribute("birthDate", vo.getBirthDate());
		request.setAttribute("email", vo.getEmail());
		
		// 생년월일 자르기
		String birthDate = vo.getBirthDate().split(" ")[0];
		request.setAttribute("birthDate", birthDate);
		
		// 전화번호 가공
		String[] pArr = vo.getPhoneNum().split("-");
		
		request.setAttribute("phoneNum2", pArr[1]);
		request.setAttribute("phoneNum3", pArr[2]);
		switch (pArr[0]) {
		case "010":
			request.setAttribute("phoneNum1", 0);
			break;
		case "031":
			request.setAttribute("phoneNum1", 1);
			break;
		} 
		
		String[] addArr = vo.getAddress().split(":");
		
		request.setAttribute("address", addArr[0]);
		request.setAttribute("detailAddr", addArr[1]);
		
		
		return "/myPage/UserDataUpdate.jsp";
	}
}
