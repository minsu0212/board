package com.eansoft.board.board.domain;

public class BoardFile {
	private int fileNo;
	private int boardNo;
	private String fileName;
	private String fileExtension;
	private String fileRename;
	private String filePath;
	private String emplId;
	
	public BoardFile() {}

	public BoardFile(int fileNo, int boardNo, String fileName, String fileExtension, String fileRename, String filePath,
			String emplId) {
		super();
		this.fileNo = fileNo;
		this.boardNo = boardNo;
		this.fileName = fileName;
		this.fileExtension = fileExtension;
		this.fileRename = fileRename;
		this.filePath = filePath;
		this.emplId = emplId;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFileRename() {
		return fileRename;
	}

	public void setFileRename(String fileRename) {
		this.fileRename = fileRename;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	@Override
	public String toString() {
		return "BoardFile [fileNo=" + fileNo + ", boardNo=" + boardNo + ", fileName=" + fileName + ", fileExtension="
				+ fileExtension + ", fileRename=" + fileRename + ", filePath=" + filePath + ", emplId=" + emplId + "]";
	}
	
}
