package org.gigsoft.secondoblog.config;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	
	private static final String[] PERMIT_ALL_PATTERNS = new String[] {
		"/**", "/user/**", "/js/**", "/image/**",
		"/auth/kakao/callback",
		"/login/kakao",
		"/user/join/process", "/user/login/process",		
		"/board/**"		
	};		
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {		
		http
			.csrf(CsrfConfigurer::disable)
			.cors(cors -> cors.disable())
	        .authorizeHttpRequests(authz -> 
	        	authz
	        		.requestMatchers(
	        			Stream.of(PERMIT_ALL_PATTERNS)
	        			      .map(AntPathRequestMatcher::antMatcher)
	        			      .toArray(AntPathRequestMatcher[]::new)
	        		).permitAll()
	        		.anyRequest().authenticated()	        
	        )
	        .formLogin(formLogin -> 
	        	formLogin
	        		.loginPage("/login")
	        		.loginProcessingUrl("/user/login/process")
	        		.usernameParameter("username")
	        		.passwordParameter("password")
	        		.defaultSuccessUrl("/", true)	        		
	        		.permitAll()
	        )
	        .userDetailsService(userDetailsServiceImpl)
	        .logout(logout -> 
	        	logout
	        		.logoutSuccessUrl("/login")
	        		.invalidateHttpSession(true)
	        		.deleteCookies("JSESSIONID")
	        );
	        
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		logger.info("authenticationConfiguration: {}", authenticationConfiguration.toString());
		return authenticationConfiguration.getAuthenticationManager();
	}

}

