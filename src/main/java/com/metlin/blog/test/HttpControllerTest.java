package com.metlin.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metlin.blog.model.BlogUser;

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest: ";
	
	@GetMapping("/http/get")
	public String getTest(BlogUser user) {
		return "get 요청: " + user.getId() + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail();
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody BlogUser user) {
		return "post 요청: " + user.getId() + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail();
//		return "post 요청: " + text;
	} 
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody BlogUser user) {
		return "put 요청: " + user.getId() + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest(@RequestBody BlogUser user) {
		return "delete 요청: "  + user.getId() + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail();
	}
	
//	@GetMapping("/http/lombok")
//	public String lombokTest() {
//		User user = User.builder().id("metlin").username("김원기").password("fjqbgurqls1!").build();
//		
//		System.out.println(TAG + user.getId());
//		user.setId("metlin-72");
//		System.out.println(TAG + user.getId());
//		
//		return "lombok test 완료: "  + user.getId() + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail();
//	}
}
