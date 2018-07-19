package dto;

public class BookCodePublisherJoinDTO {
	
	private BookDTO bookDTO;
	private BookCodeDTO bookCodeDTO;
	private BookPublisherDTO bookPublisherDTO;
	
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

}
