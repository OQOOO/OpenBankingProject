package kr.ac.kopo.controller.bankAccount.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.AccountDAO;
import kr.ac.kopo.dao.HistoryDAO;
import kr.ac.kopo.service.AccountTransaction;
import kr.ac.kopo.service.NumberUtil;
import kr.ac.kopo.service.PagingUtil;
import kr.ac.kopo.vo.BankAccountVO;
import kr.ac.kopo.vo.HistoryVO;

public class ToAccountInfoController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		AccountTransaction acct = new AccountTransaction();
		// 계좌 정보
		//TransactionHistoryDAO hDAO = new TransactionHistoryDAO();
		//SavingAccountDAO sDao = new SavingAccountDAO();
		Map<String, String> bankNameMap = new HashMap<String, String>();
		bankNameMap.put("37", "CloudBank");
		bankNameMap.put("41", "CYBank");
		bankNameMap.put("14", "JHBank");
		bankNameMap.put("10", "JOYBank");
		
		
		NumberUtil numUtil = new NumberUtil();
		String acNumber = request.getParameter("acNumber");
		AccountDAO sDao = acct.getDAOWithAccountNum(acNumber);
		HistoryDAO hDAO = acct.getHistoryDAOWithAccountNum(acNumber);
		
		BankAccountVO vo = sDao.getAccountInfo(acNumber);
		long balance = vo.getBalance();
		vo.setCurrency(numUtil.asCurrency(balance));
		vo.setCurrencyKR(numUtil.asCurrencyWithKorean(balance));
		System.out.println(vo.toString());
		vo.setBankCode(bankNameMap.getOrDefault(vo.getAcNumber().substring(0, 2), ""));
		
		request.setAttribute("svo", vo);
		
		
		// 계좌 기록
		List<HistoryVO> acHistoryList = hDAO.getAccountHistory(acNumber);
		
		
	
		// 부호때문에 오류 안나게 처리하기
		for(HistoryVO v : acHistoryList) {
			String cd = v.getTrAcNumber().substring(0, 2);
			v.setTrAcNumber(bankNameMap.getOrDefault(cd, "") + " " + v.getTrAcNumber());
			
			long a = v.getAmount();
			String np = "+";
			if (a < 0) {
				a *= -1;
				np = "-";
			}
			
			v.setCurrency(np+numUtil.asCurrency(a));
			v.setCurrencyKR(np+numUtil.asCurrencyWithKorean(a));
		}
		
		
		
		// 초기값 (현 페이지와 출력리스트)
		PagingUtil pg = new PagingUtil();
		
		//
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = 5;
		int pageBtnNum = 5; // 화면의 버튼 수
		
		
		// 현 페이지 처리값, 버튼시작값, 버튼끝값
		int[] arr = pg.getbtnPageProcess(acHistoryList, pageSize, page, pageBtnNum);
		// 페이지 출력
		List<HistoryVO> pageViewList = pg.getPagingList(acHistoryList, arr[0], pageSize);
		
		// 화면에 반환 (5개)
		request.setAttribute("nowPage", page);
		request.setAttribute("btnStart", arr[1]);
		request.setAttribute("btnEnd", arr[2]);
		request.setAttribute("hList", pageViewList);
		request.setAttribute("pageBtnNum", pageBtnNum);
		
		
		return "/bankAccount/AccountInfo.jsp";
	}
	
}











