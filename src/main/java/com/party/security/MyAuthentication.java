package com.party.security;

import com.party.dto.Party;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
public class MyAuthentication extends UsernamePasswordAuthenticationToken {


	private static final long serialVersionUID = 1L;

	String party_id;
	List<GrantedAuthority> authorities;
	Party party;
	
	
	public MyAuthentication(String party_id, String party_pwd, List<GrantedAuthority> authorities) {
		super(party_id, party_pwd, authorities);
		this.party_id = party_id;
		this.authorities = authorities;
	}
	
	public MyAuthentication(String party_id, String party_pwd, List<GrantedAuthority> authorities, Party party) {
		super(party_id, party_pwd, authorities);
		this.party_id = party_id;
		this.authorities = authorities;
		this.party = party;
	}

}
