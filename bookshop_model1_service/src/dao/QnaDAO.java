package dao;

import java.util.ArrayList;

import dto.QnaDTO;
import dto.QnaJoinMemberDTO;
import dto.MemberDTO;
import jdbcObject.JdbcObject;

public class QnaDAO {
	
	
	// Q&A 질문 작성 메서드 
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public int insertQna(QnaDTO qnaDTO, int memberNo) {
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		// qna 테이블에서 제목, 내용, 날짜 INSERT 처리
		String sql = "INSERT INTO qna(member_no,qna_title,qna_content,qna_date) VALUES(?,?,?,now())";
	
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1,memberNo);
			JdbcObject.getPreparedStatement().setString(2, qnaDTO.getQnaTitle()); 	//qna_title 제목
			JdbcObject.getPreparedStatement().setString(3, qnaDTO.getQnaContent()); //qna_content 내용
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
			
			System.out.println(check+"check update");
			
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
		System.out.println(check +"<--dao check");
		return check;
	}
	
	
	
	// Q&A 게시판의 모든 레코드 조회 
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public ArrayList<QnaJoinMemberDTO> selectQna() {
		ArrayList<QnaJoinMemberDTO> arrayList = new ArrayList<>();
		
		// 질문 게시판의[Q&A번호, 멤버번호 , 제목, 내용, 날짜] 전체 데이터 조회하는 쿼리
		String sql = "SELECT q.qna_no,q.member_no,m.member_name,q.qna_title, q.qna_content, q.qna_date FROM qna q INNER JOIN member m ON q.member_no = m.member_no ORDER BY qna_no ASC";
	
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			while(JdbcObject.getResultSet().next()) {
				QnaJoinMemberDTO qnaJoinMemberDTO = new QnaJoinMemberDTO();
				qnaJoinMemberDTO.getQnaDTO().setQnaNo(JdbcObject.getResultSet().getInt(1));
				qnaJoinMemberDTO.getQnaDTO().setMemberNo(JdbcObject.getResultSet().getInt(2));
				qnaJoinMemberDTO.getMemberDTO().setMemberName(JdbcObject.getResultSet().getString(3));
				qnaJoinMemberDTO.getQnaDTO().setQnaTitle(JdbcObject.getResultSet().getString(4));
				qnaJoinMemberDTO.getQnaDTO().setQnaContent(JdbcObject.getResultSet().getString(5));
				qnaJoinMemberDTO.getQnaDTO().setQnaDate(JdbcObject.getResultSet().getString(6));
				arrayList.add(qnaJoinMemberDTO);
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
		int totalRow = 0;
		
		String sql = "SELECT count(*) FROM qna";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			if(JdbcObject.getResultSet().next()) {
				totalRow = JdbcObject.getResultSet().getInt(1);
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return totalRow;
		
		
	}
	
	// 페이징 작업
	// Q&A :: 검색창에 입력한 데이터와 일치하는 레코드 조회 메서드
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public ArrayList<QnaJoinMemberDTO> selectSearchQna(int startRow, int pagePerRow, String searchKey, String searchValue) {
		System.out.println("selectSearchQna 메서드 실행");
		ArrayList<QnaJoinMemberDTO> arrayList = new ArrayList<>();
		
		// searchKey == null 이고,  searchValue == null 일 때 
		// 혹은 전체리스트 일 때 
		String sql = "SELECT q.qna_no,q.member_no,m.member_name,q.qna_title, q.qna_content, q.qna_date FROM qna q INNER JOIN member m ON q.member_no = m.member_no ORDER BY qna_no ASC limit ?,?";
		
		// searchKey(이름) null이 아니고(이름,  searchValue = null 일 때 
		String sql2 = "SELECT q.qna_no,q.member_no,m.member_name,q.qna_title, q.qna_content, q.qna_date FROM qna q INNER JOIN member m ON q.member_no = m.member_no WHERE m.member_name like ? limit ?,?";
		
		//searchKey(아이디)과 searchValue이 null이 아닐 때 
		String sql3 = "SELECT q.qna_no,q.member_no,m.member_name,q.qna_title, q.qna_content, q.qna_date FROM qna q INNER JOIN member m ON q.member_no = m.member_no WHERE m.member_id like ? limit ?,?";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
//			searchKey와 searchValue가 공백일 때 
			if(searchKey.equals("") && searchValue.equals("")) {
				System.out.println("searchKey와 searchValue가 공백");
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
				JdbcObject.getPreparedStatement().setInt(1, startRow); 
				JdbcObject.getPreparedStatement().setInt(2, pagePerRow); 
				
				
//			searchKey가 공백이고  | searchValue가 공백이 아닐 때 
			}else if(searchKey.equals("") && !searchValue.equals("")) {
				System.out.println("searchKey가 공백이고  | searchValue가 공백이 아닐 때  ");
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
				JdbcObject.getPreparedStatement().setInt(1, startRow); 
				JdbcObject.getPreparedStatement().setInt(2, pagePerRow); 
				
				
//			searchKey가 memberName(이름)이고 |searchValue가 공백일 때 
			}else if(searchKey.equals("memberName") && searchValue.equals("")) {
				System.out.println("searchKey가 memberName(이름)이고 |searchValue가 공백일 때 ");
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
				JdbcObject.getPreparedStatement().setInt(1, startRow); 
				JdbcObject.getPreparedStatement().setInt(2, pagePerRow); 
				
				
//			searchKey가 memberName(이름)이고  | searchValue가 공백이 아닐 때 
			}else if(searchKey.equals("memberName") && !searchValue.equals("")) {
				System.out.println("searchKey가 memberName(이름)이고  | searchValue가 공백이 아닐 때");
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql2));
				JdbcObject.getPreparedStatement().setString(1, "%"+searchValue+"%");
				JdbcObject.getPreparedStatement().setInt(2, startRow); 
				JdbcObject.getPreparedStatement().setInt(3, pagePerRow); 
				
			
//			searchKey가 qnaId(아이디)이고  | searchValue가 공백일 때 
			}else if(searchKey.equals("qnaId") && searchValue.equals("")) {
				System.out.println("searchKey가 qnaId(아이디)이고  | searchValue가 공백");
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
				JdbcObject.getPreparedStatement().setInt(1, startRow); 
				JdbcObject.getPreparedStatement().setInt(2, pagePerRow); 
				
			
//			searchKey가 qnaId(아이디)이고  | searchValue가 공백이 아닐 때 
			}else if(searchKey.equals("qnaId") && !searchValue.equals("")) {
				System.out.println("searchKey가 qnaId(아이디)이고  | searchValue가 공백");
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql3));
				JdbcObject.getPreparedStatement().setString(1, "%"+searchValue+"%");
				JdbcObject.getPreparedStatement().setInt(2, startRow); 
				JdbcObject.getPreparedStatement().setInt(3, pagePerRow); 
				
			}
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());

			
			while(JdbcObject.getResultSet().next()) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMemberName(JdbcObject.getResultSet().getString("member_name"));
				
				QnaDTO qnaDTO = new QnaDTO();
				qnaDTO.setQnaNo(JdbcObject.getResultSet().getInt("qna_no"));
				qnaDTO.setMemberNo(JdbcObject.getResultSet().getInt("member_no"));
				qnaDTO.setQnaTitle(JdbcObject.getResultSet().getString("qna_title"));
				qnaDTO.setQnaContent(JdbcObject.getResultSet().getString("qna_content"));
				qnaDTO.setQnaDate(JdbcObject.getResultSet().getString("qna_date"));
				
				
				QnaJoinMemberDTO qnaJoinMemberDTO = new QnaJoinMemberDTO();
				qnaJoinMemberDTO.setQnaDTO(qnaDTO);
				qnaJoinMemberDTO.setMemberDTO(memberDTO);
				arrayList.add(qnaJoinMemberDTO);
				
			
			}
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return arrayList;
		
		
	}
	
	
	// Q&A :: 수정 시 보일 조회 메서드로 한 명의 레코드를 보여준다.
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public QnaJoinMemberDTO selectQnaForUpdate(int qnaNo) {
		QnaJoinMemberDTO qnaJoinMemberDTO = new QnaJoinMemberDTO();

		String sql = "SELECT q.qna_no,q.member_no, m.member_name,q.qna_title, q.qna_content, q.qna_date FROM qna q INNER JOIN member m ON q.member_no = m.member_no WHERE qna_no=?";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, qnaNo); 	//qna_no 번호
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			

			//아이디 중복 X 0, 아이디 중복O 1
			if(JdbcObject.getResultSet().next()) {
				// ResultSet에 나온 리턴값을 멤버 DTO에서 이름을 셋팅 
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMemberName(JdbcObject.getResultSet().getString("member_name"));
				
				// ResultSet에 나온 리턴값을 질문 DTO에서 셋팅 
				QnaDTO qnaDTO = new QnaDTO();				
				qnaDTO.setQnaNo(JdbcObject.getResultSet().getInt("qna_no"));
				qnaDTO.setMemberNo(JdbcObject.getResultSet().getInt("member_no"));
				qnaDTO.setQnaTitle(JdbcObject.getResultSet().getString("qna_title"));
				qnaDTO.setQnaContent(JdbcObject.getResultSet().getString("qna_content"));
				qnaDTO.setQnaDate(JdbcObject.getResultSet().getString("qna_date"));
				
				//QNA와 MEMBER를 조인한 변수에  DTO객체의 주소값을 대입
				qnaJoinMemberDTO.setQnaDTO(qnaDTO);
				qnaJoinMemberDTO.setMemberDTO(memberDTO);
				
				
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return qnaJoinMemberDTO;
		
	}
	
	
	
}
