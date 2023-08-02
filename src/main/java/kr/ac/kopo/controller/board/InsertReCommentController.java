package kr.ac.kopo.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;

public class InsertReCommentController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("InsertReCommentController 실행");
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();

		// 입력부
		int cId = Integer.parseInt(request.getParameter("commentId"));
		String uid = (String)request.getSession().getAttribute("uid");
		String contents = request.getParameter("contents");
		String postId = request.getParameter("postId");
		//
		String parentReId = request.getParameter("parentReId");
		String parentDepth = request.getParameter("parentDepth");
		Object adminRight = request.getSession().getAttribute("adminRight");
		// 조건체크

		int depth = 1;
		if (parentReId != null && parentDepth != null) {
			int pid = Integer.parseInt(parentReId);
			vo.setParentReId(pid);
			
			depth = Integer.parseInt(parentDepth) + 1;
		}
	
		vo.setDepth(depth);
		
		// /입력부
		
		
		vo.setCommentId(cId);
		vo.setUserId(uid);
		vo.setContent(contents);
		
		String nextPage = "/pages/toPost.do?seq=";
		if(adminRight != null) {
			adminRight = (String) adminRight;
			vo.setUserId("[관리자]");
			if(adminRight.equals("1")) {
				nextPage = "/admin/pages/toPost.do?seq=";
				
			}
		}
		
		dao.insertReComment(vo);
		
		request.setAttribute("toPage", nextPage+postId);
		return "/refresh.do";
	}
}
