package kr.ac.kopo.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;

public class RemoveCommentController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		BoardDAO dao = new BoardDAO();
		String postId = request.getParameter("postId");
		int commentId = Integer.parseInt(request.getParameter("cid"));
		
		dao.delComment(commentId);
		
		BoardVO coVo = new BoardVO();
		Object adminRight = request.getSession().getAttribute("adminRight");
		String nextPage = "/pages/toPost.do?seq=";
		if(adminRight != null) {
			adminRight = (String) adminRight;
			coVo.setUserId("[°ü¸®ÀÚ]");
			if(adminRight.equals("1")) {
				nextPage = "/admin/pages/toPost.do?seq=";
				
			}
		}
		dao.insertComment(coVo);
		
		request.setAttribute("toPage", nextPage+postId);
		return "/refresh.do";
	}
}
