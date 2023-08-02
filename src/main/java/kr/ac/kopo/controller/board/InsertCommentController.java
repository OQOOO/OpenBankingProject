package kr.ac.kopo.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;

public class InsertCommentController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		BoardVO coVo = new BoardVO();
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		String uid = (String)request.getSession().getAttribute("uid");
		Object adminRight = request.getSession().getAttribute("adminRight");
		String contents = request.getParameter("contents");
		
		coVo.setPostId(postId);
		coVo.setUserId(uid);
		coVo.setContent(contents);
		
		
		
		
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
