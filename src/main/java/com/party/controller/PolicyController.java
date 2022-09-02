package com.party.controller;

import com.party.dto.Notice;
import com.party.service.NoticeService;
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
@RequestMapping(value="/policy")
public class PolicyController {
	
	@Autowired
	NoticeService service;
	
	/**
	 * 정책자료실
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reference")
	public String reference(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		return "policy/reference";
	}
	
	/**
	 * 정책자료실 목록
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reference/list")
	public @ResponseBody
    Map<String, Object> referenceList(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
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
	 * 정책자료실 상세
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reference/view")
	public String referenceView(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		// 조회수 증가
		service.updateNotice(notice);
		
		notice = service.selectNotice(notice);
		model.addAttribute("notice", notice);
		
		return "policy/reference_view";
	}
	
	/**
	 * 공약
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/commitment")
	public String commitment(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		return "policy/commitment";
	}
	
	/**
	 * 공약 목록
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/commitment/list")
	public @ResponseBody
    Map<String, Object> commitmentList(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
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
	 * 공약 상세
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/commitment/view")
	public String commitmentView(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		// 조회수 증가
		service.updateNotice(notice);
		
		notice = service.selectNotice(notice);
		model.addAttribute("notice", notice);
		
		return "policy/commitment_view";
	}
	
	
}
