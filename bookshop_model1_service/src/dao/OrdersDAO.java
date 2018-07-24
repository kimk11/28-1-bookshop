package dao;

import java.util.ArrayList;

import dto.BookDTO;
import dto.BookJoinCartDTO;
import dto.OrdersDTO;
import dto.ShoppingCartDTO;
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
	public ArrayList<> selectCartList(int currentPage, int rowPage, String searchValue, String searchKey) {
		ArrayList<> arrayList = new ArrayList<>();
		
		String sql = "";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));

			
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			while(JdbcObject.getResultSet().next()) {
				
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
