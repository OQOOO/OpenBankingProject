package kr.ac.kopo.controller.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.UserDAO;

public class KakaoLoginController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		UserDAO dao = new UserDAO();
		
		String kakao = request.getParameter("kakaoId");
		
		String uid = dao.getUidWithKakaoId(kakao);
		
		if(!uid.equals("")) {
			request.getSession().setAttribute("uid", uid);
		}

		return "/pages/IndexMain.do";
	}

}
