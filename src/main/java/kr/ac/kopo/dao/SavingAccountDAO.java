package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BankAccountVO;

public class SavingAccountDAO implements AccountDAO {
	
	public boolean accountNameCheck(BankAccountVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT USER_ID, AC_PW ");
		sql.append("FROM SAVING_ACCOUNT ");
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
		
		// �ش� ������ ���� ���¸� ������ true, �ƴϸ� false
		return isNameNotExist;
	}
	
	@Override
	public boolean accountNumberCheck(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT AC_NUMBER ");
		sql.append("FROM SAVING_ACCOUNT ");
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
		
		// ���� ���¹�ȣ ������ true, �ƴϸ� false
		return isAcNumberNotExist;
	}
	
	public long balanceCheck(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT BALANCE ");
		sql.append("FROM SAVING_ACCOUNT ");
		sql.append("WHERE AC_NUMBER = ? ");
		long balance = 0;
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, acNum);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				balance = rs.getLong(1);
			}
			System.out.println("dao : " + balance);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// �ش� ���¿� ���� �ܾ� ���
		return balance;
	}
	
	public void insertAccount(BankAccountVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into SAVING_ACCOUNT (BANK_CD, AC_NUMBER, USER_ID, AC_PW, AC_NAME, BALANCE, RATE, STATE, EMAIL) ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, 1, ?) ");
		
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
			pstmt.setString(8, vo.getUserMemo());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// �ش� ������ ��� �ܾ� ��ȯ
	public Long getTotalBalanceByUser(String uid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(BALANCE) ");
		sql.append("FROM SAVING_ACCOUNT ");
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
		

		return totalBelance;
	}
	@Override
	public Long getTotalBalanceByUserWithEmail(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(BALANCE) ");
		sql.append("FROM SAVING_ACCOUNT ");
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
	
	// �ش� ���̵��� ��������Ʈ ��ȯ
	public List<BankAccountVO> getAccountList(String uid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM SAVING_ACCOUNT ");
		sql.append("WHERE USER_ID = ? ");
		
		List<BankAccountVO> accountList = new ArrayList<>();

		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, uid);

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
				vo.setUserMemo(rs.getString(9));
				vo.setState(rs.getInt(10));
				
				accountList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// ���� �迭 ��ȯ
		return accountList;
	}
	
	@Override
	public List<BankAccountVO> getAccountListWithEmail(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM SAVING_ACCOUNT ");
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
				vo.setBankCode(rs.getString(1));
				vo.setAcNumber(rs.getString(2));
				vo.setUserId(rs.getString(3));
				vo.setAcPassword(rs.getString(4));
				vo.setAcName(rs.getString(5));
				vo.setBalance(rs.getLong(6));
				vo.setRate(rs.getInt(7));
				vo.setAcOpenDate(rs.getString(8));
				vo.setUserMemo(rs.getString(9));
				vo.setState(rs.getInt(10));
				
				accountList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// ���� �迭 ��ȯ
		return accountList;
	}
	
	@Override
	public BankAccountVO getAccountInfo(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM SAVING_ACCOUNT ");
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
				vo.setUserMemo(rs.getString(9));
				vo.setState(rs.getInt(10));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// ���� �迭 ��ȯ
		return vo;
	}
	
	@Override
	public String getAccountNameWithAcNum(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT AC_NAME ");
		sql.append("FROM SAVING_ACCOUNT ");
		sql.append("WHERE AC_NUMBER = ? ");
		
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
	
	// ���
	public long withdrawAmount(BankAccountVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SAVING_ACCOUNT ");
		sql.append("SET BALANCE = BALANCE - ? ");
		sql.append("WHERE AC_NUMBER = ? ");
		
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
		return vo.getBalance();
	}
	
	@Override
	public long withdrawAmount(BankAccountVO vo, Connection conn) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SAVING_ACCOUNT ");
		sql.append("SET BALANCE = BALANCE - ? ");
		sql.append("WHERE AC_NUMBER = ? ");
		
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
	
	// �Ա�
	@Override
	public void addAmount(BankAccountVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SAVING_ACCOUNT ");
		sql.append("SET BALANCE = BALANCE + ? ");
		sql.append("WHERE AC_NUMBER = ? ");
		
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
		sql.append("UPDATE SAVING_ACCOUNT ");
		sql.append("SET BALANCE = BALANCE + ? ");
		sql.append("WHERE AC_NUMBER = ? ");
		
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
	
	public BankAccountVO accountDataUpdate(BankAccountVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SAVING_ACCOUNT ");
		sql.append("SET AC_NAME = ? ");
		sql.append(", AC_PW = ? ");
		sql.append("WHERE AC_NUMBER = ? ");

		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			pstmt.setString(1, vo.getAcName());
			pstmt.setString(2, vo.getAcPassword());
			pstmt.setString(3, vo.getAcNumber());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}
	
	public void delSavingAccount(String acNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE SAVING_ACCOUNT WHERE AC_NUMBER = ? ");
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			pstmt.setString(1, acNumber);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public long getSavingBalanceWithAcNumber(String acNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT BALANCE ");
		sql.append("FROM SAVING_ACCOUNT ");
		sql.append("WHERE AC_NUMBER = ? ");
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
	
}

































