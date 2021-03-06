package com.metlin.blog.service;

import com.metlin.blog.model.BlogUser;
import com.metlin.blog.model.RoleType;
import com.metlin.blog.repository.BlogUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class BlogUserService {
	@Value("${cos.key}")
	private String cosKey;

	private final BlogUserRepository blogUserRepository;
	private final BCryptPasswordEncoder encoder;
	private final AuthenticationManager authenticationManager;

	@Transactional
	public int 회원가입(BlogUser user) {
		String rawPassword = user.getPassword();
		String encPasswrod = encoder.encode(rawPassword);  //해쉬
		
		try {			
			Calendar cal = Calendar.getInstance();
			Timestamp currentTimestamp = new Timestamp(cal.getTime().getTime());
			
			user.setPassword(encPasswrod);
			user.setRole(RoleType.USER);
			user.setUpdateDt(currentTimestamp);

			blogUserRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BlogUserService : 회원가입() : " + e.getMessage());
		} 
		return -1;
	}
	
	@Transactional
	public void 회원수정(BlogUser user) {
		BlogUser srcUser = blogUserRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});

		if ("".equals(srcUser.getOauth()) || srcUser.getOauth() == null) {
			String encPassword = encoder.encode(user.getPassword());
			srcUser.setPassword(encPassword);
		} else {
			user.setPassword(cosKey);
		}

		Calendar cal = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(cal.getTime().getTime());
		srcUser.setUpdateDt(currentTimestamp);

		srcUser.setEmail(user.getEmail());

		//세션 변경
		Authentication authentication =
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	@Transactional(readOnly = true)
	public BlogUser 회원찾기(String username) {
		BlogUser blogUser = blogUserRepository.findByUsername(username).orElseGet(() -> new BlogUser());

		return blogUser;
	}
}
 