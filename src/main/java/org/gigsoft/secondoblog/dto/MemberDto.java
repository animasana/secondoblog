package org.gigsoft.secondoblog.dto;

import java.sql.Timestamp;
import java.util.Set;

import org.gigsoft.secondoblog.model.Member;
import org.gigsoft.secondoblog.model.RoleType;

import lombok.Builder;

@Builder
public record MemberDto(
	Long id, 
	String username, 
	String password, 
	String email,
	String oauth,
	Set<RoleType> roles,
	Timestamp createdDate) {

	public Member toEntity() {
		Member user = Member.builder()
			.id(id)
			.username(username)
			.password(password)
			.email(email)
			.oauth(oauth)
			.roles(roles)
			.createdDate(createdDate)
			.build();
		return user;
	}

	public static MemberDto createUserDto(Member member) {
		return MemberDto.builder()
			.id(member.getId())
			.username(member.getUsername())
			.password(member.getPassword())
			.email(member.getEmail())
			.oauth(member.getOauth())
			.roles(member.getRoles())
			.createdDate(member.getCreatedDate())
			.build();		
	}
}
