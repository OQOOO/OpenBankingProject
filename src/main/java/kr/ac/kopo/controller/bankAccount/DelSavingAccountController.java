package kr.ac.kopo.controller.bankAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.vo.BankAccountVO;

public class DelSavingAccountController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		SavingAccountDAO dao = new SavingAccountDAO();
		BankAccountVO vo = new BankAccountVO();
		
		String acNumber = request.getParameter("acNumber");
		String inputPw = request.getParameter("acPassword");
		String delSwitch = request.getParameter("delSwitch");
		
		vo = dao.getAccountInfo(acNumber);
		
		long balance = vo.getBalance();
		String acPw = vo.getAcPassword();

		String nextPage = "/bankAccount/pages/toManagement.do";
		
		if(!delSwitch.equals("1")) {
			
			if (balance != 0) {
				request.setAttribute("alertSwitch", "balanceErrer");
				
			} else {
				request.setAttribute("alertSwitch", "inputPassword");
			}
			request.setAttribute("ansPw", acPw);

		} else {
			
			dao.delSavingAccount(acNumber);
			nextPage = "/pages/IndexMain.do";
			
		}
		
		

		return nextPage;
	}
}
