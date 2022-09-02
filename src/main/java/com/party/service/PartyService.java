package com.party.service;

import com.party.dao.PartyDao;
import com.party.dto.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {
	
	@Autowired
	PartyDao dao;
	
    /**
     * 당원 조회
     * @param party
     * @return
     * @throws Exception
     */
    public Party selectParty(Party party) throws Exception {
    	party = dao.selectParty(party);
    	return party;
    }
    
    /**
     * 당원가입
     * @param party
     * @throws Exception
     */
    public void insertParty(Party party) throws Exception {
    	dao.insertParty(party);
    }
    
}
