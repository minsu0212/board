package com.eansoft.board.employee.service;

import com.eansoft.board.employee.domain.Employee;

public interface EmployeeService {

	int registerEmployee(Employee employee);

	Employee loginEmployee(Employee employee);

}
