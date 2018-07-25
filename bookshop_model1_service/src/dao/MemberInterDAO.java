package dao;

import java.util.ArrayList;
import dto.BookCodeDTO;
import jdbcObject.JdbcObject;

public class MemberInterDAO {
	
	public ArrayList<BookCodeDTO> selectBookCode(){
		
		ArrayList<BookCodeDTO> arrayBookCodeDto = new ArrayList<BookCodeDTO>();

		String sql = "SELECT bookcode_no, bookcode_name FROM bookcode";

		try {
			
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			while(JdbcObject.getResultSet().next()) {
				
				BookCodeDTO bookCodeDto = new BookCodeDTO();
				
				bookCodeDto.setBookCodeNo(JdbcObject.getResultSet().getInt("bookcode_no"));
				bookCodeDto.setBookCodeName(JdbcObject.getResultSet().getString("bookcode_name"));
				
				arrayBookCodeDto.add(bookCodeDto);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return arrayBookCodeDto;
	}
	
	public int selectMemberNo(String memberId) {
		
		int memberNo = 0;
		
		String sql = "SELECT member_no FROM member WHERE member_id = ?";
		
		try {
			
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, memberId);
			
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			if(JdbcObject.getResultSet().next()) {
				
				memberNo = JdbcObject.getResultSet().getInt("member_no");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return memberNo;
	}
	
	//회원가입 시 회원의 관심 카테고리를 입력하는 메서드
	//리턴값 0 -> 쿼리 실행 실패, 리턴값 > 0 -> 쿼리 실행 성공
	public int insertMemberInter(int memberNo, int[] bookCodeNo) {
		
		int check = 0;
		
		String sql = "INSERT INTO memberinter (member_no, bookcode_no) VALUES(?, ?)";
		
		try {
			
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			for(int i = 0; i < bookCodeNo.length; i++) {
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
				JdbcObject.getPreparedStatement().setInt(1, memberNo);
				JdbcObject.getPreparedStatement().setInt(2, bookCodeNo[i]);
				
				JdbcObject.getPreparedStatement().executeUpdate();
				check++;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return check;
	}
	
	//회원의 관심 카테고리 삭제하는 메서드
	//리턴값 0 -> 쿼리 실행 실패, 리턴값 > 0 -> 쿼리 실행 성공
	public int deleteMemberInter(int memberNo) {
		
		int check = 0;
		
		String sql = "DELETE FROM memberinter WHERE member_no = ?";
		
		try {
			
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, memberNo);
			
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return check;
	}
}