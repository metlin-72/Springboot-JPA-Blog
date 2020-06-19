package com.metlin.blog.service;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metlin.blog.model.BlogUser;
import com.metlin.blog.model.RoleType;
import com.metlin.blog.repository.BlogUserRepository;

@Service
public class BlogUserService {
	
	@Autowired
	private BlogUserRepository blogUserRepository;
	
	@Transactional
	public int 회원가입(BlogUser user) {
		System.out.println("BlogUserService.회원가입()");
		
		try {
			Calendar cal = Calendar.getInstance();
			Timestamp currentTimestamp = new Timestamp(cal.getTime().getTime());
			
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
	
//	@Transactional(readOnly = true)  // SELECT할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성) 
//	public BlogUser 로그인(BlogUser user) {
//		return blogUserRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
 