package com.metlin.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @DynamicInsert  // insert시에 null인 필드를 제외시켜준다
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BlogUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;

	@Column(nullable = false, unique = true, length = 50)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = true, length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)  //DB는 RoleType이라는게 없다
	private RoleType role;     //ENUM을 쓰는게 좋다  //USER, ADMIN
	
	@CreationTimestamp
	private Timestamp regDt;
	
	private Timestamp updateDt;
	
//	@Builder
//	public User(String id, int seq, String username, String password, String email) {
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.email = email;
//	}

}
