package com.eansoft.board.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.eansoft.board.board.domain.Board;
import com.eansoft.board.board.domain.BoardFile;
import com.eansoft.board.board.domain.Reply;
import com.eansoft.board.board.store.BoardStore;
import com.eansoft.board.common.PageInfo;
import com.eansoft.board.common.Search;

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

	@Override
	public List<Board> searchBoard(SqlSession sqlSession, Search search, PageInfo pi) {
		int limit = pi.getListLimit();
		int currentPage = pi.getCurrentPage();
		int offset = (currentPage - 1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Board> bList = sqlSession.selectList("BoardMapper.searchBoard", search, rowBounds);
		return bList;
	}

	@Override
	public List<Reply> selectAllReply(SqlSession sqlSession, Reply reply) {
		List<Reply> rList = sqlSession.selectList("BoardMapper.selectAllReply", reply);
		return rList;
	}

	@Override
	public int insertReply(SqlSession sqlSession, Reply reply) {
		int result = sqlSession.insert("BoardMapper.insertReply", reply);
		return result;
	}

	@Override
	public int deleteReply(SqlSession sqlSession, int replyNo) {
		int result = sqlSession.delete("BoardMapper.deleteReply", replyNo);
		return result;
	}

	@Override
	public int updateReply(SqlSession sqlSession, Reply reply) {
		int result = sqlSession.update("BoardMapper.updateReply", reply);
		return result;
	}

	@Override
	public int insertReReply(SqlSession sqlSession, Reply reply) {
		int result = sqlSession.insert("BoardMapper.insertReReply", reply);
		return result;
	}

	@Override
	public int selectSearchCount(SqlSession sqlSession, Search search) {
		int totalCount = sqlSession.selectOne("BoardMapper.selectSearchCount", search);
		return totalCount;
	}

	@Override
	public List<Board> selectBoard(SqlSession sqlSession) {
		List<Board> bList = sqlSession.selectList("BoardMapper.selectBoard");
		return bList;
	}

	@Override
	public int updateBoard(SqlSession sqlSession, Board board) {
		int result = sqlSession.update("BoardMapper.updateBoard", board);
		return result;
	}

	@Override
	public List<Board> searchBoardExcel(SqlSession sqlSession, Search search) {
		List<Board> bList = sqlSession.selectList("BoardMapper.searchBoard", search);
		return bList;
	}

}
