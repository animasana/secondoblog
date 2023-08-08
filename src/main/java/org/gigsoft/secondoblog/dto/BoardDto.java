package org.gigsoft.secondoblog.dto;

import java.sql.Timestamp;
import java.util.List;

import org.gigsoft.secondoblog.model.Board;
import org.gigsoft.secondoblog.model.Member;
import org.gigsoft.secondoblog.model.Reply;

import lombok.Builder;

@Builder
public record BoardDto(
		Long id, 
		String title, 
		String content,
		Integer count,
		Member member,		
		List<Reply> replies,
		Timestamp createdDate) {
	
	public Board toEntity() {
		return Board.builder()
			.id(id)
			.title(title)
			.content(content)
			.count(count)	
			.member(member)
			.replies(replies)
			.createdDate(createdDate)
			.build();		
	}
	
	public static BoardDto createBoardDto(Board created) {
		return BoardDto.builder()
			.id(created.getId())
			.title(created.getTitle())
			.content(created.getContent())
			.member(created.getMember())			
			.count(created.getCount())
			.replies(created.getReplies())
			.createdDate(created.getCreatedDate())
			.build();			
	}
}
