package com.eansoft.board.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.eansoft.board.board.domain.Board;
import com.eansoft.board.board.domain.BoardFile;
import com.eansoft.board.board.store.BoardStore;
import com.eansoft.board.common.PageInfo;

@Repository
public class BoardStoreLogic implements BoardStore {

	@Override
	public int boardRegister(SqlSession sqlSession, Board board) {
		int result = sqlSession.insert("BoardMapper.boardRegister", board);
		return result;
	}

	@Override
	public int saveFile(SqlSession sqlSession, BoardFile boardFile) {
		int result = sqlSession.insert("BoardMapper.saveFile", boardFile);
		return result;
	}

	@Override
	public int selectBoardCount(SqlSession sqlSession, Board board) {
		int totalCount = sqlSession.selectOne("BoardMapper.selectBoardCount", board);
		return totalCount;
	}

	@Override
	public List<Board> selectBoard(SqlSession sqlSession, Board board, PageInfo pi) {
		int limit = pi.getListLimit();
		int currentPage = pi.getCurrentPage();
		int offset = (currentPage - 1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Board> bList = sqlSession.selectList("BoardMapper.selectBoard", board, rowBounds);
		return bList;
	}

	@Override
	public Board selectOneByNo(SqlSession sqlSession, int boardNo) {
		Board board = sqlSession.selectOne("BoardMapper.selectOneByNo", boardNo);
		return board;
	}

	@Override
	public int deleteBoard(SqlSession sqlSession, int boardNo) {
		int result = sqlSession.delete("BoardMapper.deleteBoard", boardNo);
		return result;
	}

	@Override
	public void updateCount(SqlSession sqlSession, int boardNo) {
		sqlSession.update("BoardMapper.updateCount", boardNo);
	}

}
