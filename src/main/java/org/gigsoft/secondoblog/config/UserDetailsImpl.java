package org.gigsoft.secondoblog.config;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.gigsoft.secondoblog.model.Member;
import org.gigsoft.secondoblog.model.RoleType;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDetailsImpl implements UserDetails, CredentialsContainer {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7534086942149339450L;	
	
	private final Member member;
	
	@Builder
	public UserDetailsImpl(Member member) {
		this.member = member;
	}
	
	public Long getId() {
		return this.member.getId();		
	}
	
	public String getEmail() {
		return this.member.getEmail();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return convertAuthorities(this.member.getRoles());		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub		
		return this.member.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.member.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void eraseCredentials() {
		// TODO Auto-generated method stub
		this.member.setPassword(null);		
	}
	
	private String toRoleString(RoleType role) {
		return "ROLE_" + role.name();
	}
	
	private Collection<? extends GrantedAuthority> convertAuthorities(Set<RoleType> roles) {
		return roles.stream()					
					.map(this::toRoleString)
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toSet());		
	}	
}
