package kr.ac.kopo.controller.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.DepositAccountDAO;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.dao.link.CYbankLinkDAO;
import kr.ac.kopo.service.NumberUtil;
import kr.ac.kopo.vo.BankAccountVO;
import kr.ac.kopo.vo.UserVO;

public class ToIndexMainController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String nextPage = "/Index.jsp";
		System.out.println("indexMain");

		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("uid") != null) {
			
			CYbankLinkDAO ldao = new CYbankLinkDAO();
			String uid = (String)session.getAttribute("uid");
			UserDAO uDao = new UserDAO();
			SavingAccountDAO sDao = new SavingAccountDAO();
			DepositAccountDAO dDao = new DepositAccountDAO();
			NumberUtil numUtil = new NumberUtil();
			
			// 타 은행 연동
//			AccountDAO cyLinkDAO = new CYbankLinkDAO();
//			AccountDAO joyLinkDAO = new JOYbankDAO();
//			AccountDAO jhLinkDAO = new JHbankDAO();
			
			//
			
			nextPage = "/Main.jsp";
			
			// 유저 정보
			UserVO uvo = uDao.getUserData(uid);
			String email = uvo.getEmail();
			
			// 계정의 모든 자산의 총 합
			long sav = sDao.getTotalBalanceByUser(uid);
			long dep = dDao.getTotalBalanceByUser(uid);
			
//			long jhSav = jhLinkDAO.getTotalBalanceByUserWithEmail(email);
//			long joySav = joyLinkDAO.getTotalBalanceByUserWithEmail(email);
//			long cySav = cyLinkDAO.getTotalBalanceByUserWithEmail(email);
			long total = sav + dep; // + jhSav + joySav + cySav;
			
			String currency = numUtil.asCurrency(total);
			String currencyKR = numUtil.asCurrencyWithKorean(total);
			
			// 다른 은행의 같은 이메일인 계좌도 출력
			// 계좌 정보 출력
			List<BankAccountVO> savingAcList = sDao.getAccountListWithEmail(email);
			for(BankAccountVO v : savingAcList) {
				long bal = v.getBalance();
				v.setBankCode("CloudBank");
				v.setCurrency(numUtil.asCurrency(bal));
				v.setCurrencyKR(numUtil.asCurrencyWithKorean(bal));
			}
			
//			List<BankAccountVO> jhSavingAcList = jhLinkDAO.getAccountListWithEmail(email);
//			for(BankAccountVO v : jhSavingAcList) {
//				long bal = v.getBalance();
//				v.setBankCode("JHBank");
//				v.setCurrency(numUtil.asCurrency(bal));
//				v.setCurrencyKR(numUtil.asCurrencyWithKorean(bal));
//			}
//			
//			List<BankAccountVO> cySavingAcList = cyLinkDAO.getAccountListWithEmail(email);
//			for(BankAccountVO v : cySavingAcList) {
//				long bal = v.getBalance();
//				v.setBankCode("CYBank");
//				v.setCurrency(numUtil.asCurrency(bal));
//				v.setCurrencyKR(numUtil.asCurrencyWithKorean(bal));
//			}
//			
//			List<BankAccountVO> joySavingAcList = joyLinkDAO.getAccountListWithEmail(email);
//			for(BankAccountVO v : joySavingAcList) {
//				long bal = v.getBalance();
//				v.setBankCode("JOYBank");
//				v.setCurrency(numUtil.asCurrency(bal));
//				v.setCurrencyKR(numUtil.asCurrencyWithKorean(bal));
//			}
			
//			savingAcList.addAll(jhSavingAcList);
//			savingAcList.addAll(cySavingAcList);
//			savingAcList.addAll(joySavingAcList);
			//
			
			//적금 정보 출력
			List<BankAccountVO> depositAcList = dDao.getAccountList(uid);
			for(BankAccountVO v : depositAcList) {
				long bal = v.getBalance();
				v.setCurrency(numUtil.asCurrency(bal));
				v.setCurrencyKR(numUtil.asCurrencyWithKorean(bal));
			}
			
			// 반환하기
			request.setAttribute("isDataUpdate", request.getSession().getAttribute("isDataUpdate"));
			request.getSession().removeAttribute("isDataUpdate");
			
			request.setAttribute("totalSaving", sav);
			request.setAttribute("totalDeposit", dep);
			
			request.setAttribute("totalBalance", currency);
			request.setAttribute("totalBalanceKR", currencyKR);
			request.setAttribute("uvo", uvo);
			
			request.setAttribute("savingAcList", savingAcList);
			request.setAttribute("depositAcList", depositAcList);
		}

		return nextPage;
	}

}
