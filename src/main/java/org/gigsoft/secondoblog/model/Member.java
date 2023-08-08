package org.gigsoft.secondoblog.model;

import java.sql.Timestamp;
import java.util.Set;

import org.gigsoft.secondoblog.dto.MemberDto;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
//@DynamicInsert //insert시에 null인 필드를 제외시킴.
public class Member {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(nullable = false, length = 100, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 100, unique = true)
	private String email;	
	
	@Column(name = "oauth", nullable = true, length = 30)
	private String oauth; //kakao, google
	
	@Convert(converter = RoleTypeListToStringConverter.class)	
	@Column(name = "roles")
	private Set<RoleType> roles;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private Timestamp createdDate;

	public Member patch(MemberDto requestedUser, PasswordEncoder passwordEncoder) {
		if (requestedUser.password() != "" && 
			requestedUser.password() != null) 
			this.password = passwordEncoder.encode(requestedUser.password());
		
		if (requestedUser.email() != null)
			this.email = requestedUser.email();
		
		this.createdDate = new Timestamp(System.currentTimeMillis());
		
		return this;
	}
	
	public static Member createUser(MemberDto dto) {		
		Timestamp createdDate = new Timestamp(System.currentTimeMillis());
		log.info("createUser(): {}", dto.password());
		
        return Member.builder()
        		.username(dto.username())
        		.password(dto.password())
        		.email(dto.email())
        		.oauth(dto.oauth())
        		.roles(dto.roles())
        		.createdDate(createdDate)
        		.build();
    }
}
