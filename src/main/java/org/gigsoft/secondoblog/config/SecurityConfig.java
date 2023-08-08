package org.gigsoft.secondoblog.config;

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
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(CsrfConfigurer::disable)
			.cors(cors -> cors.disable())
	        .authorizeHttpRequests(authz -> 
	        	authz
	        		.requestMatchers("/**", "/user/**", "/js/**", "/image/**").permitAll()
	        		.requestMatchers("/auth/kakao/callback").permitAll()
	        		.requestMatchers("/login/kakao").permitAll()
	        		.requestMatchers("/user/join/process", "/user/login/process").permitAll()	        		
	        		.requestMatchers("/dummy/**").permitAll()
	        		.requestMatchers("/board/**").permitAll()
	        		.anyRequest().authenticated()	        
	        )
	        .formLogin(formLogin -> 
	        	formLogin
	        		.loginPage("/login")
	        		.loginProcessingUrl("/user/login/process")
	        		.usernameParameter("username")
	        		.passwordParameter("password")
	        		.defaultSuccessUrl("/login", true)	        		
	        		.permitAll()
	        )
	        .userDetailsService(userDetailsServiceImpl)
//	        .logout(Customizer.withDefaults())
	        .logout(logout -> 
	        	logout
	        		.logoutSuccessUrl("/")
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

