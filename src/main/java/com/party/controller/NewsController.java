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
@RequestMapping(value="/news")
public class NewsController {
	
	@Autowired
    NoticeService service;
	
	/**
	 * 공지사항
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice")
	public String notice(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		return "news/notice";
	}
	
	/**
	 * 공지사항 목록
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/list")
	public @ResponseBody
    Map<String, Object> noticeList(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
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
	 * 공지사항 상세
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/view")
	public String noticeView(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		// 조회수 증가
		service.updateNotice(notice);
		
		notice = service.selectNotice(notice);
		model.addAttribute("notice", notice);
		
		return "news/notice_view";
	}
	
	/**
	 * 보도자료
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/report")
	public String report(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		return "news/report";
	}
	
	/**
	 * 보도자료 목록
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/report/list")
	public @ResponseBody
    Map<String, Object> reportList(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
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
	 * 보도자료 상세
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/report/view")
	public String reportView(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		// 조회수 증가
		service.updateNotice(notice);
		
		notice = service.selectNotice(notice);
		model.addAttribute("notice", notice);
		
		return "news/report_view";
	}
	
	/**
	 * 논평
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/comment")
	public String comment(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		return "news/comment";
	}
	
	/**
	 * 논평 목록
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/comment/list")
	public @ResponseBody
    Map<String, Object> commentList(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int total = service.selectNoticeListCount(notice);
		
		notice.setDataPerPage(9);
		List<Notice> list = service.selectCommentList(notice);
		
		notice.setDataPerPage(5);
		List<Notice> mList = service.selectCommentList(notice);
		
		resultMap.put("total", total);
		resultMap.put("list", list);
		resultMap.put("mList", mList);
		
		return resultMap;
	}
	
	/**
	 * 논평 상세
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/comment/view")
	public String commentView(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		// 조회수 증가
		service.updateNotice(notice);
		
		notice = service.selectNotice(notice);
		model.addAttribute("notice", notice);
				
		return "news/comment_view";
	}
	
	/**
	 * 브리핑 화면
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/briefing")
	public String briefing(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		return "news/briefing";
	}
	
	/**
	 * 브리핑 목록
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/briefing/list")
	public @ResponseBody
    Map<String, Object> briefingList(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
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
	 * 브리핑 상세
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/briefing/view")
	public String briefingView(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		// 조회수 증가
		service.updateNotice(notice);
		
		notice = service.selectNotice(notice);
		model.addAttribute("notice", notice);
		
		return "news/briefing_view";
	}
	
	/**
	 * 홍보화면
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/data")
	public String data(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		return "news/data";
	}
	
	/**
	 * 홍보 목록
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/data/list")
	public @ResponseBody
    Map<String, Object> dataList(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
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
	 * 홍보 상세
	 * @param request
	 * @param response
	 * @param model
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/data/view")
	public String dataView(HttpServletRequest request, HttpServletResponse response, Model model, Notice notice) throws Exception {
		
		// 조회수 증가
		service.updateNotice(notice);
		
		notice = service.selectNotice(notice);
		model.addAttribute("notice", notice);
		
		return "news/data_view";
	}

}
