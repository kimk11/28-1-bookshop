//28기 김진우
//2018/07/24
package service;

import java.util.ArrayList;

import dao.ShoppingCartDAO;
import dto.BookJoinCartDTO;
import dto.ShoppingCartDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class ShoppingCartService {
	private ShoppingCartDAO shoppingCartDAO;
	
	public ShoppingCartService() {
		this.shoppingCartDAO = new ShoppingCartDAO();
	}
	
	//장바구니 추가
	//리턴값 0:실패, 1:성공
	public int insertCart(ShoppingCartDTO shoppingCartDTO) {
		// 리턴값 변수
		int insertCheck = 0;
		
		try {
			//dao insert메서드 성공 유무
			boolean check = shoppingCartDAO.insertCart(shoppingCartDTO);
			
			if(check) {
				JdbcObject.getConnection().commit();
				insertCheck = 1;
			}else {
				
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
	
	//장바구니 삭제
	//리턴값 0:실패, 1:성공
	public int deleteCart(int shoppingcartNo) {
		// 리턴값 변수
		int insertCheck = 0;
		
		try {
			//dao delete메서드 성공 유무
			boolean check = shoppingCartDAO.deleteCart(shoppingcartNo);
			
			if(check) {
				JdbcObject.getConnection().commit();
				
			}else {
				
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
	
	//장바구니 리스트
	public ArrayList<BookJoinCartDTO> selectCartList(int currentPage, int rowPage){
		ArrayList<BookJoinCartDTO> arrayList = null;
		
		try {
			arrayList = shoppingCartDAO.selectCartList(currentPage, rowPage);
			
			if(null != arrayList) {
				JdbcObject.getConnection().commit();
			}else {
				
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
	
	//장바구니 리스트 마지막 페이지
	//리턴값 0:실패, 0!:성공
	public int selectLastPage(int rowPage) {
		int lastPage = 0;
		
		try {
			lastPage = shoppingCartDAO.selectLastPage(rowPage);
			
			if(0 != lastPage) {
				JdbcObject.getConnection().commit();
			}else {
				
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
	
	//장바구니 상세조회
	public ShoppingCartDTO selectOneCart(int shoppingCartNo) {
		ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
		
		try {
			shoppingCartDTO = shoppingCartDAO.selectOneCart(shoppingCartNo);
			
			if(null != shoppingCartDTO) {
				JdbcObject.getConnection().commit();
			}else {
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		
		return shoppingCartDTO;
	}
}
