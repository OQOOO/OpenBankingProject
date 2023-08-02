package kr.ac.kopo.controller.admin.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.DepositAccountDAO;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.service.NumberUtil;
import kr.ac.kopo.vo.BankAccountVO;
import kr.ac.kopo.vo.UserVO;

public class ToUserBankAccountAdController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String uid = request.getParameter("userId");
		UserDAO uDao = new UserDAO();
		SavingAccountDAO sDao = new SavingAccountDAO();
		DepositAccountDAO dDao = new DepositAccountDAO();
		NumberUtil numUtil = new NumberUtil();
		
		// 유저 정보
		UserVO uvo = uDao.getUserData(uid);
		
		// 계정의 모든 자산의 총 합
		
		long sav = sDao.getTotalBalanceByUser(uid);
		long dep = dDao.getTotalBalanceByUser(uid);
		long total = sav + dep;
		
		String currency = numUtil.asCurrency(total);
		String currencyKR = numUtil.asCurrencyWithKorean(total);
		

		// 계좌 정보 출력
		List<BankAccountVO> savingAcList = sDao.getAccountList(uid);
		for(BankAccountVO v : savingAcList) {
			long bal = v.getBalance();
			v.setCurrency(numUtil.asCurrency(bal));
			v.setCurrencyKR(numUtil.asCurrencyWithKorean(bal));
		}
		List<BankAccountVO> depositAcList = dDao.getAccountList(uid);
		for(BankAccountVO v : depositAcList) {
			long bal = v.getBalance();
			v.setCurrency(numUtil.asCurrency(bal));
			v.setCurrencyKR(numUtil.asCurrencyWithKorean(bal));
		}
		
		// 반환하기
		request.setAttribute("totalSaving", sav);
		request.setAttribute("totalDeposit", dep);
		
		request.setAttribute("totalBalance", currency);
		request.setAttribute("totalBalanceKR", currencyKR);
		request.setAttribute("uvo", uvo);
		
		request.setAttribute("savingAcList", savingAcList);
		request.setAttribute("depositAcList", depositAcList);
		return "/admin/userAd/BankAccountInfoAd.jsp";
	}

}
