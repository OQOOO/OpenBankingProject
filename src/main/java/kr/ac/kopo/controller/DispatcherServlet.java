package kr.ac.kopo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HandlerMapping mapping;
	
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	String propLoc = config.getInitParameter("propLoc");
    	System.out.println("propLoc" + propLoc);
    	mapping = new HandlerMapping(propLoc);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("\n--- dispatcherServlet에서 출력 --------------------\n");
		
		// 요청 페이지의 URI
		String uri = request.getRequestURI();
		System.out.println("dispatcher: 요청 페이지의 uri 받기 성공... : " + uri);
		
		// 적당한 형태로 주소 가공
		String path = uri.substring(request.getContextPath().length());
		System.out.println("dispatcher: 적당한 형태로 문자열 주소 가공 성공... : " + path);
		
		// 주소를 통해 적당한 컨트롤러 가져오기
		Controller ctrl = mapping.getController(path);
		System.out.println("dispatcher: 주소를 통해 컨트롤러 가져오기 성공(핸들러매핑 실행성공)...");
		System.out.println(" c--- 컨트롤러 실행 -------");
		
		// 이부분에서 컨트롤러 실행 후 페이지 주소 반환
		String viewPage = ctrl.handleRequest(request, response);
		System.out.println(" c--- 컨트롤러 실행 종료 ---");
		System.out.println("dispatcher: 컨트롤러 실행 후 페이지 주소 반환 성공...");
		
		if(viewPage.startsWith("redirect:")) {
			response.sendRedirect(viewPage.substring("redirect:".length()));
		} else {
			// 컨트롤러에서 받아온 페이지로 이동
			RequestDispatcher rd = request.getRequestDispatcher(viewPage);
			rd.forward(request, response);
		}
		System.out.println("\n--- dispatcherServlet에서 출력 끝 ------------------\n");
	}

}