package kr.ac.kopo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreventRefreshController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String toPage = (String)request.getAttribute("toPage");
		
		System.out.println("pre..." + toPage);
		request.setAttribute("toPage", toPage);
		
		//request.setAttribute("toPage", "실행할/페이지.do");
		//return "/refresh.do";

		return "/util/Refresh.jsp";
	}

}
