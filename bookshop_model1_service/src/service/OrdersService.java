//28기 김진우
//2018/07/25
package service;

import java.util.ArrayList;

import dao.OrdersDAO;
import dto.BookJoinOrdersDTO;
import dto.OrdersDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class OrdersService {
	private OrdersDAO ordersDAO;
	
	public OrdersService() {
		this.ordersDAO = new OrdersDAO();
	}
	
	//주문시 목록에 추가
	//리턴값 0:실패, 1:성공
	public int insertCart(OrdersDTO ordersDTO) {
		// 리턴값 변수
		int insertCheck = 0;
		
		try {
			//dao insert메서드 성공 유무
			boolean check = ordersDAO.insertOders(ordersDTO);
			
			if(check) {
				JdbcObject.getConnection().commit();
				
			}else {
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		}catch (Exception e) {
			// TODO: handle exception
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		return insertCheck;
	}
	
	//주문취소 주문목록에서 삭제
	//리턴값 0:실패, 1:성공
	public int deleteCart(int ordersNo) {
		// 리턴값 변수
		int insertCheck = 0;
		
		try {
			//dao delete메서드 성공 유무
			boolean check = ordersDAO.deleteCart(ordersNo);
			
			if(check) {
				JdbcObject.getConnection().commit();
				
			}else {
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		}catch (Exception e) {
			// TODO: handle exception
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		return insertCheck;
	}
	
	//주문 리스트
	//페이지
	//검색
	public ArrayList<BookJoinOrdersDTO> selectCartList(int currentPage, int rowPage, String searchValue, String searchKey , int memberNo){
		ArrayList<BookJoinOrdersDTO> arrayList = null;
		
		try {
			arrayList = ordersDAO.selectCartList(currentPage, rowPage, searchValue, searchKey, memberNo);
			
//			System.out.println("DAO");
//			System.out.println(arrayList.get(1).getOrdersDTO().getOrdersState());
			
			if(null != arrayList) {
				JdbcObject.getConnection().commit();
			}else {
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		}catch (Exception e) {
			// TODO: handle exception
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		
		return arrayList;
	}
	
	//주문목록 리스트 마지막 페이지
	//리턴값 0:실패, 0!:성공
	public int selectLastPage(int rowPage) {
		int lastPage = 0;
		
		try {
			lastPage = ordersDAO.selectLastPage(rowPage);
			
			if(0 != lastPage) {
				JdbcObject.getConnection().commit();
			}else {
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		}catch (Exception e) {
			// TODO: handle exception
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		
		return lastPage;
	}
}
