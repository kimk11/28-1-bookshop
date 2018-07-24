package dao;


import java.util.ArrayList;
import dto.QnaCommentDTO;
import jdbcObject.JdbcObject;

public class QnaCommentDAO {
	

	
	//	Q&A 댓글 입력 메서드 
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public int insertQnaComment(QnaCommentDTO qnaCommentDTO, int adminNo) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		//qna_comment 테이블에서  댓글을 입력하면 [게시글 번호, 관리자 번호, 내용, 날짜] INSERT 되는 쿼리 
		String sql = "INSERT INTO qna_comment(qna_no,admin_no,comment_content,comment_date) VALUES(?,?,?,now())";
				
		try {
			JdbcObject.setConnection(JdbcObject.getConnection());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, qnaCommentDTO.getQnaNo());
			JdbcObject.getPreparedStatement().setInt(2, adminNo);	//세션
			JdbcObject.getPreparedStatement().setString(3, qnaCommentDTO.getCommentContent());
			check = JdbcObject.getPreparedStatement().executeUpdate();
		}catch (Exception e) {
		
			e.printStackTrace();
		}
		return check;
	}
	
	
	
	
	// Q&A 댓글 삭제 메서드 
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public int deleteQnaComment(int qnaCommentNo) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		// 댓글 순차 번호로 하나의 레코드 삭제 
		String sql = "DELETE FROM qna_comment WHERE qna_comment_no =?";
				
		try {
			JdbcObject.setConnection(JdbcObject.getConnection());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, qnaCommentNo);
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
		}catch (Exception e) {
		
			e.printStackTrace();
		}
		return check;
	}
	
	
	// Q&A 게시글 번호에 대한 댓글 리스트 조회 
	public QnaCommentDTO selectQnaComment(int qnaNo) {
		QnaCommentDTO qnaCommentDTO = new QnaCommentDTO();
		
		// 한 게시글에 달린 모든 댓글 조회 
		String sql = "SELECT qna_comment_no,qna_no,admin_no,comment_content,comment_date FROM qna_comment WHERE qna_no ='1'";
				
		try {
			JdbcObject.setConnection(JdbcObject.getConnection());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, qnaNo);
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			while(JdbcObject.getResultSet().next()) {
				qnaCommentDTO.setQnaCommentNo(JdbcObject.getResultSet().getInt("qna_comment_no"));
				qnaCommentDTO.setQnaNo(JdbcObject.getResultSet().getInt("qna_no"));
				qnaCommentDTO.setAdminNo(JdbcObject.getResultSet().getInt("admin_no"));
				qnaCommentDTO.setCommentContent(JdbcObject.getResultSet().getString("comment_content"));
				qnaCommentDTO.setCommentDate(JdbcObject.getResultSet().getString("comment_date"));
			
			}
			
		}catch (Exception e) {
		
			e.printStackTrace();
		}
		return qnaCommentDTO;
	}
	
	
	
}
