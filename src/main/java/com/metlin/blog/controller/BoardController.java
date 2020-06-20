package com.metlin.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.metlin.blog.model.Board;
import com.metlin.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	 // 컨트롤러에서 세션을 어떻게 찾는지 ?
	//@AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"", "/"})
	public String index(Model m, @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) { 
		m.addAttribute("boards", boardService.글목록(pageable));
		return "index";    //viewResolver 작동!!!
	}
	
	@GetMapping("/board/{id}")
	public String 글상세보기(@PathVariable int id, Model m) {
		m.addAttribute("board", boardService.글상세보기(id));
		
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String 글수정페이지(@PathVariable int id, Model m) {
		m.addAttribute("board", boardService.글상세보기(id));
		
		return "board/updateForm";
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
