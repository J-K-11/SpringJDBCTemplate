package com.tech.springJdbcTemplate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.springJdbcTemplate.dao.EmployeeDao;
import com.tech.springJdbcTemplate.domain.Employee;
import com.tech.springJdbcTemplate.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void saveEmployee(Employee employee) {
		employeeDao.saveEmployee(employee);
		
	}

	@Override
	public void deleteEmployee(Integer id) {
		employeeDao.deleteEmployee(id);
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee findById(Integer id) {
		return employeeDao.findById(id);
	}

	@Override
	public List<Employee> findByAge(Integer age) {
		return employeeDao.findByAge(age);
	}

	@Override
	public String findNameById(Integer id) {
		return employeeDao.findNameById(id);
	}

	@Override
	public void batchInsertEmployees(List<Employee> employees) {
		employeeDao.batchInsertEmployees(employees);
		
	}

}
