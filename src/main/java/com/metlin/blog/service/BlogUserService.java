package com.metlin.blog.service;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metlin.blog.model.BlogUser;
import com.metlin.blog.model.RoleType;
import com.metlin.blog.repository.BlogUserRepository;

@Service
public class BlogUserService {
	
	@Autowired
	private BlogUserRepository blogUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
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
		
		String rawPassword = srcUser.getPassword();
		String encPassword = encoder.encode(user.getPassword());
		
		Calendar cal = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(cal.getTime().getTime());
		
		srcUser.setPassword(encPassword);
		srcUser.setEmail(user.getEmail());
		srcUser.setUpdateDt(currentTimestamp);
	}
	
//	@Transactional(readOnly = true)  // SELECT할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성) 
//	public BlogUser 로그인(BlogUser user) {
//		return blogUserRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
 