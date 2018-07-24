package dto;

public class BookJoinDTO {
	
	private BookDTO bookDTO;
	private BookCodeDTO bookCodeDTO;
	private BookPublisherDTO bookPublisherDTO;
	private BookIntroDTO bookIntroDTO;
	private BookReviewDTO bookReviewDTO;
	
	public BookDTO getBookDTO() {
		return bookDTO;
	}
	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}
	public BookCodeDTO getBookCodeDTO() {
		return bookCodeDTO;
	}
	public void setBookCodeDTO(BookCodeDTO bookCodeDTO) {
		this.bookCodeDTO = bookCodeDTO;
	}
	public BookPublisherDTO getBookPublisherDTO() {
		return bookPublisherDTO;
	}
	public void setBookPublisherDTO(BookPublisherDTO bookPublisherDTO) {
		this.bookPublisherDTO = bookPublisherDTO;
	}
	public BookIntroDTO getBookIntroDTO() {
		return bookIntroDTO;
	}
	public void setBookIntroDTO(BookIntroDTO bookIntroDTO) {
		this.bookIntroDTO = bookIntroDTO;
	}
	public BookReviewDTO getBookReviewDTO() {
		return bookReviewDTO;
	}
	public void setBookReviewDTO(BookReviewDTO bookReviewDTO) {
		this.bookReviewDTO = bookReviewDTO;
	}
}
