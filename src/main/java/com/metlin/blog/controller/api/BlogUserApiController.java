package com.metlin.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metlin.blog.dto.ResponseDto;
import com.metlin.blog.model.BlogUser;
import com.metlin.blog.service.BlogUserService;

@RestController
public class BlogUserApiController {
	@Autowired
	private BlogUserService blogUserService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody BlogUser user) {
		int result = blogUserService.회원가입(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
}
