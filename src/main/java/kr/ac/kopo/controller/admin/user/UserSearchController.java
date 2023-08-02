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
		
		// 파라미터
		String inputType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		
		// 검색조건
		vo.setId(inputType);
		vo.setPassword("%"+searchText+"%");
		
		// 검색결과
		userList = dao.searchUser(vo);
		
		// 결과값 반환
		request.setAttribute("userList", userList);
		
		return "/admin/userAd/UserAd.jsp";
	}

}
