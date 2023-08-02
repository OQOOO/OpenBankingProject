package kr.ac.kopo.controller.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;

public class ToSignUpController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("toSingUp");
		return "/account/SignUp.jsp";
	}
}
