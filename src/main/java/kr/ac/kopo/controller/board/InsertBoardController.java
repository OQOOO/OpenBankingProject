package kr.ac.kopo.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;

public class InsertBoardController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		
		String uid = (String)request.getSession().getAttribute("uid");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		vo.setUserId(uid);
		vo.setTitle(title);
		vo.setContent(content);
		
		dao.insertBoard(vo);
		
		request.setAttribute("toPage", "/pages/toBoard.do");
		return "/refresh.do";
	}
}
