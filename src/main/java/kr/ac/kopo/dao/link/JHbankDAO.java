package kr.ac.kopo.dao.link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.dao.AccountDAO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BankAccountVO;

public class JHbankDAO implements AccountDAO {
	@Override
	public boolean accountNumberCheck(String acNum) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void addAmount(BankAccountVO vo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addAmount(BankAccountVO vo, Connection conn) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SAVING_ACCOUNT @JH ");
		sql.append("SET BALANCE = BALANCE + ? ");
		sql.append("WHERE AC_NUMBER = ? ");
		
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
	public List<BankAccountVO> getAccountListWithEmail(String email) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM SAVING_ACCOUNT @JH ");
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

		// 계좌 배열 반환
		return accountList;
	}
	@Override
	public String getAccountNameWithAcNum(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT AC_NAME ");
		sql.append("FROM SAVING_ACCOUNT @JH ");
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
		
		// 계좌 이름 반환
		return acName;
	}
	@Override
	public long getSavingBalanceWithAcNumber(String acNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT BALANCE ");
		sql.append("FROM SAVING_ACCOUNT @JH ");
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
	
	@Override
	public BankAccountVO getAccountInfo(String acNum) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM SAVING_ACCOUNT @JH ");
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

		return vo;
	}
	@Override
	public long withdrawAmount(BankAccountVO vo, Connection conn) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SAVING_ACCOUNT @JH ");
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
	
	@Override
	public Long getTotalBalanceByUserWithEmail(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(BALANCE) ");
		sql.append("FROM SAVING_ACCOUNT @JH ");
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
