package com.party.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vote extends Paging {
	
	private int 	vote_idx;		//투표PK인덱스
	private String vote_title;		//투표제목
	private String vote_content;	//투표내용
	private String start_dt;		//시작일
	private String end_dt;			//종료일
	private int 	view_cnt;		//조회수
	private String reg_id;			//등록자ID
	private String reg_dt;			//등록일
	private String mod_id;			//수정자ID
	private String mod_dt;			//수정일
	
	private String delete_vote_idx_list;
	
	private String status;			//투표상태
	private int votepercent;		//투표율
	private int ypercent;			//찬성율
	private int npercent;			//반대율
	private int ycnt;				//찬성율
	private int ncnt;				//반대율
	
	@Override
	public String toString() {
		return "Vote [vote_idx=" + vote_idx + ", vote_title=" + vote_title + ", vote_content=" + vote_content
				+ ", start_dt=" + start_dt + ", end_dt=" + end_dt + ", view_cnt=" + view_cnt + ", reg_id=" + reg_id
				+ ", reg_dt=" + reg_dt + ", mod_id=" + mod_id + ", mod_dt=" + mod_dt + ", delete_vote_idx_list="
				+ delete_vote_idx_list + ", status=" + status + ", votepercent=" + votepercent + ", ypercent="
				+ ypercent + ", npercent=" + npercent + ", ycnt=" + ycnt + ", ncnt=" + ncnt + "]";
	}
}
