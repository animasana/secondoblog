package org.gigsoft.secondoblog.controller;

import org.gigsoft.secondoblog.dto.BoardDto;
import org.gigsoft.secondoblog.dto.MemberDto;
import org.gigsoft.secondoblog.service.BoardService;
import org.gigsoft.secondoblog.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private final BoardService boardService;
	private final MemberService memberService;	
	
	@GetMapping({"", "/"})
	public String index(
			@AuthenticationPrincipal 
			UserDetails userDetails,
			@PageableDefault(size=3, sort="id", direction=Sort.Direction.DESC)
			Pageable pageable,
			Model model
	) {
		if (userDetails != null && userDetails.isAccountNonExpired()) {
			logger.info("Logged in by {}", userDetails.getUsername());
		}
		
		model.addAttribute("articles", boardService.listArticles(pageable));
		
		return "index";
	}
	
	@GetMapping("/board/form")
	public String write() {
		return "board/writeForm";
	}
	
	@GetMapping("/board/{id}")
	public String findOne(@PathVariable Long id, Model model) {
		BoardDto articleDto = BoardDto.createBoardDto(boardService.findOne(id));
		articleDto.replies().stream().forEach(reply -> logger.info("content: {}", reply.getContent()));		
		
		MemberDto memberDto = MemberDto.createUserDto(memberService.findById(articleDto.member().getId()));
		logger.info("title:  {}, content: {}", articleDto.title(), articleDto.content());
		model.addAttribute("article", articleDto);
		model.addAttribute("member", memberDto);		
		return "board/detail";
	}
}
