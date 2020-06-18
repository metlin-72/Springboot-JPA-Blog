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
}
 