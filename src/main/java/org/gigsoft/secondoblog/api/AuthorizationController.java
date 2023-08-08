package org.gigsoft.secondoblog.api;

import org.gigsoft.secondoblog.config.UserDetailsImpl;
import org.gigsoft.secondoblog.config.UserDetailsServiceImpl;
import org.gigsoft.secondoblog.dto.MemberDto;
import org.gigsoft.secondoblog.dto.ResponseDto;
import org.gigsoft.secondoblog.service.RegisterMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

//import jakarta.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class AuthorizationController {
	private final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);	
	
	private final RegisterMemberService registerMemberService;
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	
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
	public ResponseEntity<?> update(@RequestBody MemberDto dto, @AuthenticationPrincipal UserDetailsImpl customUserDetails) {
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
		UserDetailsImpl userDetailsImpl = userDetailsServiceImpl.loadUserByUsername(dto.username());
		logger.info("userDetailsImpl.password(): {}", userDetailsImpl.getPassword());
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsImpl, userDetailsImpl, userDetailsImpl.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
