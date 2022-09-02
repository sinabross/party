package com.party.controller;

import com.party.dto.Notice;
import com.party.dto.Vote;
import com.party.dto.VoteParty;
import com.party.service.NoticeService;
import com.party.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value="/party")
public class PartyController {
	
	@Autowired
	NoticeService service;
	
	@Autowired
	VoteService vservice;
	
	/**
	 * 당원게시판 목록화면
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/community")
	public String community(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		return "party/community";
	}
	
	/**
	 * 당원게시판 목록
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/community/list")
	public @ResponseBody
    Map<String, Object> communityList(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int total = service.selectNoticeListCount(notice);
		notice.setDataPerPage(10);
		List<Notice> list = service.selectNoticeList(notice);
		notice.setDataPerPage(5);
		List<Notice> mList = service.selectNoticeList(notice);
		resultMap.put("total", total);
		resultMap.put("list", list);
		resultMap.put("mList", mList);
		
		return resultMap;
	}
	
	/**
	 * 당원게시판 상세
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/community/view")
	public String view(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		// 조회수 증가
		service.updateNotice(notice);
		
		notice = service.selectNotice(notice);
		model.addAttribute("notice", notice);
		
		return "party/community_view";
	}
	
	/**
	 * 투표목록화면
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vote")
	public String vote(HttpServletRequest request, HttpServletResponse response, Model model, Vote vote) throws Exception {
		return "party/vote";
	}
	
	/**
	 * 투표 목록요청
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vote/list")
	public @ResponseBody
    Map<String, Object> voteList(HttpServletRequest request, HttpServletResponse response, Model model, Vote vote) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int total = 0;
		List<Vote> list = null;
		List<Vote> mList = null;
		
		// 진행중
		if(vote.getStatus().equals("I")) {
			total = vservice.selectVoteListCount(vote);
			vote.setDataPerPage(10);
			list = vservice.selectVoteList(vote);
			vote.setDataPerPage(5);
			mList = vservice.selectVoteList(vote);
		// 마감
		}else {
			total = vservice.selectVoteListEndCount(vote);
			vote.setDataPerPage(10);
			list = vservice.selectVoteListEnd(vote);
			vote.setDataPerPage(5);
			mList = vservice.selectVoteListEnd(vote);
		}
		
		resultMap.put("total", total);
		resultMap.put("list", list);
		resultMap.put("mList", mList);
		
		return resultMap;
	}
	
	/**
	 * 진행중인 투표 상세보기
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vote_view")
	public String voteView(HttpServletRequest request, HttpServletResponse response, Model model, Vote vote) throws Exception {
		
		// 조회수 증가
		vservice.updateVote(vote);
		vote = vservice.selectVote(vote);
		model.addAttribute("vote", vote);
		
		return "party/vote_view";
	}
	
	/**
	 * 진행중인 투표 - 투표
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/voting")
	public @ResponseBody
    Map<String, Object> voting(HttpServletRequest request, HttpServletResponse response, Model model, VoteParty voteParty) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(request.getSession().getAttribute("SESSION_INFO") == null) {
			
			resultMap.put("result", "no-session");
			resultMap.put("msg", "투표는 로그인 후 가능합니다.");
			return resultMap;
			
		}else {
			Map<String, Object> sessionMap = (Map<String, Object>)request.getSession().getAttribute("SESSION_INFO");
			int party_idx = (Integer)sessionMap.get("party_idx");
			voteParty.setParty_idx(party_idx);
			int count = vservice.selectVoteParty(voteParty);
			
			if(count != 0) {
				resultMap.put("result", "completed");
			}else {
				vservice.insertVoteParty(voteParty);
				resultMap.put("result", "success");
			}
			
			return resultMap;
		}
	}
	
	/**
	 * 마감된 투표 상세보기
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vote_view2")
	public String voteView2(HttpServletRequest request, HttpServletResponse response, Model model, Vote vote) throws Exception {
		
		// 조회수 증가
		vservice.updateVote(vote);
		vote = vservice.selectVoteEnd(vote);
		model.addAttribute("vote", vote);
		
		return "party/vote_view2";
	}
	
}
