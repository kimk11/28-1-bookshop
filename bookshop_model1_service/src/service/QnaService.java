package service;


import java.util.ArrayList;

import dao.MemberDAO;
import dao.QnaDAO;
import dto.QnaDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class QnaService {
	private QnaDAO qnaDAO;
	public QnaService() {
		this.qnaDAO = new QnaDAO();
	}
	
	// Q&A 질문 작성 메서드 
	// check 리턴값  0 : 실패 , 1: 성공
		public int insertQnaService(QnaDTO qnaDTO) {
			// 리턴 결과를 담을 변수
			int check = 0;
			
			try {
				qnaDAO.insertQna(qnaDTO);
				
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
	
	
	
	// Q&A 질문삭세 메서드
	// check 리턴값  0 : 실패 , 1: 성공
		public int deleteQnaService(int qnaNo) {
			// 리턴 결과를 담을 변수
			int check = 0;
			
			try {
				qnaDAO.deleteQna(qnaNo);
				
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
		
		
		
		// Q&A :: 수정 시 보일 조회 메서드로 한 명의 레코드를 보여준다.
		// check 리턴값  0 : 실패 , 1: 성공
			public QnaDTO selectQnaForUpdateService(int qnaNo) {
				QnaDTO qnaDTO = new QnaDTO();
				try {
					
					qnaDTO = qnaDAO.selectQnaForUpdate(qnaNo);
					
					if(qnaDTO.getQnaTitle() != null) {
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
				return qnaDTO;
				
			}
		
		
		
		// Q&A 질문 수정 메서드
		public int updateQnaService(QnaDTO qnaDTO, int qnaNo) {
			// 리턴 결과를 담을 변수
			int check = 0;
			
			try {
				qnaDAO.updateQna(qnaDTO, qnaNo);
				
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
		
		
		
		// selectSearchQna
		// check 리턴값  0 : 실패 , 1: 성공
		public ArrayList<QnaDTO> selectSearchQnaService(int startRow, int pagePerRow, String searchKey, String searchValue) {
			ArrayList<QnaDTO> list = new ArrayList<>();
			try {
				
				list = qnaDAO.selectSearchQna(startRow, pagePerRow, searchKey, searchValue);
				
				if(list.get(0) != null) {
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
			return list;
			
		}
}
