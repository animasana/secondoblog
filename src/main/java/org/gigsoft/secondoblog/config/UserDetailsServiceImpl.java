package org.gigsoft.secondoblog.config;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.gigsoft.secondoblog.model.Member;
import org.gigsoft.secondoblog.model.RoleType;
import org.gigsoft.secondoblog.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final MemberService memberService;
	
	public UserDetailsServiceImpl(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
		
		logger.info("rolesSet: {}", member.getRoles());
		logger.info("password: {}", member.getPassword());		
		
		return UserDetailsImpl.builder().member(member).build();
	}
	
	public Collection<? extends GrantedAuthority> makeGrantedAuthorities(Set<RoleType> roles, Principal pricipal) {
		Set<GrantedAuthority> collection = new HashSet<>();		
		for (RoleType role : roles) {			
			collection.add(new JaasGrantedAuthority("ROLE_" + role.name(), pricipal));
		}
		return collection;
	}
	
	public Collection<? extends GrantedAuthority> makeGrantedAuthorities(Set<RoleType> roles) {
		Set<GrantedAuthority> collection = new HashSet<>();		
		for (RoleType role : roles) {
			collection.add(new SimpleGrantedAuthority("ROLE_" + role.name()));			
		}
		return collection;
	}	
}
