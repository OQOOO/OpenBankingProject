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
		System.out.println("RemovePostController���� ���: ����");
		BoardDAO dao = new BoardDAO();
		// Ư�� ���ù��� ��� ��ȣ��� ������ �ݺ������� ��� ��� ���� �� post ����
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
			coVo.setUserId("[������]");
			if(adminRight.equals("1")) {
				nextPage = "/admin/pages/toBoard.do";
				
			}
		}
		dao.insertComment(coVo);
		
		request.setAttribute("toPage", nextPage);
		return "/refresh.do";
	}
}
