package com.party.security;

import com.party.dao.PartyDao;
import com.party.dto.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	PartyDao dao;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
    	
    	// 아이디에 해당하는 로그인 정보 조회
    	Party party = new Party();
    	party.setParty_id(authentication.getName());
    	party = dao.selectParty(party);
    	
    	Map<String, Object> partyMap = new HashMap<String, Object>();
    	partyMap.put("party_idx", party.getParty_idx());			// 투표_당원테이블 복합키로 사용되기 떄문에 추가.
    	partyMap.put("party_id", party.getParty_id());
    	partyMap.put("party_level", party.getParty_level());
    	HttpSession session = request.getSession();
    	session.setAttribute("SESSION_INFO", partyMap);
    	
    	// DB의 최종 로그인 시간 UPDATE
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("{\"success\": true}");
        response.getWriter().flush();
    }
 
}
