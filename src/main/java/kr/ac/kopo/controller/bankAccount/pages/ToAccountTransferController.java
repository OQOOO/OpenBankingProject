package kr.ac.kopo.controller.bankAccount.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.AccountDAO;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.dao.link.CYbankLinkDAO;
import kr.ac.kopo.dao.link.JHbankDAO;
import kr.ac.kopo.dao.link.JOYbankDAO;
import kr.ac.kopo.service.AccountTransaction;
import kr.ac.kopo.service.NumberUtil;
import kr.ac.kopo.vo.BankAccountVO;

public class ToAccountTransferController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		AccountTransaction acct = new AccountTransaction();	
		String acNum = request.getParameter("acNumber");
		
		//AccountDAO dao = acct.getDAOWithAccountNum(acNum);
		UserDAO userDAO = new UserDAO();
		BankAccountVO myAcInfoVo = new BankAccountVO();
		NumberUtil nUtil = new NumberUtil();
		
		AccountDAO dao = new SavingAccountDAO();
		AccountDAO cyLinkDAO = new CYbankLinkDAO();
		AccountDAO joyLinkDAO = new JOYbankDAO();
		AccountDAO jhLinkDAO = new JHbankDAO();
		
		AccountDAO nowAcDAO = acct.getDAOWithAccountNum(acNum);
		
		
		myAcInfoVo = nowAcDAO.getAccountInfo(acNum);
		myAcInfoVo.setCurrency(nUtil.asCurrency(myAcInfoVo.getBalance()));
		myAcInfoVo.setCurrencyKR(nUtil.asCurrencyWithKorean(myAcInfoVo.getBalance()));
		
		String uid = (String)request.getSession().getAttribute("uid");
		String email = userDAO.getUserEmailWithId(uid);
		
		List<BankAccountVO> acList = dao.getAccountListWithEmail(email);
		acList.addAll(cyLinkDAO.getAccountListWithEmail(email));
		acList.addAll(joyLinkDAO.getAccountListWithEmail(email));
		acList.addAll(jhLinkDAO.getAccountListWithEmail(email));
		
		Map<String, String> bankNameMap = new HashMap<String, String>();
		bankNameMap.put("37", "Cloud Bank");
		bankNameMap.put("41", "CY Bank");
		bankNameMap.put("14", "JH Bank");
		bankNameMap.put("10", "JOY Bank");
		
		for(BankAccountVO v : acList) {
			String cd = v.getAcNumber().substring(0, 2);
			v.setBankCode(bankNameMap.getOrDefault(cd, ""));
		}
		
		request.setAttribute("myAcVo", myAcInfoVo);
		request.setAttribute("acList", acList);
		
		return "/bankAccount/AccountTransfer.jsp";
	}
}
