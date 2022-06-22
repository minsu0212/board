package com.eansoft.board.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.eansoft.board.board.domain.Board;
import com.eansoft.board.board.domain.BoardFile;
import com.eansoft.board.board.domain.Reply;
import com.eansoft.board.common.PageInfo;
import com.eansoft.board.common.Search;

public interface BoardStore {

	int boardRegister(SqlSession sqlSession, Board board);

	int saveFile(SqlSession sqlSession, BoardFile boardFile);

	int selectBoardCount(SqlSession sqlSession, Board board);

	List<Board> selectBoard(SqlSession sqlSession, Board board, PageInfo pi);

	Board selectOneByNo(SqlSession sqlSession, int boardNo);

	int deleteBoard(SqlSession sqlSession, int boardNo);

	void updateCount(SqlSession sqlSession, int boardNo);

	List<Board> searchBoard(SqlSession sqlSession, Search search, PageInfo pi);

	List<Reply> selectAllReply(SqlSession sqlSession, Reply reply);

	int insertReply(SqlSession sqlSession, Reply reply);

	int deleteReply(SqlSession sqlSession, int replyNo);

	int updateReply(SqlSession sqlSession, Reply reply);

	int insertReReply(SqlSession sqlSession, Reply reply);

	int selectSearchCount(SqlSession sqlSession, Search search);

	List<Board> selectBoard(SqlSession sqlSession);

	int updateBoard(SqlSession sqlSession, Board board);

}
