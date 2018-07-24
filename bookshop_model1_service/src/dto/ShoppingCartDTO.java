//28기 김진우
//2018/07/24
package dto;

public class ShoppingCartDTO {
	private int shoppingcartNo;
	private int bookNo;
	private int memberNo;
	private int shoppingcartAmount;
	private int shoppingcartPrice;
	private String shoppingcartDate;
	
	public int getShoppingcartNo() {
		return shoppingcartNo;
	}
	public void setShoppingcartNo(int shoppingcartNo) {
		this.shoppingcartNo = shoppingcartNo;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getShoppingcartAmount() {
		return shoppingcartAmount;
	}
	public void setShoppingcartAmount(int shoppingcartAmount) {
		this.shoppingcartAmount = shoppingcartAmount;
	}
	public int getShoppingcartPrice() {
		return shoppingcartPrice;
	}
	public void setShoppingcartPrice(int shoppingcartPrice) {
		this.shoppingcartPrice = shoppingcartPrice;
	}
	public String getShoppingcartDate() {
		return shoppingcartDate;
	}
	public void setShoppingcartDate(String shoppingcartDate) {
		this.shoppingcartDate = shoppingcartDate;
	}
	
}
