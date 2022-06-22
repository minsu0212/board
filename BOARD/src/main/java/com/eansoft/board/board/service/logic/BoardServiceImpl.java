package com.eansoft.board.board.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eansoft.board.board.domain.Board;
import com.eansoft.board.board.domain.BoardFile;
import com.eansoft.board.board.domain.Reply;
import com.eansoft.board.board.service.BoardService;
import com.eansoft.board.board.store.BoardStore;
import com.eansoft.board.common.PageInfo;
import com.eansoft.board.common.Search;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardStore bStore;
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int boardRegister(Board board) {
		int result = bStore.boardRegister(sqlSession, board);
		return result;
	}

	@Override
	public int saveFile(BoardFile boardFile) {
		int result = bStore.saveFile(sqlSession, boardFile);
		return result;
	}

	@Override
	public int getBoardCount(Board board) {
		int totalCount = bStore.selectBoardCount(sqlSession, board);
		return totalCount;
	}

	@Override
	public List<Board> printBoard(Board board, PageInfo pi) {
		List<Board> bList = bStore.selectBoard(sqlSession, board, pi);
		return bList;
	}

	@Override
	public Board printOneByNo(int boardNo) {
		Board board = bStore.selectOneByNo(sqlSession, boardNo);
		return board;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int result = bStore.deleteBoard(sqlSession, boardNo);
		return result;
	}

	@Override
	public void updateCount(int boardNo) {
		bStore.updateCount(sqlSession, boardNo);
	}

	@Override
	public List<Board> searchBoard(Search search, PageInfo pi) {
		List<Board> bList = bStore.searchBoard(sqlSession, search, pi);
		return bList;
	}

	@Override
	public List<Reply> printAllReply(Reply reply) {
		List<Reply> rList = bStore.selectAllReply(sqlSession, reply);
		return rList;
	}

	@Override
	public int registerReply(Reply reply) {
		int result = bStore.insertReply(sqlSession, reply);
		return result;
	}

	@Override
	public int deleteReply(int replyNo) {
		int result = bStore.deleteReply(sqlSession, replyNo);
		return result;
	}

	@Override
	public int modifyReply(Reply reply) {
		int result = bStore.updateReply(sqlSession, reply);
		return result;
	}

	@Override
	public int addReReply(Reply reply) {
		int result = bStore.insertReReply(sqlSession, reply);
		return result;
	}

	@Override
	public int getSearchCount(Search search) {
		int totalCount = bStore.selectSearchCount(sqlSession, search);
		return totalCount;
	}

	@Override
	public List<Board> printBoard() {
		List<Board> bList = bStore.selectBoard(sqlSession);
		return bList;
	}

	@Override
	public int modifyBoard(Board board) {
		int result = bStore.updateBoard(sqlSession, board);
		return result;
	}

}
