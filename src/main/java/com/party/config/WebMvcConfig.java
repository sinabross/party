package com.party.config;

import com.party.interceptor.CommonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {
   @Autowired
   CommonInterceptor commonInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(commonInterceptor)
      		  	.addPathPatterns("/**")
      		    .excludePathPatterns("/img/**")
      		    .excludePathPatterns("/img/*")
      		    .excludePathPatterns("/css/**")
      		    .excludePathPatterns("/css/*")
      		    .excludePathPatterns("/js/**")
      		    .excludePathPatterns("/js/*");
   }
   
}
