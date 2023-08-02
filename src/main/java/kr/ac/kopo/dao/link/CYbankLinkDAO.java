package kr.ac.kopo.dao.link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.dao.AccountDAO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BankAccountVO;

public class CYbankLinkDAO implements AccountDAO {
	
	/*
	 CREATE DATABASE LINK CY_BANK_LINK
	 CONNECT TO hr
	 IDENTIFIED BY hr
	 USING
	'(DESCRIPTION =
	      (ADDRESS = (PROTOCOL = TCP)(HOST = 172.31.9.185)(PORT = 1521))
	      (CONNECT_DATA =
	        (SERVER = DEDICATED)
	        (SERVICE_NAME = xe))
	)';
	 */
	
	@Override
	public void addAmount(BankAccountVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ACCOUNT@CY_BANK_LINK ");
		sql.append("SET BALANCE = BALANCE + ? ");
		sql.append("WHERE ACCOUNTNUM = ? ");
		
		System.out.println("addamount실행");
		System.out.println(vo.getBalance());
		System.out.println(vo.getAcNumber());
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setLong(1, vo.getBalance());
			pstmt.setString(2, vo.getAcNumber());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addAmount(BankAccountVO vo, Connection conn) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ACCOUNT@CY_BANK_LINK ");
		sql.append("SET BALANCE = BALANCE + ? ");
		sql.append("WHERE ACCOUNTNUM = ? ");
		
		System.out.println("addamount실행");
		System.out.println(vo.getBalance());
		System.out.println(vo.getAcNumber());
		try (
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setLong(1, vo.getBalance());
			pstmt.setString(2, vo.getAcNumber());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean accountNumberCheck(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ACCOUNTNUM ");
		sql.append("FROM ACCOUNT@CY_BANK_LINK ");
		sql.append("WHERE ACCOUNTNUM = ? ");
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
	
	@Override
	public String getAccountNameWithAcNum(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ACCOUNTNAME ");
		sql.append("FROM ACCOUNT@CY_BANK_LINK ");
		sql.append("WHERE ACCOUNTNUM = ? ");
		
		String acName = "";
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, acNum);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				acName = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 계좌 이름 반환
		return acName;
	}
	
	@Override
	public long getSavingBalanceWithAcNumber(String acNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT BALANCE ");
		sql.append("FROM ACCOUNT @CY_BANK_LINK ");
		sql.append("WHERE ACCOUNTNUM = ? ");
		long balance = 0;
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			pstmt.setString(1, acNumber);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				balance = rs.getLong(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return balance;
	}
	
	
	
	@Override
	public List<BankAccountVO> getAccountListWithEmail(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM ACCOUNT @CY_BANK_LINK ");
		sql.append("WHERE EMAIL = ? ");
		
		List<BankAccountVO> accountList = new ArrayList<>();

		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BankAccountVO vo = new BankAccountVO();
				
				// 1.accoountNUm
				// 2.userId
				// 3.type
				// 4.accountName
				// 5.accountPassword
				
				// 6.balance
				// 7. makeDate
				// 8. email
				
				//vo.setBankCode(rs.getString(1));
				vo.setAcNumber(rs.getString(1));
				vo.setUserId(rs.getString(2));
				// acType (3)
				vo.setAcName(rs.getString(4));
				vo.setAcPassword(rs.getString(5));
				vo.setBalance(rs.getLong(6));
				vo.setAcOpenDate(rs.getString(7));
				
				//vo.setRate(rs.getInt(7));
				//vo.setUserMemo(rs.getString(9));
				//vo.setState(rs.getInt(10));
				
				accountList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 계좌 배열 반환
		return accountList;
	}
	
	@Override
	public BankAccountVO getAccountInfo(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM ACCOUNT @CY_BANK_LINK ");
		sql.append("WHERE ACCOUNTNUM = ? ");
		
		BankAccountVO vo = new BankAccountVO();
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, acNum);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//vo.setBankCode(rs.getString(1));
				vo.setAcNumber(rs.getString(1));
				vo.setUserId(rs.getString(2));
				// acType (3)
				vo.setAcName(rs.getString(4));
				vo.setAcPassword(rs.getString(5));
				vo.setBalance(rs.getLong(6));
				vo.setAcOpenDate(rs.getString(7));
				
				//vo.setRate(rs.getInt(7));
				//vo.setUserMemo(rs.getString(9));
				//vo.setState(rs.getInt(10));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 계좌 배열 반환
		return vo;
	}
	@Override
	public long withdrawAmount(BankAccountVO vo, Connection conn) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ACCOUNT @CY_BANK_LINK ");
		sql.append("SET BALANCE = BALANCE - ? ");
		sql.append("WHERE ACCOUNTNUM = ? ");
		
		try (
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setLong(1, vo.getBalance());
			pstmt.setString(2, vo.getAcNumber());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo.getBalance();
	}
	
	@Override
	public Long getTotalBalanceByUserWithEmail(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(BALANCE) ");
		sql.append("FROM ACCOUNT @CY_BANK_LINK ");
		sql.append("WHERE EMAIL = ? ");
		long totalBelance = 0;
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				totalBelance = rs.getLong(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return totalBelance;
	}
}


























