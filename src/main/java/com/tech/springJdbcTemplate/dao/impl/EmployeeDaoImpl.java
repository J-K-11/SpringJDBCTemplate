package com.tech.springJdbcTemplate.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tech.springJdbcTemplate.dao.EmployeeDao;
import com.tech.springJdbcTemplate.domain.Employee;
import com.tech.springJdbcTemplate.helpers.EmployeeNameRowMapper;
import com.tech.springJdbcTemplate.helpers.EmployeeRowMapper;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void saveEmployee(Employee employee) {
		String insertquery = "Insert into employee (id, name, age) values (?,?,?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(insertquery, new Object[] {employee.getId(), employee.getName(), employee.getAge()});
		
	}

	@Override
	public void deleteEmployee(Integer id) {
		String deleteQuery = "delete from employee where id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(deleteQuery, new Object[] {id});
		
	}
		
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from employee");
		for (Map<String, Object> row : rows) {
			Employee emp = new Employee();
			emp.setId((int)row.get("id"));
			emp.setAge((int) row.get("age"));
			emp.setName((String) row.get("name"));
			employees.add(emp);
		}
	return employees;
	}

	@Override
	public Employee findById(Integer id) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Employee emp = jdbcTemplate.queryForObject("select * from employee where id= ?", new Object[] { id }, new EmployeeRowMapper());
		return emp;
	}

	@Override
	public List<Employee> findByAge(Integer age) {
		List<Employee> employees = new ArrayList<>();
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rows= jdbcTemplate.queryForList("select * from employee where age= ?", new Object[] { age });
		for (Map<String, Object> row : rows) {
			Employee emp = new Employee();
			emp.setId((int)row.get("id"));
			emp.setAge((int) row.get("age"));
			emp.setName((String) row.get("name"));
			employees.add(emp);
		}
	return employees;
	}
	
	
	
	@Override
	public String findNameById(Integer id) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<String> employeeName= jdbcTemplate.query("select name from employee where id= ?", new Object[] { id }, new EmployeeNameRowMapper());
		if ( employeeName.isEmpty() || employeeName == null){
			  return null;
		}else { 
			  return employeeName.get(0);
		}
	}
	
	@Override
	public void batchInsertEmployees(final List<Employee> employees){
		jdbcTemplate = new JdbcTemplate(dataSource);
		int [] numberOfRowsadded =jdbcTemplate.batchUpdate("insert into employee (id, name, age) values (?, ?, ?)", new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setInt(1, employee.getId());
				ps.setString(2, employee.getName());
				ps.setInt(3, employee.getAge());
			}
			
			@Override
			public int getBatchSize() {
				return employees.size();
			}
		});
		
	}
	
}
