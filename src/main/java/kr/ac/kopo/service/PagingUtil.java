package kr.ac.kopo.service;

import java.util.ArrayList;
import java.util.List;

public class PagingUtil {
	
	
	/**
	 * 페이징 할 리스트, 페이지 크기
	 * @param li
	 * @param pageSize
	 * @return
	 */
	public int getAllPageBtnNum(List<?> li, int pageSize) {
		int pageBtnNum = li.size() / pageSize + 1;
		return pageBtnNum;
	}
	
	/**
	 * 총 페이지 수, 페이지당 버튼 수, 현제페이지
	 * @param allPageBtnNum
	 * @param pageBtnNum
	 * @param page
	 * @return
	 */
	public int[] getPageButtonStartEnd(int allPageBtnNum, int pageBtnNum, int page) {
		int btnPage = page / pageBtnNum + 1;

		if (page % pageBtnNum == 0) {
			btnPage = (page-1) / pageBtnNum + 1;
		}

		
		int btnStart = pageBtnNum * (btnPage - 1) + 1;
		
		int btnEnd = pageBtnNum * btnPage;
		
		if(btnEnd > allPageBtnNum) {
			btnEnd = allPageBtnNum;
		}
		
		if(page > btnEnd) {
			page = btnEnd;
		}
		
		int[] arr = new int[2];
		arr[0] = btnStart;
		arr[1] = btnEnd;
		
		return arr;
	}
	
	/**
	 * 
	 * @param infoList
	 * @param pageSize
	 * @param nowPage
	 * @param pageBtnNum
	 * @return
	 */
	public int[] getbtnPageProcess(List<?> infoList, int pageSize, int nowPage, int pageBtnNum) {
		int allPageBtnNum = getAllPageBtnNum(infoList, pageSize);
		int[] arr = getPageButtonStartEnd(allPageBtnNum, pageBtnNum, nowPage);
		
		// 시작페이지 끝페이지
		int btnStart = arr[0];
		int btnEnd = arr[1];
		
		// 페이지 끝 처리
		int page = nowPage < 1 ? 1 : nowPage;
		page = page > btnEnd ? btnEnd : page;

		int[] resultArr = {page, btnStart, btnEnd};
		
		return resultArr;
	}
	
	/**
	 * 페이징 할 리스트, 클라이언트에서 받은 페이지, 페이지에 나타낼 목록 수
	 * @param <T>
	 * @param li
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> getPagingList(List<T> li, int page, int pageSize) {
	    List<T> pageViewList = new ArrayList<>();

	    int pageStart = pageSize * (page - 1);
	    int pageEnd = pageSize * page;

	    // 페이지 마지막이 리스트보다 많다면
	    if (pageEnd > li.size()) {
	        pageEnd = li.size();
	    }

	    // 화면에 반환할 페이지 리스트 add
	    for (int i = pageStart; i < pageEnd; ++i) {
	        pageViewList.add(li.get(i));
	    }
	    return pageViewList;
	}
	
}

/* Controller

		BoardDAO dao = new BoardDAO();
		
		List<BoardVO> postList = dao.boardView();
		//request.setAttribute("postList", postList);
		
		PagingUtil pg = new PagingUtil();
		
		//////////////////////////////////////////////////////////////////////
		String getPage = request.getParameter("page");
		int page = 0;
		if (getPage != null) {
			page = Integer.parseInt(getPage);
		}
		int pageSize = 5;
		int pageBtnNum = 5; // 화면의 버튼 수
		
		
		// 현 페이지 처리값, 버튼시작값, 버튼끝값
		int[] arr = pg.getbtnPageProcess(postList, pageSize, page, pageBtnNum);
		// 페이지 출력
		List<BoardVO> pageViewList = pg.getPagingList(postList, arr[0], pageSize);
		
		// 화면에 반환 (5개)
		request.setAttribute("nowPage", page);
		request.setAttribute("btnStart", arr[1]);
		request.setAttribute("btnEnd", arr[2]);
		request.setAttribute("postList", pageViewList);
		request.setAttribute("pageBtnNum", pageBtnNum);
		//////////////////////////////////////////////////////////////////////////
		
		return "/board/Board.jsp";

 */



/* jsp

<div id="pageButtonBox">
	<form method="post" action="${pageContext.request.contextPath }/pages/toBoard.do">
			<input id="infoClickAcNum" type="hidden" name="acNumber" value="${svo.getAcNumber() }">
			<input type="hidden" name="page" value="${btnStart-1}">
			<input id="beforePage" class="pageButton" type="submit" value="<<">
	 </form>
	<c:forEach var="i" begin="${btnStart}" end="${btnEnd}">
		<form method="post" action="${pageContext.request.contextPath }/pages/toBoard.do">
			<input id="infoClickAcNum" type="hidden" name="acNumber" value="${svo.getAcNumber() }">
			<input type="hidden" name="page" value="${i}">
			<c:if test="${nowPage eq i }">
				<input id="nowPage" class="pageButton" type="submit" value="${i}">
			</c:if>
			<c:if test="${nowPage != i }">
				<input class="pageButton" type="submit" value="${i}">
			</c:if>
	 	</form>
	</c:forEach>
	<form method="post" action="${pageContext.request.contextPath }/pages/toBoard.do">
		<input id="infoClickAcNum" type="hidden" name="acNumber" value="${svo.getAcNumber() }">
		<input type="hidden" name="page" value="${btnEnd+1}">
		<input id="nextPage" class="pageButton" type="submit" value=">>">
	 </form>
</div>

 */


















