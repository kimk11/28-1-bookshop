package dto;

public class BookJoinCartDTO {
	private BookDTO bookDTO;
	private ShoppingCartDTO shoppingCartDTO;
	
	public BookDTO getBookDTO() {
		return bookDTO;
	}
	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}
	public ShoppingCartDTO getShoppingCartDTO() {
		return shoppingCartDTO;
	}
	public void setShoppingCartDTO(ShoppingCartDTO shoppingCartDTO) {
		this.shoppingCartDTO = shoppingCartDTO;
	}
	
}
