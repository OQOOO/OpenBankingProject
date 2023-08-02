package kr.ac.kopo.controller.bankAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.DepositAccountDAO;

public class DepositAcRemoveController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		DepositAccountDAO dao = new DepositAccountDAO();
		
		String acNumber = request.getParameter("acNumber");
		
		dao.delDepositAccount(acNumber);
		
		request.setAttribute("isDataUpdate", "delDeposit");
		return "/pages/IndexMain.do";
	}

}
