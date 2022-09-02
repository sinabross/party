package com.party.dao;

import com.party.dto.Vote;
import com.party.dto.VoteParty;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class VoteDao {
	
	protected static final String NAMESPACE = "vote.";

	@Autowired
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	/**
	 * 투표목록 카운트(진행중)
	 * @param vote
	 * @return
	 */
	public int selectVoteListCount(Vote vote) {
		int total = sqlSession.selectOne(NAMESPACE + "selectVoteListCount", vote);
		return total;
	}
	
	/**
	 * 투표목록조회(진행중)
	 * @param vote
	 */
	public List<Vote> selectVoteList(Vote vote) {
		List<Vote> list = sqlSession.selectList(NAMESPACE + "selectVoteList", vote);
		return list;
	}
	
	/**
	 * 투표목록 카운트(마감)
	 * @param vote
	 * @return
	 */
	public int selectVoteListEndCount(Vote vote) {
		int total = sqlSession.selectOne(NAMESPACE + "selectVoteListEndCount", vote);
		return total;
	}
	
	/**
	 * 투표목록조회(마감)
	 * @param vote
	 */
	public List<Vote> selectVoteListEnd(Vote vote) {
		List<Vote> list = sqlSession.selectList(NAMESPACE + "selectVoteListEnd", vote);
		return list;
	}
	
	/**
	 * 진행중인 투표 조회
	 * @param vote
	 * @return
	 */
	public Vote selectVote(Vote vote) {
		vote = sqlSession.selectOne(NAMESPACE + "selectVote", vote);
		return vote;
	}
	
	/**
	 * 마감된 투표 조회
	 * @param vote
	 * @return
	 */
	public Vote selectVoteEnd(Vote vote) {
		vote = sqlSession.selectOne(NAMESPACE + "selectVoteEnd", vote);
		return vote;
	}
	
	/**
	 * 투표여부 확인 카운트
	 * @param voteParty
	 * @return
	 */
	public int selectVoteParty(VoteParty voteParty) {
		int count = sqlSession.selectOne(NAMESPACE + "selectVoteParty", voteParty);
		return count;
	}
	
	/**
	 * 투표
	 * @param voteParty
	 */
	public void insertVoteParty(VoteParty voteParty) {
		sqlSession.insert(NAMESPACE + "insertVoteParty", voteParty);
	}
	
	/**
	 * 투표조회수 증가
	 * @param vote
	 */
	public void updateVote(Vote vote) {
		sqlSession.update(NAMESPACE + "updateVote", vote);
	}
	
	/**
	 * 투표 등록
	 * @param notice
	 */
	public void insertVote(Vote vote) {
		sqlSession.insert(NAMESPACE + "insertVote", vote);
	}
	
	/**
	 * 투표 수정
	 * @param vote
	 */
	public void updateVoteA(Vote vote) {
		sqlSession.update(NAMESPACE + "updateVoteA", vote);
	}
	
	/**
	 * 투표 삭제
	 * @param vote
	 */
	public void deleteVote(List list) {
		sqlSession.delete(NAMESPACE + "deleteVote", list);
	}
}
