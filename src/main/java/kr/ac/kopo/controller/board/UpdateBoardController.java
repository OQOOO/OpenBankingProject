package kr.ac.kopo.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;

public class UpdateBoardController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		
		String spid = request.getParameter("pid");
		int pid = Integer.parseInt(spid);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		vo.setPostId(pid);
		vo.setTitle(title);
		vo.setContent(content);
		
		dao.updatePost(vo);
		
		return "/pages/toPost.do?seq="+pid;
	}
}
