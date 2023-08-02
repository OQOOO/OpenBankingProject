package kr.ac.kopo.controller.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.util.Secrets;

public class LogoutController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().removeAttribute("uid");
		request.getSession().removeAttribute("adminRight");
		Secrets sec = new Secrets();
		String kakaoKey = sec.getKakaoKey();
		request.setAttribute("kakaoKey", kakaoKey);
		request.setAttribute("flag", "logout");
			
		return "/Index.jsp";
	}

}
