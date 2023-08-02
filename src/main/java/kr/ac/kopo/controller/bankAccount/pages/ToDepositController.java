package kr.ac.kopo.controller.bankAccount.pages;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.vo.BankAccountVO;

public class ToDepositController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		SavingAccountDAO dao = new SavingAccountDAO();
		
		
		String acNum = request.getParameter("acNumber");
		request.setAttribute("acNumber", acNum);
		
		String uid = (String)request.getSession().getAttribute("uid");
		List<BankAccountVO> acList = dao.getAccountList(uid);
		
		request.setAttribute("acList", acList);
		
		return "/bankAccount/Deposit.jsp";
	}

}
