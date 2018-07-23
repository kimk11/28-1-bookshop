package dto;

public class QnaCommentDTO {
	private int qnaCommentNo;
	private int qnaNo;
	private int adminNo;
	private String commentContent;
	private String commentDate;
	
	
	public int getQnaCommentNo() {
		return qnaCommentNo;
	}
	public void setQnaCommentNo(int qnaCommentNo) {
		this.qnaCommentNo = qnaCommentNo;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	
	
	
}
