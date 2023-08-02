package kr.ac.kopo.dao;

import java.sql.Connection;
import java.util.List;

import kr.ac.kopo.vo.BankAccountVO;
import kr.ac.kopo.vo.HistoryVO;

public interface HistoryDAO {
	public void insertRecord(BankAccountVO vo, Connection conn);
	public List<HistoryVO> getAccountHistory(String acNum);
}
