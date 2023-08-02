package kr.ac.kopo.controller.bankAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.dao.DepositAccountDAO;
import kr.ac.kopo.service.MakeAccountNumber;
import kr.ac.kopo.service.TimeUtil;
import kr.ac.kopo.vo.BankAccountVO;

public class CreateDepositAccountController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		DepositAccountDAO dao = new DepositAccountDAO();
		BankAccountVO vo = new BankAccountVO();
		
		// < 입력 > //
		String acName = request.getParameter("accountName");
		String acPw = request.getParameter("accountPw");
		String acPwCheck = request.getParameter("accountPwCheck");
		// 입금계좌, 은행 생략됨. 나중에 추가해야 함
		String diposit = request.getParameter("diposit");
		String regularTransfer = request.getParameter("regularTransfer");
		String uid = (String)request.getSession().getAttribute("uid");
		
		// < 처리 > //
		int success = 0;
		
		// 계좌명
		boolean isAcNameInput = !acName.equals("") ? true : false;
		if (isAcNameInput) {
			vo.setAcName(acName);
			vo.setUserId(uid);
		}
		
		boolean isIdNotDuplicate = dao.accountNameCheck(vo);
		
		if (isAcNameInput && isIdNotDuplicate) {
			request.setAttribute("acName", acName);
			success++;
		} else {
			if (isAcNameInput == false) {
				request.setAttribute("acNameErrer", "계좌이름을 입력해주세요");
			} else if (isIdNotDuplicate == false) {
				request.setAttribute("acNameErrer", "중복된 계좌명입니다.");
			}
		}
		
		// 계좌비밀번호
		boolean isPasswordMatch = (acPw.equals(acPwCheck)) ? true : false;
		if (isPasswordMatch && acPw.length() == 4) {
			request.setAttribute("acPw", acPw);
			success++;
		} else {
			if (acPw.length() != 4) {
				request.setAttribute("acPwErrer", "4자리 비밀번호를 입력해주세요");
			} else {
				request.setAttribute("acPwwErrer", "비밀번호가 일치하지 않습니다.");
			}
			
		}
		
		// 최초입금액
		long dipositNum = 0;
		if (!diposit.equals("")) {
			dipositNum = Long.parseLong(diposit);
		}
		if (dipositNum >= 1000) {
			success++;
			request.setAttribute("diposit", diposit);
		} else {
			request.setAttribute("dipositErrer", "1000원 이상의 최초 입금액이 필요합니다.");
		}
		
		long regularTransferNum = 0;
		if (!diposit.equals("")) {
			regularTransferNum = Long.parseLong(regularTransfer);
		}
		if (regularTransferNum >= 1000) {
			success++;
			request.setAttribute("regularTransfer", regularTransfer);
		} else {
			request.setAttribute("regularTransferErrer", "1000원 이상부터 가능합니다.");
		}
		
		// 입금계좌번호
		
		// < 데이터베이스 입력 >
		String nextPage = "/bankAccount/CreateDepositAccount.jsp";
		String BANK_CD = "A";
		
		if (success == 5 - 1) {
			vo.setBankCode(BANK_CD);
			vo.setAcNumber(new MakeAccountNumber().make());
			vo.setUserId(uid);
			vo.setAcPassword(acPw);
			vo.setAcName(acName);
			vo.setBalance(dipositNum);
			vo.setRate(5);
			vo.setTransferType("nomal");
			vo.setRegularTransfer(regularTransferNum);
			// 입금계좌번호
			vo.setAcEndDate(new TimeUtil().getNextYear(1));
			dao.insertAccount(vo);
			nextPage = "/refresh.do";
			request.setAttribute("toPage", "/pages/IndexMain.do");
		}
		
		return nextPage;
		
	}
}
