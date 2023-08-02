package kr.ac.kopo.controller.bankAccount.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;

public class ToCreateDepositAccountController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		return "/bankAccount/CreateDepositAccount.jsp";
	}

}
