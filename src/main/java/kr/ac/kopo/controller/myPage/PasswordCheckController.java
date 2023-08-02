package kr.ac.kopo.controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.UserVO;

public class PasswordCheckController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "/myPage/PasswordMyPage.jsp";
		
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		
		String pw = request.getParameter("pw");
		String uid = (String)request.getSession().getAttribute("uid");
		
		vo.setId(uid);
		vo.setPassword(pw);
		
		if (dao.userCheck(vo)) {
			nextPage = "/pages/myPage.do";
		}
		
		return nextPage;
	}

}
