package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BankAccountVO;
import kr.ac.kopo.vo.HistoryVO;

public class TransactionHistoryDAO implements HistoryDAO {

	public void insertRecord(BankAccountVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TRANSACTION_HISTORY (TR_NUMBER, AC_NUMBER, TR_AC_NUMBER, TR_NAME, AMOUNT) ");
		sql.append("VALUES((SELECT MAX(TR_NUMBER) + 1 FROM TRANSACTION_HISTORY) ");
		sql.append(", ?, ?, ?, ?)");
		
		System.out.println(vo.getBalance());
		System.out.println(vo.getAcNumber());
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, vo.getAcNumber());
			pstmt.setString(2, vo.getTransferAcNumber());
			pstmt.setString(3, vo.getAcName());
			pstmt.setLong(4, vo.getBalance());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertRecord(BankAccountVO vo, Connection conn) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TRANSACTION_HISTORY (TR_NUMBER, AC_NUMBER, TR_AC_NUMBER, TR_NAME, AMOUNT) ");
		sql.append("VALUES((SELECT MAX(TR_NUMBER) + 1 FROM TRANSACTION_HISTORY) ");
		sql.append(", ?, ?, ?, ?)");
		
		System.out.println(vo.getBalance());
		System.out.println(vo.getAcNumber());
		try (
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, vo.getAcNumber());
			pstmt.setString(2, vo.getTransferAcNumber());
			pstmt.setString(3, vo.getAcName());
			pstmt.setLong(4, vo.getBalance());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<HistoryVO> getAccountHistory(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM TRANSACTION_HISTORY ");
		sql.append("WHERE AC_NUMBER = ? ");
		sql.append("ORDER BY TR_TIME DESC ");
		
		List<HistoryVO> hList = new ArrayList<>();
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, acNum);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				HistoryVO vo = new HistoryVO();
				
				vo.setTrNum(rs.getInt(1));
				vo.setAcNumber(rs.getString(2));
				vo.setTrAcNumber(rs.getString(3));
				vo.setTrName(rs.getString(4));
				vo.setAmount(rs.getLong(5));
				vo.setTrTime(rs.getString(6));
				
				hList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hList;
	}
}















