package dao;

import java.util.ArrayList;

import dto.QnaDTO;
import dto.MemberDTO;
import jdbcObject.JdbcObject;

public class QnaDAO {
	
	
	// Q&A 질문 작성 메서드 
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public int insertQna(QnaDTO qnaDTO) {
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		// qna 테이블에서 제목, 내용, 날짜 INSERT 처리
		String sql = "INSERT INTO qna(qna_title,qna_content,qna_date) VALUES(?,?,now())";
	
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, qnaDTO.getQnaTitle()); 	//qna_title 제목
			JdbcObject.getPreparedStatement().setString(2, qnaDTO.getQnaContent()); //qna_content 내용
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
		}catch (Exception e) {
		
			e.printStackTrace();
		}
		return check;
	}
	
	
	
	// Q&A 질문 수정 메서드
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public int updateQna(QnaDTO qnaDTO, int qnaNo) {
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		// qna 테이블에서 [제목, 내용]을 수정할 수 있는 쿼리
		String sql = "UPDATE qna SET qna_title=?, qna_content=? WHERE qna_no =?";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, qnaDTO.getQnaTitle()); 	//qna_title 제목
			JdbcObject.getPreparedStatement().setString(2, qnaDTO.getQnaContent()); //qna_content 내용
			JdbcObject.getPreparedStatement().setInt(3, qnaNo); //qna_no 번호
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
			
		}catch (Exception e) {
		
			e.printStackTrace();
		}
		return check;
	}
	
	
	
	// Q&A 질문 삭제 메서드
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public int deleteQna(int qnaNo) {
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		// Q&A번호로 하나의 레코드를 삭제하는 쿼리 
		String sql = "DELETE FROM qna WHERE qna_no = ?";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, qnaNo); 	//qna_no 번호
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return check;
	}
	
	
	
	// Q&A 게시판의 모든 레코드 조회 
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public ArrayList<QnaDTO> selectQna() {
		ArrayList<QnaDTO> arrayList = new ArrayList<>();
		
		// 질문 게시판의[Q&A번호, 멤버번호 , 제목, 내용, 날짜] 전체 데이터 조회하는 쿼리
		String sql = "SELECT q.qna_no,q.member_no,q.qna_title, q.qna_content, q.qna_date FROM qna q INNER JOIN member m ON q.member_no = m.member_no";
	
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			while(JdbcObject.getResultSet().next()) {
				QnaDTO qnaDTO = new QnaDTO();
				qnaDTO.setQnaNo(JdbcObject.getResultSet().getInt(1));
				qnaDTO.setMemberNo(JdbcObject.getResultSet().getInt(2));
				qnaDTO.setQnaTitle(JdbcObject.getResultSet().getString(4));
				qnaDTO.setQnaContent(JdbcObject.getResultSet().getString(5));
				qnaDTO.setQnaDate(JdbcObject.getResultSet().getString(6));
				arrayList.add(qnaDTO);
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return arrayList;
	
	}
	
	
	// 페이징 작업
	// Q&A :: qna_no로 총 row 수 구하는 메서드 
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public int count() {
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		String sql = "SELECT count(*) FROM qna";
		
		try {
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return check;
		
		
	}
	
	// 페이징 작업
	// Q&A :: 검색창에 입력한 데이터와 일치하는 레코드 조회 메서드
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public int selectSearchMember() {
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		// searchKey == null 이고,  searchValue == null 일 때 
		// 혹은 전체리스트 일 때 
		String sql = "SELECT q.qna_no,q.member_no,m.member_name,q.qna_title, q.qna_content, q.qna_date FROM qna q INNER JOIN member m ON q.member_no = m.member_no";
		
		// searchKey가 null이 아니고(이름,  searchValue = null 일 때 
		String sql2 = "";
		
		//searchKey과 searchValue이 null이 아닐 때 
		String sql3 = "";
		
		try {
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return check;
		
		
	}
	
	
	// Q&A :: 수정 시 보일 조회 메서드로 한 명의 레코드를 보여준다.
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public int selectQnaForUpdate() {
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		String sql = "SELECT q.qna_no,q.member_no, m.member_name,q.qna_title, q.qna_content, q.qna_date FROM qna q INNER JOIN member m ON q.member_no = m.member_no WHERE qna_no=?";
		
		try {
			
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return check;
		
	}
	
	
	
}
