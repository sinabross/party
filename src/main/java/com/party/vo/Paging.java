package com.party.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
	
	private int dataPerPage;
	private int page;
	private int start;
	private int end;
	private String searchWord;
	
}
