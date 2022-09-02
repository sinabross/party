package com.party.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Party implements Serializable {
	
	private static final long serialVersionUID = 1326968531561113062L;
	
	public Party(String party_id) {
		this.party_id = party_id;
	}

	private int 	party_idx;		//당원PK인덱스
	private String party_id;		//당원ID
	private String party_pwd;		//당원비밀번호
	private String name;			//당원명
	private String mobile;			//휴대폰번호
	private String birth;			//생년월일
	private String addr;			//주소
	private String addr_detail;	//주소상세
	private String id;			//아이디
	private int 	party_level;	//관리자 레벨
	private String reg_dt;			//가입일

	@Override
	public String toString() {
		return "Party [party_idx=" + party_idx + ", party_id=" + party_id + ", party_pwd=" + party_pwd + ", name="
				+ name + ", mobile=" + mobile + ", birth=" + birth + ", addr=" + addr + ", addr_detail=" + addr_detail
				+ ", id=" + id + ", party_level=" + party_level + ", reg_dt=" + reg_dt + "]";
	}
	
}
