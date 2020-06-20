package com.metlin.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metlin.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
