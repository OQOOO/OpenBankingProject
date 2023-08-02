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
		
		// 목표 게시물 가져오기

		String postId = request.getParameter("seq");

		int pid = Integer.parseInt(postId);
		
		// 게시물 정보 반환
		BoardVO vo = dao.postView(pid);
		request.setAttribute("vo", vo);
		
		// 댓글 정보 반환
		List<BoardVO> commentList = dao.commentView(pid);
		request.setAttribute("commentList", commentList);
		
		// 대댓글 정보 반환
		List<BoardVO> reCommentList = dao.reCommentView(pid);
		request.setAttribute("reCommentList", reCommentList);
		System.out.println(pid);
		System.out.println(reCommentList.size());
		
		// 조회수 + 1
		dao.addPostViews(pid);

		
		
		return "/board/Post.jsp";
	}

}
