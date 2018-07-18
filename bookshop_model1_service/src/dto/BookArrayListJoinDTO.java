package dto;

import java.util.ArrayList;

public class BookArrayListJoinDTO {
	private ArrayList<BookCodeDTO> bookCodeList;
	private ArrayList<BookPublisherDTO> bookPublisherList;
	
	public ArrayList<BookCodeDTO> getBookCodeList() {
		return bookCodeList;
	}
	public void setBookCodeList(ArrayList<BookCodeDTO> bookCodeList) {
		this.bookCodeList = bookCodeList;
	}
	public ArrayList<BookPublisherDTO> getBookPublisherList() {
		return bookPublisherList;
	}
	public void setBookPublisherList(ArrayList<BookPublisherDTO> bookPublisherList) {
		this.bookPublisherList = bookPublisherList;
	}
}
