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
@RequestMapping(value="/intro")
public class IntroController {
	
	@RequestMapping(value="/info")
	public String info(HttpServletRequest request, HttpServletResponse response, Model model, TimeZone timeZone) throws Exception {
		return "intro/info";
	}
	
	@RequestMapping(value="/regulation")
	public String regulation(HttpServletRequest request, HttpServletResponse response, Model model, TimeZone timeZone) throws Exception {
		return "intro/regulation";
	}
	
	@RequestMapping(value="/organization")
	public String organization(HttpServletRequest request, HttpServletResponse response, Model model, TimeZone timeZone) throws Exception {
		return "intro/organization";
	}
	
}
 
