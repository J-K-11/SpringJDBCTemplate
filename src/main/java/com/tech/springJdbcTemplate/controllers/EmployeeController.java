package com.tech.springJdbcTemplate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.springJdbcTemplate.domain.Employee;
import com.tech.springJdbcTemplate.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping(value="/addEmployee", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee){
		
		try {
			employeeService.saveEmployee(employee);
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"Something wrong happened\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("{\"message\":\"Employee saved successfully.\"}", HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/deleteEmployee", method=RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<String> deleteEmployee(@RequestParam("id") Integer id){
		
		try {
			employeeService.deleteEmployee(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"Something wrong happened\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("{\"message\":\"Employee deleted successfully.\"}", HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/getAllEmployees", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getAllEmployees(){
		List<Employee> employees= null;
		try {
			employees = employeeService.getAllEmployees();
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"Something wrong happened\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(employees, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/findEmployeeById", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> findById(@RequestParam("id") Integer id){
		Employee emp = null;
		try {
			emp = employeeService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"Something wrong happened\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(emp, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/findEmployeeByAge", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> findEmployeeByAge(@RequestParam("age") Integer age){
		List<Employee> employees= null;
		try {
			employees = employeeService.findByAge(age);
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"Something wrong happened\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(employees, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/findEmployeeNameById", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> findNameById(@RequestParam("id") Integer id){
		String empName = null;
		try {
			empName = employeeService.findNameById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"Something wrong happened\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("{\"name\":\""+empName+"\"}", HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/batchInsertEmployees", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public ResponseEntity<String> batchInsertEmployees(@RequestBody List<Employee> employees){
		
		try {
			employeeService.batchInsertEmployees(employees);
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"Something wrong happened\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("{\"message\":\"Employees batch inserted successfully.\"}", HttpStatus.OK);
		
	}
	
	
}
