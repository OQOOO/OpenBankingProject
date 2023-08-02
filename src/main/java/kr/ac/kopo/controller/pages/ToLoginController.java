package kr.ac.kopo.controller.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.util.Secrets;



public class ToLoginController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("toLogin");
		Secrets sec = new Secrets();
		String kakaoKey = sec.getKakaoKey();
		request.setAttribute("kakaoKey", kakaoKey);
		
		return "/account/Login.jsp";
	}
}
