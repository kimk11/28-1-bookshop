//28기 김진우
//2018/07/24
package dao;

import java.util.ArrayList;
import dto.BookDTO;
import dto.BookJoinCartDTO;
import dto.ShoppingCartDTO;
import jdbcObject.JdbcObject;

public class ShoppingCartDAO {
	
	//선택 물품 장바구니에 추가 메서드
	//리턴값 true, false로 메서드 성공 확인
	public boolean insertCart(ShoppingCartDTO shoppingCartDTO) {
		
		//데이터 추가 성공 여부 변수
		int check = 0;
		
		String sql = "INSERT INTO shoppingcart(book_no,member_no,shoppingcart_amount,shoppingcart_price,shoppingcart_date) VALUES(?,?,?,?,?,now())";

		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, shoppingCartDTO.getBookNo());
			JdbcObject.getPreparedStatement().setInt(2, shoppingCartDTO.getMemberNo());
			JdbcObject.getPreparedStatement().setInt(3, shoppingCartDTO.getShoppingcartAmount());
			JdbcObject.getPreparedStatement().setInt(4, shoppingCartDTO.getShoppingcartPrice());
			
			check = JdbcObject.getPreparedStatement().executeUpdate();
			if(0 == check)return false;
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	//선택 물품 장바구니에서 삭제 메서드
	//리턴값 true, false로 메서드 성공 확인
	public boolean deleteCart(int shoppingcartNo) {
		
		//데이터 삭제 성공 여부 변수
		int check = 0;
		
		String sql = "DELETE FROM shoppingcart WHERE shoppingcart_no=?";

		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, shoppingcartNo);
			
			check = JdbcObject.getPreparedStatement().executeUpdate();
			if(0 == check)return false;
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	//장바구니 리스트 보여주기
	//페이지기능
	public ArrayList<BookJoinCartDTO> selectCartList(int currentPage, int rowPage){
		ArrayList<BookJoinCartDTO> arrayList = new ArrayList<>();
		
		String sql = "SELECT s.shoppingcart_no, s.book_no, s.member_no, s.shoppingcart_amount, s.shoppingcart_price, s.shoppingcart_date, b.book_name FROM shoppingcart s JOIN book b ON s.book_no = b.book_no LIMIT ?, ?";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, (currentPage-1)*rowPage);
			JdbcObject.getPreparedStatement().setInt(2, rowPage);
			
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			while(JdbcObject.getResultSet().next()) {
				BookJoinCartDTO bookJoinCartDTO = new BookJoinCartDTO();
				ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
				BookDTO bookDTO = new BookDTO();
				shoppingCartDTO.setShoppingcartNo(JdbcObject.getResultSet().getInt(1));
				shoppingCartDTO.setBookNo(JdbcObject.getResultSet().getInt(2));
				shoppingCartDTO.setMemberNo(JdbcObject.getResultSet().getInt(3));
				shoppingCartDTO.setShoppingcartAmount(JdbcObject.getResultSet().getInt(4));
				shoppingCartDTO.setShoppingcartPrice(JdbcObject.getResultSet().getInt(5));
				shoppingCartDTO.setShoppingcartDate(JdbcObject.getResultSet().getString(6));
				bookDTO.setBookName(JdbcObject.getResultSet().getString(7));
				bookJoinCartDTO.setBookDTO(bookDTO);
				bookJoinCartDTO.setShoppingCartDTO(shoppingCartDTO);
				arrayList.add(bookJoinCartDTO);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return arrayList;
	}
	
	//페이지 기능 마지막 페이지 번호 구하기
	public int selectLastPage(int rowPage) {
		//마지막 페이지 변수
		int lastPage = 0;
		
		String sql = "SELECT count(shoppingcart_no) FROM shoppingcart";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			if(JdbcObject.getResultSet().next()) {
				int count = JdbcObject.getResultSet().getInt(1);
				lastPage = count/rowPage;
				if(0 != (count%rowPage)) lastPage++;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return lastPage;
	}
}
