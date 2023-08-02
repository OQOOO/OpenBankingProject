package kr.ac.kopo.controller.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;

public class ToPostController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		
		// ��ǥ �Խù� ��������

		String postId = request.getParameter("seq");

		int pid = Integer.parseInt(postId);
		
		// �Խù� ���� ��ȯ
		BoardVO vo = dao.postView(pid);
		request.setAttribute("vo", vo);
		
		// ��� ���� ��ȯ
		List<BoardVO> commentList = dao.commentView(pid);
		request.setAttribute("commentList", commentList);
		
		// ���� ���� ��ȯ
		List<BoardVO> reCommentList = dao.reCommentView(pid);
		request.setAttribute("reCommentList", reCommentList);
		System.out.println(pid);
		System.out.println(reCommentList.size());
		
		// ��ȸ�� + 1
		dao.addPostViews(pid);

		
		
		return "/board/Post.jsp";
	}

}
