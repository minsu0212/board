package com.eansoft.board.employee.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.eansoft.board.employee.domain.Employee;
import com.eansoft.board.employee.store.EmployeeStore;

@Repository
public class EmployeeStoreLogic implements EmployeeStore {

	@Override
	public int registerEmployee(SqlSession sqlSession, Employee employee) {
		int result = sqlSession.insert("EmployeeMapper.registerEmployee", employee);
		return result;
	}

}
