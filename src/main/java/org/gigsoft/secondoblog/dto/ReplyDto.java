package org.gigsoft.secondoblog.dto;

import java.sql.Timestamp;

import org.gigsoft.secondoblog.model.Board;
import org.gigsoft.secondoblog.model.Member;
import org.gigsoft.secondoblog.model.Reply;

import lombok.Builder;

@Builder
public record ReplyDto(
		Long id, 
		String content, 
		Board board, 
		Member member, 
		Timestamp createdDate) {
	
	public Reply toEntity() {
		return Reply.builder()
			.id(id)			
			.content(content)
			.board(board)
			.member(member)			
			.createdDate(createdDate)
			.build();		
	}
	
	public static ReplyDto createReplyDto(Reply created) {
		return ReplyDto.builder()
			.id(created.getId())			
			.content(created.getContent())
			.board(created.getBoard())
			.member(created.getMember())			
			.createdDate(created.getCreatedDate())
			.build();			
	}	
}
