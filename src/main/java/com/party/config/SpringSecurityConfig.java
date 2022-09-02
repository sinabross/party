package com.party.config;

import com.party.security.AuthFailureHandler;
import com.party.security.AuthProvider;
import com.party.security.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
    AuthProvider authProvider;
    
    @Autowired
    AuthFailureHandler authFailureHandler;
 
    @Autowired
    AuthSuccessHandler authSuccessHandler;

 
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 허용되어야 할 경로들
        web.ignoring().antMatchers("/css/**", "/css/*", 
                                   "/js/**", "/js/*",
                                   "/img/**", "/img/*",
                                   "/plugins/**", "/plugins/*"
                                   ); 
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        // 로그인 설정
        http.authorizeRequests()
            // ROLE_USER, ROLE_ADMIN으로 권한 분리 유알엘 정의
            .antMatchers("/", "/login", "/loginProcess", "/error**", "/health").permitAll()
            .antMatchers("/**/**/regist*").authenticated()
        .and()
            // 로그인 페이지 및 성공 url, handler 그리고 로그인 시 사용되는 id, password 파라미터 정의
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/loginProcess")
            .failureHandler(authFailureHandler)
            .successHandler(authSuccessHandler)
            .usernameParameter("party_id")
            .passwordParameter("party_pwd")
        .and()    
            // 로그아웃 관련 설정
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
        .and()
            // 로그인 프로세스가 진행될 provider
            .authenticationProvider(authProvider);
        
        http.sessionManagement()
    		.maximumSessions(5)
    		.maxSessionsPreventsLogin(false); // true : 동일한 사용자 로그인하면 로그인 안됨,  false : 로그인되고 기존 사용자 로그아웃
        
        http.csrf().disable();
            // csrf 사용유무 설정
            // csrf 설정을 사용하면 모든 request에 csrf 값을 함께 전달해야한다.
        //http.headers().frameOptions().disable();		// X-Frame-Options 비활성화
        http.headers().frameOptions().sameOrigin();		// X-Frame-Options 동일도메인 허용
    }
 
}
