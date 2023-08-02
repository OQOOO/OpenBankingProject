package kr.ac.kopo.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;

public class RemovePostController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("RemovePostController에서 출력: 시작");
		BoardDAO dao = new BoardDAO();
		// 특정 개시물의 댓글 번호목록 가져와 반복문으로 댓글 모두 지운 후 post 삭제
		int postId = Integer.parseInt(request.getParameter("postId"));
		List<BoardVO> cList =  dao.commentView(postId);
		
		for(BoardVO vo : cList) {
			int cid = vo.getCommentId();
			
			dao.delComment(cid);
		}
		
		dao.delPost(postId);
		BoardVO coVo = new BoardVO();
		Object adminRight = request.getSession().getAttribute("adminRight");
		String nextPage = "/pages/toBoard.do";
		if(adminRight != null) {
			adminRight = (String) adminRight;
			coVo.setUserId("[관리자]");
			if(adminRight.equals("1")) {
				nextPage = "/admin/pages/toBoard.do";
				
			}
		}
		dao.insertComment(coVo);
		
		request.setAttribute("toPage", nextPage);
		return "/refresh.do";
	}
}
