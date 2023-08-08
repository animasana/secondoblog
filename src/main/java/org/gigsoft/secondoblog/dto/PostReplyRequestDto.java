package org.gigsoft.secondoblog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record PostReplyRequestDto(
		@JsonProperty("board_id")
		Long boardId,
		@JsonProperty("member_id")
		Long memberId, 
		String content) {
}
