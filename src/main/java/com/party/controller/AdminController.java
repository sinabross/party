package com.party.controller;

import com.party.dto.Notice;
import com.party.dto.Vote;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value="admin")
public class AdminController {
	
	@Autowired
    NoticeService service;
	
	@Autowired
    VoteService vservice;
	
	/**
	 * 게시판 삭제
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"notice/deleteNotice"})
	public @ResponseBody
    Map<String, Object> deleteNotice(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String[] idxArr = notice.getDelete_notice_idx_list().split(",");
		List list = Arrays.asList(idxArr);
		
		service.deleteNotice(list);
		
		resultMap.put("result", "success");
		return resultMap;
	}
	
	/**
	 * 게시판 등록/수정 화면
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"notice/write"})
	public String add(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		if(notice.getNotice_idx() != 0) { // 등록:0,수정:code_idx
			notice = service.selectNotice(notice);
			model.addAttribute("data", notice);
		}
		
		return "admin/notice_write";
	}
	
	/**
	 * 게시판 등록
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"notice/addProc"})
	public @ResponseBody
    Map<String, Object> addProc(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> sessionMap = (Map<String, Object>)request.getSession().getAttribute("SESSION_INFO");
		
		notice.setReg_id((String)sessionMap.get("party_id"));
		notice.setMod_id((String)sessionMap.get("party_id"));
		
		service.insertNotice(notice);
		
		resultMap.put("result", "success");
		return resultMap;
	}
	
	/**
	 * 게시판 수정
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="notice/modifyProc")
	public @ResponseBody
    Map<String, Object> modifyProc(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> sessionMap = (Map<String, Object>)request.getSession().getAttribute("SESSION_INFO");
		
		notice.setMod_id((String)sessionMap.get("party_id"));
		
		service.updateNoticeA(notice);
		
		resultMap.put("result", "success");
		return resultMap;
	}
	
	/**
	 * 투표 삭제
	 * @param request
	 * @param response
	 * @param model
	 * @param vote
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"vote/deleteVote"})
	public @ResponseBody
    Map<String, Object> deleteVote(HttpServletRequest request, HttpServletResponse response, Model model, Vote vote) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String[] idxArr = vote.getDelete_vote_idx_list().split(",");
		List list = Arrays.asList(idxArr);
		vservice.deleteVote(list);
		resultMap.put("result", "success");
		return resultMap;
	}
	
	/**
	 * 투표 등록/수정 화면
	 * @param request
	 * @param response
	 * @param model
	 * @param vote
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"vote/write"})
	public String voteWrite(HttpServletRequest request, HttpServletResponse response, Model model, Vote vote) throws Exception {
		
		if(vote.getVote_idx() != 0) { // 등록:0,수정:code_idx
			vote = vservice.selectVote(vote);
			model.addAttribute("data", vote);
		}
		
		return "admin/vote_write";
	}
	
	/**
	 * 투표 등록
	 * @param request
	 * @param response
	 * @param model
	 * @param vote
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"vote/addProc"})
	public @ResponseBody
    Map<String, Object> voteAddProc(HttpServletRequest request, HttpServletResponse response, Model model, Vote vote) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		Map<String, Object> sessionMap = (Map<String, Object>)request.getSession().getAttribute("SESSION_INFO");
		
		vote.setReg_id((String)sessionMap.get("party_id"));
		vote.setMod_id((String)sessionMap.get("party_id"));
		vservice.insertVote(vote);
		resultMap.put("result", "success");
		return resultMap;
	}
	
	/**
	 * 투표 수정
	 * @param request
	 * @param response
	 * @param model
	 * @param vote
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="vote/modifyProc")
	public @ResponseBody
    Map<String, Object> voteModifyProc(HttpServletRequest request, HttpServletResponse response, Model model, Vote vote) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> sessionMap = (Map<String, Object>)request.getSession().getAttribute("SESSION_INFO");
		
		vote.setMod_id((String)sessionMap.get("party_id"));
		vservice.updateVoteA(vote);
		resultMap.put("result", "success");
		return resultMap;
		
	}
	
}
