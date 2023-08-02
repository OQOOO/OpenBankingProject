package kr.ac.kopo.controller.admin.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.service.PagingUtil;
import kr.ac.kopo.vo.BoardVO;

public class ToBoardOfAdminController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		
		List<BoardVO> postList = dao.boardView();
		//request.setAttribute("postList", postList);
		
		PagingUtil pg = new PagingUtil();
		
		//
		String getPage = request.getParameter("page");
		int page = 0;
		if (getPage != null) {
			page = Integer.parseInt(getPage);
		}
		int pageSize = 5;
		int pageBtnNum = 5; // ȭ���� ��ư ��
		
		
		// �� ������ ó����, ��ư���۰�, ��ư����
		int[] arr = pg.getbtnPageProcess(postList, pageSize, page, pageBtnNum);
		// ������ ���
		List<BoardVO> pageViewList = pg.getPagingList(postList, arr[0], pageSize);
		
		// ȭ�鿡 ��ȯ (5��)
		request.setAttribute("nowPage", page);
		request.setAttribute("btnStart", arr[1]);
		request.setAttribute("btnEnd", arr[2]);
		request.setAttribute("postList", pageViewList);
		request.setAttribute("pageBtnNum", pageBtnNum);
		
		return "/admin/boardAdmin/BoardOfAdmin.jsp";
	}

}
