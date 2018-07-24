package service;

import java.util.ArrayList;

import dao.QnaCommentDAO;
import dto.QnaCommentDTO;
import dto.QnaDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class QnaCommentService {
	private QnaCommentDAO qnaCommentDAO;
	public QnaCommentService() {
		this.qnaCommentDAO = new QnaCommentDAO();
	}
	
	
	//	Q&A 댓글 입력 메서드
	// check 리턴값  0 : 실패 , 1: 성공
	public int insertQnaCommentService(QnaCommentDTO qnaCommentDTO, int adminNo) { 
		// 리턴 결과를 담을 변수
		int check = 0;
		
		try {
			qnaCommentDAO.insertQnaComment(qnaCommentDTO, adminNo);
			
			if(1 == check) {
				check = 1;	// 리턴값을 담을 변수에 1을 대입
				JdbcObject.getConnection().commit(); // Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
			}else {
				// Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcObject.getConnection().rollback();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		System.out.println(check + "<--service메서드 처리 성공 여부");
		return check;
		
	}
	
	

	// Q&A 댓글 삭제 메서드 
	// check 리턴값  0 : 실패 , 1: 성공
	public int deleteQnaCommentService(int qnaCommentNo) {
		// 리턴 결과를 담을 변수
		int check = 0;
		
		try {
			qnaCommentDAO.deleteQnaComment(qnaCommentNo);
			
			if(1 == check) {
				check = 1;	// 리턴값을 담을 변수에 1을 대입
				JdbcObject.getConnection().commit(); // Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
			}else {
				// Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcObject.getConnection().rollback();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		System.out.println(check + "<--service메서드 처리 성공 여부");
		return check;
		
	}
	
	
	
	
	
	//Q&A 게시글 번호에 대한 댓글 리스트 조회 
	// check 리턴값  0 : 실패 , 1: 성공
	public QnaCommentDTO selectQnaCommentService(int qnaNo) {
		QnaCommentDTO qnaCommentDTO = new QnaCommentDTO();
		try {
			
			qnaCommentDTO = qnaCommentDAO.selectQnaComment(qnaNo);
			
			if(qnaCommentDTO.getCommentContent() != null) {		// dto 안의 내용이 있다면 
				JdbcObject.getConnection().commit(); // Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
			}else {
				// Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcObject.getConnection().rollback();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		return qnaCommentDTO;
		
	}
	
}
