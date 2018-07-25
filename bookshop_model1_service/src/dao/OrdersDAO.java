//28기 김진우
//2018/07/25
package dao;

import java.util.ArrayList;

import dto.BookDTO;
import dto.BookJoinOrdersDTO;
import dto.OrdersDTO;
import jdbcObject.JdbcObject;

public class OrdersDAO {
	
	//장바구니에서 주문했을 시 테이블에 데이터 추가
	//리턴값 boolean으로 메서드 성공 유무
	public boolean insertOders(OrdersDTO ordersDTO) {

		//데이터 추가 성공 여부 변수
		int check = 0;
		
		String sql = "INSERT INTO orders(book_no,member_no,orders_amount,orders_price,orders_date,orders_addr,orders_state) VALUES(?,?,?,?,now(),?,'주문완료')";

		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, ordersDTO.getBookNo());
			JdbcObject.getPreparedStatement().setInt(2, ordersDTO.getMemberNo());
			JdbcObject.getPreparedStatement().setInt(3, ordersDTO.getOrdersAmount());
			JdbcObject.getPreparedStatement().setInt(4, ordersDTO.getOrdersPrice());
			JdbcObject.getPreparedStatement().setString(5, ordersDTO.getOrdersAddr());
			
			check = JdbcObject.getPreparedStatement().executeUpdate();
			if(0 == check)return false;
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	//선택 물품 주문리스트에서 삭제 메서드
	//리턴값 true, false로 메서드 성공 확인
	public boolean deleteCart(int ordersNo) {
		
		//데이터 삭제 성공 여부 변수
		int check = 0;
		
		String sql = "DELETE FROM orders WHERE orders_no=?";

		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, ordersNo);
			
			check = JdbcObject.getPreparedStatement().executeUpdate();
			if(0 == check)return false;
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	//주문 리스트 보여주기
	//페이지기능
	//검색기능
	public ArrayList<BookJoinOrdersDTO> selectCartList(int currentPage, int rowPage, String searchValue, String searchKey, int memberNo) {
		System.out.println(rowPage+"리스트행");
		System.out.println(searchValue+"검색값");
		System.out.println(searchKey+"검색키");
		System.out.println(memberNo+"멤버넘버");
		
		
		
		ArrayList<BookJoinOrdersDTO> arrayList = new ArrayList<>();
		
		String sql = "select b.book_name, o.orders_no, o.book_no, o.member_no, o.orders_amount, o.orders_price, o.orders_date, o.orders_addr, o.orders_state from book b join orders o on b.book_no=o.book_no where member_no=? limit ?,?";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			
			// 처음 실행 시켰을 경우
			if(null == searchValue && searchKey.equals("")) {
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
				JdbcObject.getPreparedStatement().setInt(1, memberNo);
				JdbcObject.getPreparedStatement().setInt(2, (currentPage-1)*rowPage);
				JdbcObject.getPreparedStatement().setInt(3, rowPage);
			
			// 검색단어가 없는 경우
			}else if(null != searchValue && searchKey.equals("")) {
				sql = "select b.book_name, o.orders_no, o.book_no, o.member_no, o.orders_amount, o.orders_price, o.orders_date, o.orders_addr, o.orders_state from book b join orders o on b.book_no=o.book_no where member_no=? limit ?,?";
				JdbcObject.getPreparedStatement().setInt(1, memberNo);
				JdbcObject.getPreparedStatement().setInt(2, (currentPage-1)*rowPage);
				JdbcObject.getPreparedStatement().setInt(3, rowPage);
				
			// 검색단어가 있을 경우
			}else if(null != searchValue && !searchKey.equals("")) {
				sql = "select b.book_name, o.orders_no, o.book_no, o.member_no, o.orders_amount, o.orders_price, o.orders_date, o.orders_addr, o.orders_state from book b join orders o on b.book_no=o.book_no like book_name=? where member_no=? limit ?,?";
				JdbcObject.getPreparedStatement().setString(1, "%"+searchKey+"%");
				JdbcObject.getPreparedStatement().setInt(2, memberNo);
				JdbcObject.getPreparedStatement().setInt(3, (currentPage-1)*rowPage);
				JdbcObject.getPreparedStatement().setInt(4, rowPage);
			}
			
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			while(JdbcObject.getResultSet().next()) {
				BookJoinOrdersDTO bookJoinOrdersDTO = new BookJoinOrdersDTO();
				BookDTO bookDTO = new BookDTO();
				OrdersDTO ordersDTO = new OrdersDTO();
				
				bookDTO.setBookName(JdbcObject.getResultSet().getString(1));
				ordersDTO.setOrdersNo(JdbcObject.getResultSet().getInt(2));
				ordersDTO.setBookNo(JdbcObject.getResultSet().getInt(3));
				ordersDTO.setMemberNo(JdbcObject.getResultSet().getInt(4));
				ordersDTO.setOrdersAmount(JdbcObject.getResultSet().getInt(5));
				ordersDTO.setOrdersPrice(JdbcObject.getResultSet().getInt(6));
				ordersDTO.setOrdersDate(JdbcObject.getResultSet().getString(7));
				ordersDTO.setOrdersAddr(JdbcObject.getResultSet().getString(8));
				ordersDTO.setOrdersState(JdbcObject.getResultSet().getString(9));
				bookJoinOrdersDTO.setBookDTO(bookDTO);
				bookJoinOrdersDTO.setOrdersDTO(ordersDTO);
				arrayList.add(bookJoinOrdersDTO);
				
				System.out.println("DAO");
				System.out.println(arrayList.get(1).getOrdersDTO().getOrdersState());
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
		
		String sql = "SELECT count(orders_no) FROM orders";
		
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
