package com.party.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteParty {
	
	private int 	vote_party_idx;	//투표_당원PK인덱스
	private int 	vote_idx;		//투표PK인덱스
	private int 	party_idx;		//당원PK인덱스
	private String vote;			//찬성:Y,반대:N
	private String reg_dt;			//투표시간
	
	@Override
	public String toString() {
		return "VoteParty [vote_party_idx=" + vote_party_idx + ", vote_idx=" + vote_idx + ", party_idx=" + party_idx
				+ ", vote=" + vote + ", reg_dt=" + reg_dt + "]";
	}
}
