package org.gigsoft.secondoblog.api;

import org.gigsoft.secondoblog.config.UserDetailsImpl;
import org.gigsoft.secondoblog.dto.MemberDto;
import org.gigsoft.secondoblog.dto.ResponseDto;
import org.gigsoft.secondoblog.service.RegisterMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

//import jakarta.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class AuthorizationController {
	private final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);	
	
	private final RegisterMemberService registerMemberService;
//	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final AuthenticationManager authenticationManager;
	
	@PostMapping("/user/join/process")
	public ResponseEntity<ResponseDto<MemberDto>> join(@RequestBody MemberDto dto) {
		try {
			MemberDto body = registerMemberService.join(dto);
			ResponseDto<MemberDto> responseDto = ResponseDto.<MemberDto>builder()
					.message("Join Success!!")
					.data(body)
					.build();
			return ResponseEntity.ok(responseDto);
		} 
		catch (Exception e) {
			ResponseDto<MemberDto> responseDto = new ResponseDto<>(e.getMessage(), null);
			return ResponseEntity.badRequest().body(responseDto);			
		}
	}
	
	@PutMapping("/user/info")
	public ResponseEntity<?> update(@RequestBody MemberDto dto) {
		try {
			MemberDto updatedDto = registerMemberService.update(dto);
			resetSession(dto);
			
			ResponseDto<?> responseDto = ResponseDto.<MemberDto>builder()
					.message("Update Success!!")
					.data(updatedDto)
					.build();
			return ResponseEntity.ok(responseDto);
		} 
		catch (Exception e) {
			ResponseDto<MemberDto> responseDto = new ResponseDto<>(e.getMessage(), null);
			return ResponseEntity.badRequest().body(responseDto);			
		}		
	}
	
	private void resetSession(MemberDto dto) {		
		Authentication authReq = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
		Authentication authentication = authenticationManager.authenticate(authReq);
		logger.info("getPrincipal(): {}", ((UserDetailsImpl) authentication.getPrincipal()).getEmail());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}	
	
	public void login(HttpServletRequest req, String username, String password) { 
    Authentication authReq = new UsernamePasswordAuthenticationToken(username, password);
    Authentication authentication = authenticationManager.authenticate(authReq);
    
    SecurityContext securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(authentication);
    HttpSession session = req.getSession(true);
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);    
	}
}
