package com.metlin.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metlin.blog.model.BlogUser;

// @Repository // 생략 가능하다.
public interface BlogUserRepository extends JpaRepository<BlogUser, Integer>{
	
	
}
