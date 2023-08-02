package kr.ac.kopo.dao;

import java.sql.Connection;
import java.util.List;

import kr.ac.kopo.vo.BankAccountVO;

public interface AccountDAO {
	void addAmount(BankAccountVO vo);
	void addAmount(BankAccountVO vo, Connection conn);
	long withdrawAmount(BankAccountVO vo, Connection conn);
	boolean accountNumberCheck(String acNum);
	String getAccountNameWithAcNum(String acNum);
	long getSavingBalanceWithAcNumber(String acNumber);
	List<BankAccountVO> getAccountListWithEmail(String email);
	BankAccountVO getAccountInfo(String acNum);
	Long getTotalBalanceByUserWithEmail(String email);
}
