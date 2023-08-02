package kr.ac.kopo.controller.admin.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;

public class ToUserController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "/admin/userAd/UserAd.jsp";
	}
}
