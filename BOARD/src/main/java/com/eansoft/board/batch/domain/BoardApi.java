package com.eansoft.board.batch.domain;

public class BoardApi {
	private int boardNo;
	private String boardType;
	private String boardTitle;
	private String boardContents;
	private String memberId;
	private String writeDate;
	private int boardCount;
	private String boardStatus;
	
	public BoardApi() {}

	public BoardApi(int boardNo, String boardType, String boardTitle, String boardContents, String memberId,
			String writeDate, int boardCount, String boardStatus) {
		super();
		this.boardNo = boardNo;
		this.boardType = boardType;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.memberId = memberId;
		this.writeDate = writeDate;
		this.boardCount = boardCount;
		this.boardStatus = boardStatus;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public String getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}

	@Override
	public String toString() {
		return "BoardApi [boardNo=" + boardNo + ", boardType=" + boardType + ", boardTitle=" + boardTitle
				+ ", boardContents=" + boardContents + ", memberId=" + memberId + ", writeDate=" + writeDate
				+ ", boardCount=" + boardCount + ", boardStatus=" + boardStatus + "]";
	}
	
}
