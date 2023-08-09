package org.gigsoft.secondoblog.controller;

import org.gigsoft.secondoblog.dto.MemberDto;
import org.gigsoft.secondoblog.model.KakaoProfile;
import org.gigsoft.secondoblog.model.OAuthToken;
import org.gigsoft.secondoblog.service.RegisterMemberService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

// 인증이 안된 사용자들이 출입할 수 있는 경로로 /auth/* 허용
// 그냥 주소가 '/' 이면 index.jsp 허용
// static 이하에 있는 ./js/*, ./css/*, ./image/*

@RequiredArgsConstructor
@Controller
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);	
	
	@Value("${gig.key}")
	private String gigKey;
	
	private final RegisterMemberService registerMemberService;
	private final AuthenticationManager authenticationManager;
	
	@GetMapping("/login")
	public String login() {
		return "user/loginForm";
	}
	
	@GetMapping("/user/join")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/user/login")
	public String loginForm() {		
		return "user/loginForm";
	}
	
	@GetMapping("/user/info") 
	public String userInfo() {		
		return "user/infoForm";		
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code, HttpServletRequest req) {		
		OAuthToken oauthToken = getAccessToken(code);		
		KakaoProfile kakaoProfile = getUserProfile(oauthToken.access_token());		
		kakaoJoin(kakaoProfile, req);		
		return "redirect:/";
	}
	
	private OAuthToken getAccessToken(String code) {
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", "4f66b92874c03fd1c08f3c5a0d2fe38d");
		body.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		body.add("code", code);
		
		JSONObject jsonObject = new JSONObject(body);
		System.out.println(jsonObject.toString());
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);		
		
		ResponseEntity<?> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);
		
		//Gson, Json Simple, ObjectMapper				
		try {
			ObjectMapper objectMapper = new ObjectMapper();			
			OAuthToken oauthToken = objectMapper.readValue(response.getBody().toString(), OAuthToken.class);
			return oauthToken;
		}
		catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private KakaoProfile getUserProfile(String accessToken) {
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");		
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);		
		
		ResponseEntity<?> response = rt.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.POST,
			kakaoProfileRequest,
			String.class
		);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();			
			KakaoProfile kakaoProfile = objectMapper.readValue(response.getBody().toString(), KakaoProfile.class);
			logger.info("[KaKaoProfile]: {}", kakaoProfile.getKakaoAccount().getEmail());
			return kakaoProfile;
		}
		catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void kakaoJoin(KakaoProfile kakaoProfile, HttpServletRequest req) {
		String username = kakaoProfile.getKakaoAccount().getEmail() + "_" + kakaoProfile.getId();
		String password = gigKey;
		String email = kakaoProfile.getKakaoAccount().getEmail();
		
		MemberDto dto = MemberDto.builder()
			.username(username)
			.password(password)
			.email(email)
			.oauth("kakao")
			.build();		
		
		if (registerMemberService.searchMember(dto.username()) == 0L) {
			logger.info("{} 사용자가 없습니다.", dto.username());
			registerMemberService.join(dto);			
		}
		
		logger.info("[password]: {}", password);		
		login(req, username, password);
	}	
	
	public void login(HttpServletRequest req, String username, String password) { 
    Authentication authReq = new UsernamePasswordAuthenticationToken(username, password);
    Authentication authentication = authenticationManager.authenticate(authReq);
    
    SecurityContext securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(authentication);
    HttpSession session = req.getSession(true);
    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);    
	}
}
