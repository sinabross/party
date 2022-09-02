package com.party.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class CommonInterceptor implements HandlerInterceptor {
   @Override
   public boolean preHandle(
           HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	   
	   String requestUri = request.getRequestURI();
	   log.info("##################################################################");
	   log.info("uri : " + requestUri);
	   log.info("##################################################################");
	   
	   response.setHeader("Cache-Control","no-store");  
	   response.setHeader("Pragma","no-cache");  
	   response.setDateHeader("Expires",0);  
	   if (request.getProtocol().equals("HTTP/1.1"))
	           response.setHeader("Cache-Control", "no-cache");
	   
	   return true;
	   
   }
   
   @Override
   public void postHandle(
           HttpServletRequest request, HttpServletResponse response, Object handler,
           ModelAndView modelAndView) throws Exception {}
   
   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                               Object handler, Exception exception) throws Exception {}
}