package org.gigsoft.secondoblog.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.gigsoft.secondoblog.dto.MemberDto;
import org.gigsoft.secondoblog.model.Member;
import org.gigsoft.secondoblog.model.RoleType;
import org.gigsoft.secondoblog.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterMemberService {	
	Logger logger = LoggerFactory.getLogger(RegisterMemberService.class);
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;    

    @Transactional
    public MemberDto join(MemberDto dto) {    	
    	Set<RoleType> roles = new HashSet<>();
    	roles.add(RoleType.USER);
    	
        Member member = Member.builder()
        	.username(dto.username())
        	.password(passwordEncoder.encode(dto.password()))
        	.email(dto.email())
        	.oauth(dto.oauth())
        	.roles(roles)
        	.createdDate(new Timestamp(System.currentTimeMillis()))
        	.build();
        logger.info("join encoded password: {}", member.getPassword());
        validateDuplicateMember(member);        
        
        return MemberDto.createUserDto(memberRepository.save(member));
    }

    private void validateDuplicateMember(Member member) throws IllegalStateException {
        memberRepository.findByUsername(member.getUsername())
        	.ifPresent(m -> new IllegalStateException(m.getUsername() + "는 이미 존재하는 회원입니다."));                
    }	

    @Transactional
	public MemberDto update(MemberDto dto) {		
		Member member = memberRepository.findByUsername(dto.username())
			.orElseThrow(() -> new IllegalArgumentException(dto.username() + "는 존재하지 않는 회원입니다."));		
		member.patch(dto, passwordEncoder);
		
		return MemberDto.createUserDto(member);
	}
    
    @Transactional
    public Long searchMember(String username) {
    	Member member = memberRepository.findByUsername(username).orElse(null);
    	if (member == null) 
    		return 0L;
    	return member.getId();
    }   
}
