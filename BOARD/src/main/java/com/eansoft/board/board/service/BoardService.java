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

	List<Board> searchBoard(Search search, PageInfo pi);

	List<Reply> printAllReply(Reply reply);

	int registerReply(Reply reply);

	int deleteReply(int replyNo);

	int modifyReply(Reply reply);

	int addReReply(Reply reply);

	int getSearchCount(Search search);

	List<Board> printBoard();

	int modifyBoard(Board board);

	List<Board> searchBoardExcel(Search search);

}
