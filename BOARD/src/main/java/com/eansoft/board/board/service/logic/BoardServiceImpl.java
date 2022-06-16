package com.eansoft.board.board.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eansoft.board.board.domain.Board;
import com.eansoft.board.board.domain.BoardFile;
import com.eansoft.board.board.service.BoardService;
import com.eansoft.board.board.store.BoardStore;
import com.eansoft.board.common.PageInfo;

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

}
