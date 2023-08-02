package kr.ac.kopo.controller.admin.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.UserVO;

public class UserSearchController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		List<UserVO> userList = new ArrayList<>();
		
		// �Ķ����
		String inputType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		
		// �˻�����
		vo.setId(inputType);
		vo.setPassword("%"+searchText+"%");
		
		// �˻����
		userList = dao.searchUser(vo);
		
		// ����� ��ȯ
		request.setAttribute("userList", userList);
		
		return "/admin/userAd/UserAd.jsp";
	}

}
