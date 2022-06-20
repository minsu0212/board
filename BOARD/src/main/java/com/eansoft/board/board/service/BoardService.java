package com.eansoft.board.board.service;

import java.util.List;

import com.eansoft.board.board.domain.Board;
import com.eansoft.board.board.domain.BoardFile;
import com.eansoft.board.board.domain.Reply;
import com.eansoft.board.common.PageInfo;
import com.eansoft.board.common.Search;

public interface BoardService {

	int boardRegister(Board board);

	int saveFile(BoardFile boardFile);

	int getBoardCount(Board board);
	
	List<Board> printBoard(Board board, PageInfo pi);

	Board printOneByNo(int boardNo);

	int deleteBoard(int boardNo);

	void updateCount(int boardNo);

	List<Board> searchBoard(Search search);

	List<Reply> printAllReply(Reply reply);

	int registerReply(Reply reply);

}
