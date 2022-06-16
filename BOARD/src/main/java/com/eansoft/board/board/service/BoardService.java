package com.eansoft.board.board.service;

import java.util.List;

import com.eansoft.board.board.domain.Board;
import com.eansoft.board.board.domain.BoardFile;
import com.eansoft.board.common.PageInfo;

public interface BoardService {

	int boardRegister(Board board);

	int saveFile(BoardFile boardFile);

	int getBoardCount(Board board);
	
	List<Board> printBoard(Board board, PageInfo pi);

}
