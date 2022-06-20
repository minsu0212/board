package com.eansoft.board.board.domain;

public class Reply {
	private int replyNo;
	private int boardNo;
	private int parentReplyNo;
	private int replyOrder;
	private int replyDepth;
	private String emplId;
	private String replyContents;
	private String replyWriteDate;
	
	public Reply() {}

	public Reply(int replyNo, int boardNo, int parentReplyNo, int replyOrder, int replyDepth, String emplId,
			String replyContents, String replyWriteDate) {
		super();
		this.replyNo = replyNo;
		this.boardNo = boardNo;
		this.parentReplyNo = parentReplyNo;
		this.replyOrder = replyOrder;
		this.replyDepth = replyDepth;
		this.emplId = emplId;
		this.replyContents = replyContents;
		this.replyWriteDate = replyWriteDate;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getParentReplyNo() {
		return parentReplyNo;
	}

	public void setParentReplyNo(int parentReplyNo) {
		this.parentReplyNo = parentReplyNo;
	}

	public int getReplyOrder() {
		return replyOrder;
	}

	public void setReplyOrder(int replyOrder) {
		this.replyOrder = replyOrder;
	}

	public int getReplyDepth() {
		return replyDepth;
	}

	public void setReplyDepth(int replyDepth) {
		this.replyDepth = replyDepth;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getReplyContents() {
		return replyContents;
	}

	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}

	public String getReplyWriteDate() {
		return replyWriteDate;
	}

	public void setReplyWriteDate(String replyWriteDate) {
		this.replyWriteDate = replyWriteDate;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", boardNo=" + boardNo + ", parentReplyNo=" + parentReplyNo
				+ ", replyOrder=" + replyOrder + ", replyDepth=" + replyDepth + ", emplId=" + emplId
				+ ", replyContents=" + replyContents + ", replyWriteDate=" + replyWriteDate + "]";
	}
	
}
