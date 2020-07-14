package com.metlin.blog.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metlin.blog.model.OAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.metlin.blog.dto.ResponseDto;
import com.metlin.blog.model.BlogUser;
import com.metlin.blog.service.BlogUserService;
import org.springframework.web.client.RestTemplate;

@RestController
public class BlogUserApiController {
	@Autowired
	private BlogUserService blogUserService;
	
//	@Autowired
//	private HttpSession session;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody BlogUser user) {
		int result = blogUserService.회원가입(user);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody BlogUser user){
		blogUserService.회원수정(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code){

		// POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
		// Retrofit2
		// OkHttp
		// RestTemplate

		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "f0fd118d74eefd5e1e9a8234c1572acf");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> responseEntity = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, kakaoTokenRequest, String.class);
		//ResponseEntity responseEntity = rt.postForEntity("https://kauth.kakao.com/oauth/token", kakaoTokenRequest, String.class);

		//Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;

		try {
			oAuthToken = objectMapper.readValue(responseEntity.getBody(), OAuthToken.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return responseEntity.getBody();
	}

}
