package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.UserVO;

public class UserDAO {

	public boolean idChack(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT USER_ID, PASSWORD ");
		sql.append("FROM BANK_USER_INFO ");
		sql.append("WHERE USER_ID = ? ");
		boolean isIdNotExist = true;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				isIdNotExist = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 아이디 비존재 여부 반환
		return isIdNotExist;
	}

	public boolean userCheck(UserVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT USER_ID, PASSWORD ");
		sql.append("FROM BANK_USER_INFO ");
		sql.append("WHERE USER_ID = ? AND PASSWORD = ?");
		boolean isUser = false;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				isUser = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 아이디, 패스워드가 일치하면 true 반환
		return isUser;
	}

	public void userDataInsert(UserVO vo) {
		System.out.println(vo.getBirthDate());
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO BANK_USER_INFO");
		sql.append("(USER_ID, EMAIL, PASSWORD, NAME, BIRTH_DATE, PHONE_NUMBER, ADDR) ");
		sql.append("VALUES(?, ?, ?, ?, to_date(?, 'YYYY-MM-DD'), ?, ?)");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getBirthDate());
			pstmt.setString(6, vo.getPhoneNum());
			pstmt.setString(7, vo.getAddress());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUserEmailWithId(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT EMAIL FROM BANK_USER_INFO ");
		sql.append("WHERE USER_ID = ? ");
		
		String userEmail = "";
		
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				userEmail = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userEmail;
	}
	
	public UserVO getUserData(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM BANK_USER_INFO ");
		sql.append("WHERE USER_ID = ? ");

		UserVO vo = new UserVO();

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setId(rs.getString(1));
				vo.setEmail(rs.getString(2));
				vo.setPassword(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setBirthDate(rs.getString(5));
				vo.setPhoneNum(rs.getString(6));
				vo.setAddress(rs.getString(7));
				vo.setAccountLink(rs.getString(8));
				vo.setAdminRight(rs.getInt(9));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	public void updateUserData(UserVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE BANK_USER_INFO ");
		sql.append("SET EMAIL = ?, ");
		sql.append("PASSWORD = ?, ");
		sql.append("NAME = ?, ");
		sql.append("BIRTH_DATE = TO_DATE(?, 'YYYY-MM-DD'), ");
		sql.append("PHONE_NUMBER = ?, ");
		sql.append("ADDR = ? ");
		sql.append("WHERE USER_ID = ?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getBirthDate());
			pstmt.setString(5, vo.getPhoneNum());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getId());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean adminRightCheck(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ADMIN_RIGHT FROM BANK_USER_INFO ");
		sql.append("WHERE USER_ID = ? ");

		boolean isAdmin = false;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt(1) == 1) {
					isAdmin = true;

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAdmin;
	}
	public String getUidWithKakaoId(String kakaoId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT USER_ID FROM BANK_USER_INFO ");
		sql.append("WHERE ACCOUNT_LINK = ? ");
		
		String uid = "";
		
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, kakaoId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				uid = rs.getString(1);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uid;
	}
	
	
	public List<UserVO> searchUser(UserVO typeAndValueVo) {
		String sType = null;
		switch (typeAndValueVo.getId()) {
		case "userId":
			sType = "USER_ID";
			break;
		case "userName":
			sType = "NAME";
			break;
		case "addr":
			sType = "ADDR";
			break;
		case "phonNum":
			sType = "PHONE_NUMBER";
			break;
		default:
			sType ="USER_ID";
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM BANK_USER_INFO " );
		sql.append("WHERE "+ sType +" LIKE ? ");
		// 유효성 검사된 값만 들어오니 위험하지 않음
		List<UserVO> userList = new ArrayList<>();
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			pstmt.setString(1, typeAndValueVo.getPassword());
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("UserDAO 실행");
			while (rs.next()) {
				UserVO vo = new UserVO();
				vo.setId(rs.getString("USER_ID"));
		        vo.setEmail(rs.getString("EMAIL"));
		        vo.setPassword(rs.getString("PASSWORD"));
		        vo.setName(rs.getString("NAME"));
		        vo.setBirthDate(rs.getString("BIRTH_DATE"));
		        vo.setPhoneNum(rs.getString("PHONE_NUMBER"));
		        vo.setAddress(rs.getString("ADDR"));
		        vo.setAccountLink(rs.getString("ACCOUNT_LINK"));
		        vo.setAdminRight(rs.getInt("ADMIN_RIGHT"));
				userList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	
}
