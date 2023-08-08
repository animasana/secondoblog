package org.gigsoft.secondoblog.api;

import org.gigsoft.secondoblog.dto.PostReplyRequestDto;
import org.gigsoft.secondoblog.dto.ReplyDto;
import org.gigsoft.secondoblog.dto.ResponseDto;
import org.gigsoft.secondoblog.model.Reply;
import org.gigsoft.secondoblog.service.ReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReplyApiController {
	private final ReplyService replyService;
	
	@PostMapping("/api/board/{boardId}/reply")	
	ResponseEntity<?> createRepy(@RequestBody PostReplyRequestDto dto) {		
		Reply saved = replyService.createReply(dto);
		log.info("boardId: {}", dto.boardId());
		
		if (saved != null) {
			ResponseDto<?> resDto = ResponseDto.<ReplyDto>builder()
				.message("Post Reply Success!!")
				.data(ReplyDto.createReplyDto(saved))
				.build();
			return ResponseEntity.ok(resDto);
		}		
		return ResponseEntity.badRequest().body(null);		
	}
}
