package com.party.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TimeZone;

@Slf4j
@Controller
public class LoginController {

	@RequestMapping(value={"/login"})
	public String login(HttpServletRequest request, HttpServletResponse response, Model model, TimeZone timeZone) throws Exception {
		return "login";
	}
	
	@RequestMapping(value={"/logout"})
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model, TimeZone timeZone) throws Exception {
		request.getSession().removeAttribute("SESSION_INFO");
		request.getSession().invalidate();
		return "redirect:/login";
	}
	
}

