package com.party.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
	
	private int dataPerPage=10;
	private int page=1;
	private int start;
	private int end;
	private String searchWord;
	private int offsetSec;
	
	public void setPage(int page) {
		this.page = page;
		setStart();
	}
	
	public void setDataPerPage(int dataPerPage) {
		this.dataPerPage = dataPerPage;
		setStart();
	}
	
	public void setStart() {
		this.start = (this.page - 1) * this.dataPerPage;
	}
}
