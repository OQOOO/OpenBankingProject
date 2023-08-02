package kr.ac.kopo.controller.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.UserVO;

public class ToMyPageController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		
		String uid = (String)request.getSession().getAttribute("uid");
		
		vo = dao.getUserData(uid);
		
		// ��й�ȣ ������
		int sLen = vo.getPassword().length();
		StringBuilder star = new StringBuilder();
		for (int i = 0; i < sLen; ++i) {
			star.append("*");
		}
		vo.setPassword(star.toString());
		
		// ������� �ڸ���
		vo.setBirthDate(vo.getBirthDate().split(" ")[0]);
		
		// �ּ� ����
		vo.setAddress(vo.getAddress().replace(":", "   "));
		
		request.setAttribute("vo", vo);
		
		return "/myPage/MyPage.jsp";
	}

}
