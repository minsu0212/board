package com.eansoft.board.employee.store;

import org.apache.ibatis.session.SqlSession;

import com.eansoft.board.employee.domain.Employee;

public interface EmployeeStore {

	int registerEmployee(SqlSession sqlSession, Employee employee);

	Employee loginEmployee(SqlSession sqlSession, Employee employee);

}
