package com.eansoft.board.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.eansoft.board.board.domain.Board;
import com.eansoft.board.board.domain.BoardFile;
import com.eansoft.board.common.PageInfo;

public interface BoardStore {

	int boardRegister(SqlSession sqlSession, Board board);

	int saveFile(SqlSession sqlSession, BoardFile boardFile);

	int selectBoardCount(SqlSession sqlSession, Board board);

	List<Board> selectBoard(SqlSession sqlSession, Board board, PageInfo pi);

}
