package com.eansoft.board.board.domain;

public class BoardType {
	private String boardType;
	private String boardWriteType;
	
	public BoardType() {}

	public BoardType(String boardType, String boardWriteType) {
		super();
		this.boardType = boardType;
		this.boardWriteType = boardWriteType;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getBoardWriteType() {
		return boardWriteType;
	}

	public void setBoardWriteType(String boardWriteType) {
		this.boardWriteType = boardWriteType;
	}

	@Override
	public String toString() {
		return "BoardType [boardType=" + boardType + ", boardWriteType=" + boardWriteType + "]";
	}
	
}
