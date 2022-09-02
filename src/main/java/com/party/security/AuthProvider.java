package com.party.security;

import com.party.dao.PartyDao;
import com.party.dto.Party;
import com.party.utils.Constant;
import com.party.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("authProvider")
public class AuthProvider implements AuthenticationProvider {
    
    @Autowired
    PartyDao dao;
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	
        String party_id = authentication.getName();
        String party_pwd = HashUtil.sha256(authentication.getCredentials().toString());
        
        Party party = dao.selectParty(new Party(party_id));
        
        // email에 맞는 user가 없거나 비밀번호가 맞지 않는 경우.
        if (null == party || !party.getParty_pwd().equals(party_pwd)) {
            return null;
        }
        
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        
        // 로그인한 계정에게 권한 부여
        grantedAuthorityList.add(new SimpleGrantedAuthority(Constant.ROLE_TYPE.ROLE_USER.toString()));
 
        // 로그인 성공시 로그인 사용자 정보 반환
        return new MyAuthentication(party_id, party_pwd, grantedAuthorityList);
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}