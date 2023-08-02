package kr.ac.kopo.controller.bankAccount.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;

public class ToAccountManagementController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String acNumber = request.getParameter("acNumber");

		request.setAttribute("acNumber", acNumber);

		return "/bankAccount/AccountManagement.jsp";
	}
}
