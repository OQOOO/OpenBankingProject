package kr.ac.kopo.controller.board.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;

public class ToUpdateBoardController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		
		String sPid = request.getParameter("postId");

		int pid = Integer.parseInt(sPid);
		
		BoardVO vo = dao.postView(pid);
		
		request.setAttribute("vo", vo);
		
		
		return "/board/UpdateBoard.jsp";
	}
}
