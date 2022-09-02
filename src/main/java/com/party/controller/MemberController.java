package com.party.controller;

import com.party.dto.Party;
import com.party.service.PartyService;
import com.party.utils.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
	PartyService service;
	
	@RequestMapping(value="/join")
	public String join(HttpServletRequest request, HttpServletResponse response, Model model, Party party) throws Exception {
		return "member/join";
	}

	/**
	 * 아이디 중복확인
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @param party
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/check_duplication")
	public @ResponseBody
    Map<String, Object> checkDuplication(HttpServletRequest request, HttpServletResponse response, Model model, TimeZone timeZone, Party party) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		party = service.selectParty(party);
		
		String duplicationYN = "N";
		if(party != null) {
			duplicationYN = "Y";
		} 
		resultMap.put("duplicationYN", duplicationYN);
		
		return resultMap;
	}
	
	/**
	 * 당원가입
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @param party
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/joinProc")
	public  @ResponseBody
    Map<String, Object> joinProc(HttpServletRequest request, HttpServletResponse response, Model model, Party party) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		// 서버 Validation
		if(party.getParty_id() == null || party.getParty_id().equals("")) {
			resultMap.put("result", "fail");
			resultMap.put("msg", "아이디가 없습니다.");
			return resultMap;
		}
		if(party.getParty_pwd() == null || party.getParty_pwd().equals("")) {
			resultMap.put("result", "fail");
			resultMap.put("msg", "비밀번호가 없습니다.");
			return resultMap;
		}
		if(party.getAddr() == null || party.getAddr().equals("")) {
			resultMap.put("result", "fail");
			resultMap.put("msg", "주소가 없습니다.");
			return resultMap;
		}
		if(party.getAddr_detail() == null || party.getAddr_detail().equals("")) {
			resultMap.put("result", "fail");
			resultMap.put("msg", "상세주소가 없습니다.");
			return resultMap;
		}
		
		Map<String, String> joinMap = (Map<String, String>)request.getSession().getAttribute("JOIN_INFO");
		Party chkParty = new Party();
		chkParty.setMobile((String)joinMap.get("mobile"));
		chkParty = service.selectParty(chkParty);
		if(chkParty != null) {
			resultMap.put("result", "fail");
			resultMap.put("msg", "이미 당원으로 가입된 휴대폰 번호입니다.");
			return resultMap;
		}
		
		
		party.setName((String)joinMap.get("name"));
		party.setMobile((String)joinMap.get("mobile"));
		party.setBirth((String)joinMap.get("birth"));
		
		// 비밀번호 단방향 암호화 후 DB저장
		String hashPwd = HashUtil.sha256(party.getParty_pwd());
		party.setParty_pwd(hashPwd);
		service.insertParty(party);
		
		request.getSession().removeAttribute("JOIN_INFO");
		
		resultMap.put("result", "success");
		return resultMap;
	}

	@PostMapping(value="/join_result")
	public String joinResult(HttpServletRequest request, HttpServletResponse response, Model model, String fullPath) throws Exception {
		return "member/join_result";
	}
	
	@RequestMapping(value="/findPW")
	public String findPW(HttpServletRequest request, HttpServletResponse response, Model model, TimeZone timeZone) throws Exception {
		return "member/findPW";
	}
	
	@PostMapping(value="/findPW_result")
	public String findPWResult(HttpServletRequest request, HttpServletResponse response, Model model, TimeZone timeZone) throws Exception {
		return "member/findPW_result";
	}
	
	/**
	 * 잘못된 요청의 경우 gateway 페이지
	 * @param request
	 * @param response
	 * @param model
	 * @param timeZone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="message")
	public String message(HttpServletRequest request, HttpServletResponse response, Model model, TimeZone timeZone) throws Exception {
		return "member/message";
	}
	
}
