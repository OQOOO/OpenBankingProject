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
			request.setAttribute("idErrer", "���̵� �Է��ϼ���");
			isNotVoid = false;
		}

		if (pw.equals("")) {
			request.setAttribute("pwErrer", "��й�ȣ�� �Է��ϼ���");
			isNotVoid = false;
		}
		request.setAttribute("id", id);
		request.setAttribute("pw", pw);

		vo.setId(id);
		vo.setPassword(pw);

		boolean isLogin = dao.userCheck(vo);

		// �Է�â ��� ä������
		if (isNotVoid) {
			
			// �α��� �����ߴٸ�
			if (isLogin) {
				nextPage = "/pages/IndexMain.do";
				HttpSession session = request.getSession();
				session.setAttribute("uid", id);
				
				// �����ڶ��?
				if(dao.adminRightCheck(id)) {
					nextPage ="/admin/MainOfAdmin.jsp";
					session.setAttribute("adminRight", "1");
				}
				
				
			} else {
				request.setAttribute("pwErrer", "�������� �ʴ� �����Դϴ�.");
				request.setAttribute("id", "");
				request.setAttribute("pw", "");
			}
		}

		return nextPage;
	}
}
