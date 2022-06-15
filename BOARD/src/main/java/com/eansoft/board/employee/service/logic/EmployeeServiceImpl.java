package com.eansoft.board.employee.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eansoft.board.employee.domain.Employee;
import com.eansoft.board.employee.service.EmployeeService;
import com.eansoft.board.employee.store.EmployeeStore;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private EmployeeStore eStore;
	
	@Override
	public int registerEmployee(Employee employee) {
		int result = eStore.registerEmployee(sqlSession, employee);
		return result;
	}
	
}
