package com.party.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Notice extends Paging {
	
	private int 	notice_idx;				//게시판
	private String notice_type;			//정책자료실~당원게시판:NTC01~NTC08
	private String division;				//게시글타입
	private String notice_title;			//제목
	private String notice_content;			//내용
	private int     view_cnt;				//조회수
	private String reg_id;					//등록아이디
	private String reg_dt;					//등록일
	private String mod_id;					//수정아이디
	private String mod_dt;					//수정일
	
	private String real_file_name; 			// 실제파일명
	private String upload_file_path;		// 업로드파일명
	
	private String real_file_name_list; 	// 실제파일명
	private String upload_file_path_list;	// 업로드파일명
	
	private String delete_notice_idx_list;	// 삭제할 IDX 목록
	
	private List<Attach> attachList;		// 첨부파일 목록

	@Override
	public String toString() {
		return "Notice [notice_idx=" + notice_idx + ", notice_type=" + notice_type + ", division=" + division
				+ ", notice_title=" + notice_title + ", notice_content=" + notice_content + ", view_cnt=" + view_cnt
				+ ", reg_id=" + reg_id + ", reg_dt=" + reg_dt + ", mod_id=" + mod_id + ", mod_dt=" + mod_dt
				+ ", real_file_name=" + real_file_name + ", upload_file_path=" + upload_file_path
				+ ", real_file_name_list=" + real_file_name_list + ", upload_file_path_list=" + upload_file_path_list
				+ ", delete_notice_idx_list=" + delete_notice_idx_list + "]";
	}
	
}
