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
		
		System.out.println("\n--- dispatcherServlet���� ��� --------------------\n");
		
		// ��û �������� URI
		String uri = request.getRequestURI();
		System.out.println("dispatcher: ��û �������� uri �ޱ� ����... : " + uri);
		
		// ������ ���·� �ּ� ����
		String path = uri.substring(request.getContextPath().length());
		System.out.println("dispatcher: ������ ���·� ���ڿ� �ּ� ���� ����... : " + path);
		
		// �ּҸ� ���� ������ ��Ʈ�ѷ� ��������
		Controller ctrl = mapping.getController(path);
		System.out.println("dispatcher: �ּҸ� ���� ��Ʈ�ѷ� �������� ����(�ڵ鷯���� ���༺��)...");
		System.out.println(" c--- ��Ʈ�ѷ� ���� -------");
		
		// �̺κп��� ��Ʈ�ѷ� ���� �� ������ �ּ� ��ȯ
		String viewPage = ctrl.handleRequest(request, response);
		System.out.println(" c--- ��Ʈ�ѷ� ���� ���� ---");
		System.out.println("dispatcher: ��Ʈ�ѷ� ���� �� ������ �ּ� ��ȯ ����...");
		
		if(viewPage.startsWith("redirect:")) {
			response.sendRedirect(viewPage.substring("redirect:".length()));
		} else {
			// ��Ʈ�ѷ����� �޾ƿ� �������� �̵�
			RequestDispatcher rd = request.getRequestDispatcher(viewPage);
			rd.forward(request, response);
		}
		System.out.println("\n--- dispatcherServlet���� ��� �� ------------------\n");
	}

}