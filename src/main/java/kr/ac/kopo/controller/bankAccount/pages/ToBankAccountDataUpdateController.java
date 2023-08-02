package kr.ac.kopo.controller.bankAccount.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.vo.BankAccountVO;

public class ToBankAccountDataUpdateController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		SavingAccountDAO dao = new SavingAccountDAO();
		BankAccountVO vo = new BankAccountVO();
		
		String acNumber = request.getParameter("acNumber");
		
		vo = dao.getAccountInfo(acNumber);
		
		request.setAttribute("vo", vo);
		
		return "/bankAccount/AccountDataUpdate.jsp";
	}
}
