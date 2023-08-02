package kr.ac.kopo.dao.link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.dao.AccountDAO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BankAccountVO;

public class JOYbankDAO implements AccountDAO {

	/*
	 CREATE DATABASE LINK JOY_BANK
	 CONNECT TO hr
	 IDENTIFIED BY hr
	 USING
	'(DESCRIPTION =
	      (ADDRESS = (PROTOCOL = TCP)(HOST = 172.31.9.176)(PORT = 1521))
	      (CONNECT_DATA =
	        (SERVER = DEDICATED)
	        (SERVICE_NAME = xe))
	)'; 
	 */
	
	@Override
	public void addAmount(BankAccountVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SAVING_ACCOUNT@JOY_BANK ");
		sql.append("SET ACC_BAL = ACC_BAL + ? ");
		sql.append("WHERE ACC_NO = ? ");
		
		System.out.println("addamount����");
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
		sql.append("UPDATE SAVING_ACCOUNT@JOY_BANK ");
		sql.append("SET ACC_BAL = ACC_BAL + ? ");
		sql.append("WHERE ACC_NO = ? ");
		
		System.out.println("addamount����");
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
		sql.append("SELECT ACC_NO ");
		sql.append("FROM SAVING_ACCOUNT@JOY_BANK ");
		sql.append("WHERE ACC_NO = ? ");
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
		
		// ���� ���¹�ȣ ������ true, �ƴϸ� false
		return isAcNumberNotExist;
	}
	
	@Override
	public String getAccountNameWithAcNum(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ACC_NM ");
		sql.append("FROM SAVING_ACCOUNT@JOY_BANK ");
		sql.append("WHERE ACC_NO = ? ");
		
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
		
		// ���� �̸� ��ȯ
		return acName;
	}
	
	@Override
	public long getSavingBalanceWithAcNumber(String acNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ACC_BAL ");
		sql.append("FROM SAVING_ACCOUNT @JOY_BANK ");
		sql.append("WHERE ACC_NO = ? ");
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
		System.out.println("joyBankDAO: �۵�����" + email);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM SAVING_ACCOUNT @JOY_BANK ");
		sql.append("WHERE U_EMAIL = ? ");
		
		List<BankAccountVO> accountList = new ArrayList<>();

		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BankAccountVO vo = new BankAccountVO();
				vo.setBankCode(rs.getString(1));
				vo.setAcNumber(rs.getString(2));
				vo.setUserId(rs.getString(3));
				vo.setAcPassword(rs.getString(4));
				vo.setAcName(rs.getString(5));
				vo.setBalance(rs.getLong(6));
				vo.setAcOpenDate(rs.getString(7));//
				vo.setRate(rs.getInt(8));//
				vo.setUserMemo(rs.getString(9));
				//vo.setState(rs.getInt(10));
				
				
				accountList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(accountList);
		// ���� �迭 ��ȯ
		return accountList;
	}
	
	@Override
	public BankAccountVO getAccountInfo(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM SAVING_ACCOUNT @JOY_BANK ");
		sql.append("WHERE ACC_NO = ? ");
		
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
				vo.setAcOpenDate(rs.getString(7));
				vo.setRate(rs.getInt(8));
				vo.setUserMemo(rs.getString(9));
				//vo.setState(rs.getInt(10));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// ���� �迭 ��ȯ
		return vo;
	}
	@Override
	public long withdrawAmount(BankAccountVO vo, Connection conn) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SAVING_ACCOUNT @JOY_BANK ");
		sql.append("SET ACC_BAL = ACC_BAL - ? ");
		sql.append("WHERE ACC_NO = ? ");
		
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
		sql.append("SELECT SUM(ACC_BAL) ");
		sql.append("FROM SAVING_ACCOUNT @JOY_BANK ");
		sql.append("WHERE U_EMAIL = ? ");
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
