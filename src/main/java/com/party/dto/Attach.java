package com.party.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attach {
	
	private int attach_idx;
	private String notice_type;
	private int parent_idx;
	private String real_file_name;
	private String upload_file_path;
	private String reg_id;
	private String reg_dt;
	private boolean image;
	
	@Override
	public String toString() {
		return "Attach [attach_idx=" + attach_idx + ", notice_type=" + notice_type + ", parent_idx=" + parent_idx
				+ ", real_file_name=" + real_file_name + ", upload_file_path=" + upload_file_path + ", reg_id=" + reg_id
				+ ", reg_dt=" + reg_dt + "]";
	}
}
