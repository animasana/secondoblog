package org.gigsoft.secondoblog.service;

import java.util.Optional;

import org.gigsoft.secondoblog.dto.BoardDto;
import org.gigsoft.secondoblog.model.Board;
import org.gigsoft.secondoblog.model.Member;
import org.gigsoft.secondoblog.repository.BoardRepository;
import org.gigsoft.secondoblog.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {	
	private final MemberRepository memberRepository;
	private final BoardRepository boardRepository;	
	
	public BoardService(MemberRepository memberRepository,
						BoardRepository boardRepository) {
		this.memberRepository = memberRepository;
		this.boardRepository = boardRepository;
	}
	
	@Transactional
	public Board write(BoardDto dto, UserDetails userDetails) {
		Optional<Member> optionalMember = memberRepository.findByUsername(userDetails.getUsername());
		if (optionalMember.isPresent()) {
			Board boardEntity = dto.toEntity();
			boardEntity.setMember(optionalMember.get());
			boardEntity.setCount(0);
			return boardRepository.save(boardEntity);			
		}
		return null;
	}
	
	@Transactional(readOnly=true)
	public Page<Board> listArticles(Pageable pageable) {
		return boardRepository.findAll(pageable);		
	}

	@Transactional(readOnly=true)
	public Board findOne(Long id) {
		return boardRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("글 찾기 실패: 사용자를 찾을 수 없습니다."));		
	}
	
	@Transactional
	public void delete(Long id) {
		boardRepository.findById(id).ifPresent(boardRepository::delete);		
	}

	@Transactional
	public Board update(BoardDto dto) {
		Board articleEntity = boardRepository.findById(dto.id())
			.orElseThrow(() -> new IllegalArgumentException("글 찾기 실패: 수정할 수 없습니다."));		
		
		return articleEntity.patch(dto);
	}
}
