package com.metlin.blog.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metlin.blog.model.BlogUser;

// @Repository // 생략 가능하다.
public interface BlogUserRepository extends JpaRepository<BlogUser, Integer>{
	// SELECT * FROM blogUser WHERE username = 1?;
	Optional<BlogUser> findByUsername(String username);
	
	//	// SELECT * FROM blogUser WHERE username = ?1 and password = ?2;
	//	BlogUser findByUsernameAndPassword(String username, String password);
	//	
	//	@Query(value = "SELECT * FROM blogUser WHERE username = ?1 and password = ?2", nativeQuery = true)
	//	BlogUser loign(String username, String password);
	
	
}
