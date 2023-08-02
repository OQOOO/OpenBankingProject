package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BoardVO;

public class BoardDAO {
	
	public void insertBoard(BoardVO vo) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO QUERY_BOARD (POST_ID, USER_ID, TITLE, CONTENTS, VIEWS)  ");
		sql.append("VALUES((SELECT NVL(MAX(POST_ID),0)+1 FROM QUERY_BOARD), ?, ?, ?, 0) ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
//	public void updateBoard(BoardVO vo) {
//		
//		StringBuilder sql = new StringBuilder();
//		sql.append("update user_board set title=?, content=?, updated_at = TO_CHAR(SYSTIMESTAMP, 'YYYY-MM-DD HH24:MI:SS') ");
//		sql.append("where seq = ? ");
//		
//		try (
//				Connection conn = new ConnectionFactory().getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
//				){
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContent());
//			pstmt.setInt(3, vo.getSeq());
//			pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
//	public void addViews(int seq) {
//		
//		StringBuilder sql = new StringBuilder();
//		sql.append("update user_board set views = views + 1 ");
//		sql.append("where seq = ? ");
//		
//		try (
//				Connection conn = new ConnectionFactory().getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
//				){
//			pstmt.setInt(1, seq);
//			pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
//	
	public List<BoardVO> boardView() {
		List<BoardVO> postList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM QUERY_BOARD ORDER BY POST_ID DESC ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setPostId(rs.getInt(1));
				vo.setUserId(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setContent(rs.getString(4));
				
				String time = rs.getString(5);
				time = time.substring(0, time.lastIndexOf("."));
				vo.setCreateTime(time);
				
				vo.setIsNotice(rs.getInt(6));
				vo.setViews(rs.getInt(7));
				postList.add(vo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return postList;
	}
	public BoardVO postView(int postId) {
		StringBuilder sql = new StringBuilder();
		BoardVO vo = new BoardVO();
		sql.append("SELECT * FROM QUERY_BOARD WHERE POST_ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setInt(1, postId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setPostId(rs.getInt(1));
				vo.setUserId(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setCreateTime(rs.getString(5));
				vo.setIsNotice(rs.getInt(6));
				vo.setViews(rs.getInt(7));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	
	public void delPost(int postId) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM QUERY_BOARD WHERE POST_ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setInt(1, postId);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void insertComment(BoardVO vo) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO POST_COMMENT (COMMENT_ID, POST_ID, USER_ID, CONTENTS)  ");
		sql.append("VALUES((SELECT NVL(MAX(COMMENT_ID),0)+1 FROM POST_COMMENT), ?, ?, ?) ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, vo.getPostId());
			pstmt.setString(2, vo.getUserId());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardVO> commentView(int postId) {
		List<BoardVO> postList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM POST_COMMENT ");
		sql.append("WHERE POST_ID = ? ");
		sql.append("ORDER BY COMMENT_ID ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			
			pstmt.setInt(1, postId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setCommentId(rs.getInt(1));
				vo.setPostId(rs.getInt(2));
				vo.setUserId(rs.getString(3));
				vo.setContent(rs.getString(4));
				
				String time = rs.getString(5);
				time = time.substring(0, time.lastIndexOf("."));
				vo.setCreateTime(time);
				postList.add(vo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postList;
	}
	
	public void delComment(int cid) {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE POST_COMMENT WHERE COMMENT_ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setInt(1, cid);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuilder sql2 = new StringBuilder();
		sql2.append("DELETE COMMENT_REPLY WHERE COMMENT_ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql2.toString());
			){
			pstmt.setInt(1, cid);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertReComment(BoardVO vo) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO COMMENT_REPLY (COMMENT_REPLY_ID, COMMENT_ID, USER_ID, CONTENTS, PARENT_RE_ID, DEPTH) ");
		sql.append("VALUES((SELECT NVL(MAX(COMMENT_REPLY_ID),0)+1 FROM COMMENT_REPLY), ?, ?, ?, ?, ?) ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, vo.getCommentId());
			pstmt.setString(2, vo.getUserId());
			pstmt.setString(3, vo.getContent());
			
			
			int pid = vo.getParentReId();
			if (pid == 0) {
				pstmt.setNull(4, java.sql.Types.INTEGER);
			} else {
				pstmt.setInt(4, pid);
			}
			
			pstmt.setInt(5, vo.getDepth());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardVO> reCommentView(int postId) {
		List<BoardVO> reCommentList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT POST_ID, C.COMMENT_ID, CR.COMMENT_REPLY_ID, CR.USER_ID, CR.CONTENTS, CR.CREATE_TIME ");
//		sql.append("FROM POST_COMMENT C ");
//		sql.append("JOIN COMMENT_REPLY CR ON C.COMMENT_ID = CR.COMMENT_ID ");
//		sql.append("WHERE POST_ID = ? ");
		
		sql.append("SELECT POST_ID, C.COMMENT_ID, CR.COMMENT_REPLY_ID, CR.USER_ID, CR.CONTENTS, CR.CREATE_TIME, CR.DEPTH ");
		sql.append("FROM ( ");
		sql.append("    SELECT CR.* ");
		sql.append("	FROM COMMENT_REPLY CR ");
		sql.append("	START WITH CR.PARENT_RE_ID IS NULL ");
		sql.append("	CONNECT BY PRIOR CR.COMMENT_REPLY_ID = CR.PARENT_RE_ID ");
		sql.append("	ORDER SIBLINGS BY CR.COMMENT_REPLY_ID ");
		sql.append("	) CR ");
		sql.append("JOIN POST_COMMENT C ON CR.COMMENT_ID = C.COMMENT_ID ");
		sql.append("WHERE POST_ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			
			pstmt.setInt(1, postId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setPostId(rs.getInt(1));
				vo.setCommentId(rs.getInt(2));
				vo.setReCommentId(rs.getInt(3));
				
				vo.setUserId(rs.getString(4));
				vo.setContent(rs.getString(5));
				
				String time = rs.getString(6);
				time = time.substring(0, time.lastIndexOf("."));
				vo.setCreateTime(time);
				
				int depth = rs.getInt(7);
				depth = depth <= 7 ? depth : 7;
				vo.setDepth(depth);
				
				reCommentList.add(vo);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reCommentList;
	}
	
	public void delReComment(int reId) {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE COMMENT_REPLY WHERE COMMENT_REPLY_ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, reId);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addPostViews(int postId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE QUERY_BOARD SET VIEWS = VIEWS + 1 WHERE POST_ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setInt(1, postId);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePost(BoardVO vo) {
		
		StringBuilder sql = new StringBuilder();
		//sql.append("UPDATE QUERY_BOARD SET VIEWS = VIEWS + 1 WHERE POST_ID = ? ");
		sql.append("UPDATE QUERY_BOARD ");
		sql.append("SET TITLE = ?, CONTENTS = ? ");
		sql.append("WHERE POST_ID = ? ");
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getPostId());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}







































