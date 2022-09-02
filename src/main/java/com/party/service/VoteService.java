package com.party.service;

import com.party.dao.VoteDao;
import com.party.dto.Vote;
import com.party.dto.VoteParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
	
	@Autowired
	VoteDao dao;
	
    /**
     * 투표목록 카운트(진행중)
     * @param vote
     * @return
     * @throws Exception
     */
    public int selectVoteListCount(Vote vote) throws Exception {
    	int total = dao.selectVoteListCount(vote);
    	return total;
    }
    
    /**
     * 투표목록(진행중)
     * @param vote
     * @throws Exception
     */
    public List<Vote> selectVoteList(Vote vote) throws Exception {
    	List<Vote> list = dao.selectVoteList(vote);
    	return list;
    }
    
    /**
     * 투표목록 카운트(마감)
     * @param vote
     * @return
     * @throws Exception
     */
    public int selectVoteListEndCount(Vote vote) throws Exception {
    	int total = dao.selectVoteListEndCount(vote);
    	return total;
    }
    
    /**
     * 투표목록(마감)
     * @param vote
     * @throws Exception
     */
    public List<Vote> selectVoteListEnd(Vote vote) throws Exception {
    	List<Vote> list = dao.selectVoteListEnd(vote);
    	return list;
    }
    
    /**
     * 진행중인 투표 상세조회
     * @param vote
     * @return
     * @throws Exception
     */
    public Vote selectVote(Vote vote) throws Exception {
    	vote = dao.selectVote(vote);
    	return vote;
    }
    
    /**
     * 마감된 투표 상세조회
     * @param vote
     * @return
     * @throws Exception
     */
    public Vote selectVoteEnd(Vote vote) throws Exception {
    	vote = dao.selectVoteEnd(vote);
    	return vote;
    }
    
    /**
     * 투표여부 확인 카운트
     * @param voteParty
     * @return
     * @throws Exception
     */
    public int selectVoteParty(VoteParty voteParty) throws Exception {
    	int count = dao.selectVoteParty(voteParty);
    	return count;
    }
    
    /**
     * 투표
     * @param voteParty
     * @throws Exception
     */
    public void insertVoteParty(VoteParty voteParty) throws Exception {
    	dao.insertVoteParty(voteParty);
    }
    
    /**
     * 투표 조회수 증가
     * @param vote
     * @throws Exception
     */
    public void updateVote(Vote vote) throws Exception {
    	dao.updateVote(vote);
    }
    
    /**
     * 투표 등록
     * @param notice
     * @throws Exception
     */
    public void insertVote(Vote vote) throws Exception {
    	dao.insertVote(vote);
    }
    
    /**
     * 투표수정
     * @param vote
     * @throws Exception
     */
    public void updateVoteA(Vote vote) throws Exception {
    	dao.updateVoteA(vote);
    }
    
    /**
     * 투표삭제
     * @param list
     * @throws Exception
     */
    public void deleteVote(List list) throws Exception {
    	dao.deleteVote(list);
    }
}
