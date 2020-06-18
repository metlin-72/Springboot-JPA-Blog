package com.metlin.blog.test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metlin.blog.model.BlogUser;
import com.metlin.blog.model.RoleType;
import com.metlin.blog.repository.BlogUserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired
	private BlogUserRepository blogUserRepository;
	
	// {id} 주소로 파라미터를 전달 받을 수 있음.
	@GetMapping("/dummy/user/{id}")
	public BlogUser detail(@PathVariable int id) {
		BlogUser blogUser =
			blogUserRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
				@Override
				public IllegalArgumentException get() {
					return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
				} 
			});
		
//		 람다식
//		blogUserRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
//		});
		
		return blogUser;
	}
	
	@GetMapping("/dummy/userAll")
	public List<BlogUser> userAll() {
		return blogUserRepository.findAll();
	}
 
	@GetMapping("/dummy/userAllPage")
	public List<BlogUser> pageUserList( @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable ){
		Page<BlogUser> pagingUser = blogUserRepository.findAll(pageable);
		
		List<BlogUser> blogUser = pagingUser.getContent();
		return blogUser;
	}
	
	@PostMapping("/dummy/join")
	public String join(BlogUser blogUser) {
		System.out.println("id: " + blogUser.getId());
		System.out.println("username: " + blogUser.getUsername());
		System.out.println("password: " + blogUser.getPassword());
		System.out.println("email: " + blogUser.getEmail());
		System.out.println("role: " + blogUser.getRole());
		System.out.println("regDt: " + blogUser.getRegDt());
		System.out.println("updateDt: " + blogUser.getUpdateDt());
		
		// 현재 Timestamp 값 가져오기
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		
		blogUser.setUpdateDt(currentTimestamp);
		blogUser.setRole(RoleType.USER);
		blogUserRepository.save(blogUser);
		
		return "회원가입이 완료되었습니다.";
	}
	
	@PutMapping("/dummy/update/{id}")
	@Transactional   //함수 종료시 자동 commit
	public BlogUser updateUser(@PathVariable int id, @RequestBody BlogUser reqUser) {
		System.out.println("id: " + id);
		System.out.println("username: " + reqUser.getUsername());		
		System.out.println("password: " + reqUser.getPassword());		
		System.out.println("email: " + reqUser.getEmail());
		
		BlogUser blogUser = blogUserRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		//현재 Timestamp 값 가져오기
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(calendar.getTime().getTime());
		
		blogUser.setPassword(reqUser.getPassword());
		blogUser.setEmail(reqUser.getEmail());
		blogUser.setUpdateDt(currentTimestamp);
		
//		blogUserRepository.save(blogUser);
		
		//더티 체킹
		return blogUser;
	}
	
	@DeleteMapping("/dummy/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			blogUserRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id: " + id + "은(는) 없습니다.";
		}
		
		return "삭제 되었습니다. id: " + id;
	}
}
