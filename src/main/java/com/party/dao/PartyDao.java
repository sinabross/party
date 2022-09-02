package com.party.dao;

import com.party.dto.Party;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class PartyDao {
	
	protected static final String NAMESPACE = "party.";

	@Autowired
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	/**
	 * 사용자 조회
	 * @param party
	 * @return
	 */
	public Party selectParty(Party party) {
		party = sqlSession.selectOne(NAMESPACE + "selectParty", party);
		return party;
	}
	
	/**
	 * 당원가입
	 * @param party
	 */
	public void insertParty(Party party) {
		sqlSession.insert(NAMESPACE + "insertParty", party);
	}
	
}
