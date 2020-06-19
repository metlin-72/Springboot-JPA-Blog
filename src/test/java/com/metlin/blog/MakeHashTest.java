package com.metlin.blog;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MakeHashTest {

	@Test
	public void 해쉬_암호화() {
		String encPassword = new BCryptPasswordEncoder().encode("1234");
		System.out.println("1234 해쉬: " + encPassword);
	}
		
}
