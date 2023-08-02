package kr.ac.kopo.controller.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.UserVO;

public class LoginController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "/account/Login.jsp";

		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();

		String id = request.getParameter("id");
		String pw = request.getParameter("password");

		boolean isNotVoid = true;

		if (id.equals("")) {
			request.setAttribute("idErrer", "아이디를 입력하세요");
			isNotVoid = false;
		}

		if (pw.equals("")) {
			request.setAttribute("pwErrer", "비밀번호를 입력하세요");
			isNotVoid = false;
		}
		request.setAttribute("id", id);
		request.setAttribute("pw", pw);

		vo.setId(id);
		vo.setPassword(pw);

		boolean isLogin = dao.userCheck(vo);

		// 입력창 모두 채웠을때
		if (isNotVoid) {
			
			// 로그인 성공했다면
			if (isLogin) {
				nextPage = "/pages/IndexMain.do";
				HttpSession session = request.getSession();
				session.setAttribute("uid", id);
				
				// 관리자라면?
				if(dao.adminRightCheck(id)) {
					nextPage ="/admin/MainOfAdmin.jsp";
					session.setAttribute("adminRight", "1");
				}
				
				
			} else {
				request.setAttribute("pwErrer", "존재하지 않는 계정입니다.");
				request.setAttribute("id", "");
				request.setAttribute("pw", "");
			}
		}

		return nextPage;
	}
}
