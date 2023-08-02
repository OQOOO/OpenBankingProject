package kr.ac.kopo.controller.admin.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.dao.TransactionHistoryDAO;
import kr.ac.kopo.service.NumberUtil;
import kr.ac.kopo.service.PagingUtil;
import kr.ac.kopo.vo.BankAccountVO;
import kr.ac.kopo.vo.HistoryVO;

public class ToUserBankAccountInfoAdController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// ���� ����
				SavingAccountDAO sDao = new SavingAccountDAO();
				NumberUtil numUtil = new NumberUtil();
				String acNumber = request.getParameter("acNumber");
				
				BankAccountVO vo = sDao.getAccountInfo(acNumber);
				long balance = vo.getBalance();
				vo.setCurrency(numUtil.asCurrency(balance));
				vo.setCurrencyKR(numUtil.asCurrencyWithKorean(balance));

				request.setAttribute("svo", vo);
				
				
				// ���� ���
				TransactionHistoryDAO hDAO = new TransactionHistoryDAO();
				List<HistoryVO> acHistoryList = hDAO.getAccountHistory(acNumber);
				
				// ��ȣ������ ���� �ȳ��� ó���ϱ�
				for(HistoryVO v : acHistoryList) {
					long a = v.getAmount();
					String np = "+";
					if (a < 0) {
						a *= -1;
						np = "-";
					}
					
					v.setCurrency(np+numUtil.asCurrency(a));
					v.setCurrencyKR(np+numUtil.asCurrencyWithKorean(a));
				}
				
				
				
				// �ʱⰪ (�� �������� ��¸���Ʈ)
				PagingUtil pg = new PagingUtil();
				
				//
				int page = Integer.parseInt(request.getParameter("page"));
				int pageSize = 5;
				int pageBtnNum = 5; // ȭ���� ��ư ��
				
				
				// �� ������ ó����, ��ư���۰�, ��ư����
				int[] arr = pg.getbtnPageProcess(acHistoryList, pageSize, page, pageBtnNum);
				// ������ ���
				List<HistoryVO> pageViewList = pg.getPagingList(acHistoryList, arr[0], pageSize);
				
				// ȭ�鿡 ��ȯ (5��)
				request.setAttribute("nowPage", page);
				request.setAttribute("btnStart", arr[1]);
				request.setAttribute("btnEnd", arr[2]);
				request.setAttribute("hList", pageViewList);
				request.setAttribute("pageBtnNum", pageBtnNum);
		
		return "/admin/userAd/BankAccountInfoAd2.jsp";
	}
}
