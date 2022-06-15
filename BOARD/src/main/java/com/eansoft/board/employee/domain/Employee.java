package com.eansoft.board.employee.domain;

public class Employee {
	private String emplId;
	private String emplPw;
	private String emplName;
	
	public Employee() {}

	public Employee(String emplId, String emplPw, String emplName) {
		super();
		this.emplId = emplId;
		this.emplPw = emplPw;
		this.emplName = emplName;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getEmplPw() {
		return emplPw;
	}

	public void setEmplPw(String emplPw) {
		this.emplPw = emplPw;
	}

	public String getEmplName() {
		return emplName;
	}

	public void setEmplName(String emplName) {
		this.emplName = emplName;
	}

	@Override
	public String toString() {
		return "Employee [emplId=" + emplId + ", emplPw=" + emplPw + ", emplName=" + emplName + "]";
	}
	
}
