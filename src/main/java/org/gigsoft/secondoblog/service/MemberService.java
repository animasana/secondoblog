package org.gigsoft.secondoblog.service;

import java.util.Optional;

import org.gigsoft.secondoblog.model.Member;
import org.gigsoft.secondoblog.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {	
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public Optional<Member> findByUsername(String username) {
		return memberRepository.findByUsername(username);		
	}
	
	public Member findById(Long id) {
		return memberRepository.findById(id).orElseThrow(() -> new IllegalStateException());
	}
	
	public Boolean isValidMember(String username, String password) {
		Optional<Member> optionalUser = memberRepository.findByUsername(username);
		if (optionalUser.isPresent()) {
			return optionalUser.get().getPassword().equals(password);
		}
		return false;		
	}	
}
