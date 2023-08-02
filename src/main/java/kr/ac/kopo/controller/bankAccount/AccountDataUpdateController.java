package kr.ac.kopo.controller.bankAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.SavingAccountDAO;
import kr.ac.kopo.vo.BankAccountVO;

public class AccountDataUpdateController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		SavingAccountDAO dao = new SavingAccountDAO();
		BankAccountVO vo = new BankAccountVO();
		
		// < 입력 > //
		String acNumber = request.getParameter("acNumber");
		
		String acName = request.getParameter("accountName");
		String bfAcName = request.getParameter("bfAcName");
		
		String acPw = request.getParameter("accountPw");
		
		String acPwCheck = request.getParameter("accountPwCheck");
		String uid = (String)request.getSession().getAttribute("uid");
		
		// < 처리 > //
		int success = 0;
		vo.setAcNumber(acNumber);
		
		// 계좌명
			// 계좌명 입력 확인
		boolean isAcNameInput = !acName.equals("") ? true : false;
		if (isAcNameInput) {
			vo.setAcName(acName);
			vo.setUserId(uid);
		}
		
			// 같은유저 계좌명 중복 확인
		boolean isIdNotDuplicate = dao.accountNameCheck(vo);
		if (isAcNameInput && (isIdNotDuplicate || acName.equals(bfAcName) )) {
			vo.setAcName(acName);
			success++;
		} else {
			if (isAcNameInput == false) {
				request.setAttribute("acNameErrer", "계좌이름을 입력해주세요");
			} else if ((isIdNotDuplicate || acName.equals(bfAcName) ) == false) {
				request.setAttribute("acNameErrer", "중복된 계좌명입니다.");
			}
		}
		
		// 계좌비밀번호
		boolean isPasswordMatch = (acPw.equals(acPwCheck)) ? true : false;
		if (isPasswordMatch && acPw.length() == 4) {
			vo.setAcPassword(acPw);
			success++;
		} else {
			if (acPw.length() != 4) {
				request.setAttribute("acPwErrer", "4자리 비밀번호를 입력해주세요");
			} else {
				request.setAttribute("acPwwErrer", "비밀번호가 일치하지 않습니다.");
			}
			
		}
		
		
		// 입금은행
		// 입금계좌번호
		
		// < 데이터베이스 입력 >
		String nextPage = "/bankAccount/AccountDataUpdate.jsp";

		if (success == 2) {
			dao.accountDataUpdate(vo);
			request.setAttribute("isDataUpdate", "1");
			nextPage = "/bankAccount/pages/toAccountInfo.do?acNumber="+acNumber+"&page=1";
		}
		request.setAttribute("vo", vo);

		return nextPage;
	}
}
