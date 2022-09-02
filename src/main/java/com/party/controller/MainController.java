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
public class MainController {
	
	@RequestMapping(value={"/","index"})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model, TimeZone timeZone) throws Exception {
		return "index";
	}

}
 
