package com.eansoft.board.board.domain;

import java.util.List;

public class Board {
	private int boardNo;
	private String emplId;
	private String boardType;
	private String boardTitle;
	private String boardContents;
	private String boardWriteDate;
	private int boardCount;
	private int boardFileCount;
	private List<BoardFile> fList; // 게시글 첨부파일
	
	public Board() {}

	public Board(int boardNo, String emplId, String boardType, String boardTitle, String boardContents,
			String boardWriteDate, int boardCount, int boardFileCount, List<BoardFile> fList) {
		super();
		this.boardNo = boardNo;
		this.emplId = emplId;
		this.boardType = boardType;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.boardWriteDate = boardWriteDate;
		this.boardCount = boardCount;
		this.boardFileCount = boardFileCount;
		this.fList = fList;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}

	public String getBoardWriteDate() {
		return boardWriteDate;
	}

	public void setBoardWriteDate(String boardWriteDate) {
		this.boardWriteDate = boardWriteDate;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public int getBoardFileCount() {
		return boardFileCount;
	}

	public void setBoardFileCount(int boardFileCount) {
		this.boardFileCount = boardFileCount;
	}

	public List<BoardFile> getfList() {
		return fList;
	}

	public void setfList(List<BoardFile> fList) {
		this.fList = fList;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", emplId=" + emplId + ", boardType=" + boardType + ", boardTitle="
				+ boardTitle + ", boardContents=" + boardContents + ", boardWriteDate=" + boardWriteDate
				+ ", boardCount=" + boardCount + ", boardFileCount=" + boardFileCount + ", fList=" + fList + "]";
	}

}
