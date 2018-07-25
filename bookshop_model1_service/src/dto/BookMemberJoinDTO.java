package dto;

public class BookMemberJoinDTO {

	private BookReviewDTO bookReviewDTO;
	private MemberDTO memberDTO;
	
	public BookReviewDTO getBookReviewDTO() {
		return bookReviewDTO;
	}
	public void setBookReviewDTO(BookReviewDTO bookReviewDTO) {
		this.bookReviewDTO = bookReviewDTO;
	}
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}
	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}
}
