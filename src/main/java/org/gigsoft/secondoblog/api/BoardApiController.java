package org.gigsoft.secondoblog.api;

import org.gigsoft.secondoblog.dto.BoardDto;
import org.gigsoft.secondoblog.dto.ResponseDto;
import org.gigsoft.secondoblog.model.Board;
import org.gigsoft.secondoblog.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {
	private Logger logger = LoggerFactory.getLogger(BoardApiController.class);
	private final BoardService boardService;
	
	public BoardApiController(BoardService boardService) {
		this.boardService = boardService;
	}	
	
	@PostMapping("/api/board")
	ResponseEntity<?> write(@RequestBody BoardDto dto, 
							@AuthenticationPrincipal UserDetails userDetails
	) {
		logger.info("BoardApiController: {}", userDetails.getUsername());
		logger.info("{}:{}", dto.title(), dto.content());		
		
		Board saved = boardService.write(dto, userDetails);
		if (saved != null) {
			ResponseDto<BoardDto> resDto = ResponseDto.<BoardDto>builder()
				.message("Write Success!!")
				.data(BoardDto.createBoardDto(saved))
				.build();
			return ResponseEntity.ok(resDto);
		}		
		return null;		
	}
	
	@PutMapping("/api/board")
	ResponseEntity<?> update(@RequestBody BoardDto dto) {
		Board updated = boardService.update(dto);
		if (updated != null) {
			ResponseDto<BoardDto> resDto = ResponseDto.<BoardDto>builder()
				.message("Update Success!!")
				.data(BoardDto.createBoardDto(updated))
				.build();
			return ResponseEntity.ok(resDto);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/api/board/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		boardService.delete(id);		
		return ResponseEntity.ok("Delete Success!!");		
	}
}
