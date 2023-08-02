package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BankAccountVO;

public class DepositAccountDAO {

	public boolean accountNameCheck(BankAccountVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT USER_ID, AC_PW ");
		sql.append("FROM DEPOSIT_ACCOUNT ");
		sql.append("WHERE USER_ID = ? AND AC_NAME = ? ");
		boolean isNameNotExist = true;
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getAcName());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isNameNotExist = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 해당 유저에 같은 계좌명 없으면 true, 아니면 false
		return isNameNotExist;
	}
	
	public boolean accountNumberCheck(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT AC_NUMBER ");
		sql.append("FROM DEPOSIT_ACCOUNT ");
		sql.append("WHERE AC_NUMBER = ? ");
		boolean isAcNumberNotExist = true;
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, acNum);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isAcNumberNotExist = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 같은 계좌번호 없으면 true, 아니면 false
		return isAcNumberNotExist;
	}
	
	public boolean insertAccount(BankAccountVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into DEPOSIT_ACCOUNT (BANK_CD, AC_NUMBER, USER_ID, AC_PW, AC_NAME, BALANCE, RATE, TR_TYPE, TR_PAYMENT, AC_ED_DATE) ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD')) ");
		boolean isNameNotExist = true;
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, vo.getBankCode());
			pstmt.setString(2, vo.getAcNumber());
			pstmt.setString(3, vo.getUserId());
			pstmt.setString(4, vo.getAcPassword());
			pstmt.setString(5, vo.getAcName());
			pstmt.setLong(6, vo.getBalance());
			pstmt.setInt(7, vo.getRate());
			pstmt.setString(8, vo.getTransferType());
			pstmt.setLong(9, vo.getRegularTransfer());
			pstmt.setString(10, vo.getAcEndDate());
			// 입금계좌 추가해야함
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isNameNotExist = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 해당 유저에 같은 계좌명 없으면 true, 아니면 false
		return isNameNotExist;
	}
	
	// 해당 유저의 모든 잔액 반환
	public Long getTotalBalanceByUser(String uid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(BALANCE) ");
		sql.append("FROM DEPOSIT_ACCOUNT ");
		sql.append("WHERE USER_ID = ? ");
		long totalBelance = 0;
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, uid);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				totalBelance = rs.getLong(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 해당 유저에 같은 계좌명 없으면 true, 아니면 false
		return totalBelance;
	}
	
	
	public List<BankAccountVO> getAccountList(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM DEPOSIT_ACCOUNT ");
		sql.append("WHERE USER_ID = ? ");
		
		List<BankAccountVO> accountList = new ArrayList<>();
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, acNum);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BankAccountVO vo = new BankAccountVO();
				vo.setBankCode(rs.getString(1));
				vo.setAcNumber(rs.getString(2));
				vo.setUserId(rs.getString(3));
				vo.setAcPassword(rs.getString(4));
				vo.setAcName(rs.getString(5));
				vo.setBalance(rs.getLong(6));
				vo.setRate(rs.getInt(7));
				vo.setAcOpenDate(rs.getString(8));
				vo.setAcEndDate(rs.getString(9));
				vo.setTransferAcNumber(rs.getString(10));
				vo.setTransferType(rs.getString(11));
				vo.setRegularTransfer(rs.getLong(12));
				vo.setUserMemo(rs.getString(13));
				accountList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 계좌 배열 반환
		return accountList;
	}
	public BankAccountVO getAccountInfo(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM DEPOSIT_ACCOUNT ");
		sql.append("WHERE AC_NUMBER = ? ");
		
		BankAccountVO vo = new BankAccountVO();
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, acNum);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vo.setBankCode(rs.getString(1));
				vo.setAcNumber(rs.getString(2));
				vo.setUserId(rs.getString(3));
				vo.setAcPassword(rs.getString(4));
				vo.setAcName(rs.getString(5));
				vo.setBalance(rs.getLong(6));
				vo.setRate(rs.getInt(7));
				vo.setAcOpenDate(rs.getString(8));
				vo.setAcEndDate(rs.getString(9));
				vo.setTransferAcNumber(rs.getString(10));
				vo.setTransferType(rs.getString(11));
				vo.setRegularTransfer(rs.getLong(12));
				vo.setUserMemo(rs.getString(13));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 계좌 배열 반환
		return vo;
	}
	public void delDepositAccount(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM DEPOSIT_ACCOUNT ");
		sql.append("WHERE AC_NUMBER = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, acNum);
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}


