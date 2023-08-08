package org.gigsoft.secondoblog.service;

import org.gigsoft.secondoblog.dto.PostReplyRequestDto;
import org.gigsoft.secondoblog.model.Board;
import org.gigsoft.secondoblog.model.Member;
import org.gigsoft.secondoblog.model.Reply;
import org.gigsoft.secondoblog.repository.BoardRepository;
import org.gigsoft.secondoblog.repository.MemberRepository;
import org.gigsoft.secondoblog.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	private final ReplyRepository replyRepository;	

	@Transactional
	public Reply createReply(PostReplyRequestDto dto) {
		Board board = boardRepository.findById(dto.boardId())
			.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));;
		Member member = memberRepository.findById(dto.memberId())
			.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
		
		Reply reply = Reply.builder()			
			.content(dto.content())
			.board(board)
			.member(member)			
			.build();		
		
		return replyRepository.save(reply);
	}
}
