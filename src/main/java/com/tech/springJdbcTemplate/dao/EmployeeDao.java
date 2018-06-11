package com.tech.springJdbcTemplate.dao;

import java.util.List;

import com.tech.springJdbcTemplate.domain.Employee;

public interface EmployeeDao {
	
	public void saveEmployee(Employee employee);
	
	public void deleteEmployee(Integer id);

	public List<Employee> getAllEmployees();

	public Employee findById(Integer id);
	
	public List<Employee> findByAge(Integer age);
	
	public String findNameById(Integer id);
	
	public void batchInsertEmployees(final List<Employee> employees);
}
