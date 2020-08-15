package com.metlin.blog.service;

import com.metlin.blog.dto.ReplySaveRequestDto;
import com.metlin.blog.model.BlogUser;
import com.metlin.blog.model.Board;
import com.metlin.blog.model.Reply;
import com.metlin.blog.repository.BlogUserRepository;
import com.metlin.blog.repository.BoardRepository;
import com.metlin.blog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BlogUserRepository blogUserRepository;
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;

	@Transactional
	public void 글쓰기(Board board, BlogUser blogUser) {
		Calendar cal = Calendar.getInstance();
		Timestamp nowTimestamp = new Timestamp(cal.getTime().getTime());
		
		board.setUser(blogUser);
		board.setCount(0);
		board.setRegDt(nowTimestamp);
		board.setUpdateDt(nowTimestamp);
		
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 상세 보기 실패: 게시글을 찾을 수 없습니다."));
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);		
	}
	
	@Transactional
	public void 글수정하기(int id, Board reqBoard) {
		//Board orgBoard = this.글상세보기(id);
		Board orgBoard = boardRepository.findById(id).orElseThrow( ()-> { return new IllegalArgumentException("글 찾기 실패: 게시글을 찾을 수 없습니다."); } );  //영속화 완료
		
		Calendar cal = Calendar.getInstance();
		Timestamp nowTimestamp = new Timestamp(cal.getTime().getTime());
		
		orgBoard.setUpdateDt(nowTimestamp);
		orgBoard.setTitle(reqBoard.getTitle());
		orgBoard.setContent(reqBoard.getContent());

		// 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동업데이트가 됨. DB flush 
	}

	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		BlogUser blogUser = blogUserRepository.findById(replySaveRequestDto.getUserId())
				.orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 사용자를 찾을 수 없습니다."));

		Board board = boardRepository.findById(replySaveRequestDto.getBoardId())
				.orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 게시글을 찾을 수 없습니다."));

		Calendar cal = Calendar.getInstance();
		Timestamp nowTimestamp = new Timestamp(cal.getTime().getTime());

		Reply saveReply = Reply.builder()
							.user(blogUser)
							.board(board)
							.updateDt(nowTimestamp)
							.content(replySaveRequestDto.getContent())
							.build();

		replyRepository.save(saveReply);
	}

	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
 