package lk.oshan.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lk.oshan.sms.model.Address;
import lk.oshan.sms.model.Employee;
import lk.oshan.sms.model.Project;
import lk.oshan.sms.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping(value = "/emscloud")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
//	@Bean
//	@LoadBalanced
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		builder.build();
//	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@RequestMapping(value = "/employee",method = RequestMethod.POST)
	public Employee Save(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}
	
	@RequestMapping(value = "/employee",method = RequestMethod.GET)
	public List<Employee> fetchAllEmployees() {
		return employeeService.fetchAllEmployee();
	}
	
	@RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
	public ResponseEntity<Employee> fetctEmployee(@PathVariable Integer id) {
		Employee employee = new Employee();
		employee.setId(id);
		Employee employee2 =employeeService.fetchEmployee(employee);
		if(employee2==null) {
			 return ResponseEntity.notFound().build();
		 }else{
			 return ResponseEntity.ok(employee2);
}
	}
	
	@RequestMapping(value = "/employee/{id}/project",method = RequestMethod.GET)
	public ResponseEntity<List<Project>> fetchAllEmployeeProjects(@PathVariable Integer id) {
		Employee employee = new Employee();
		employee.setId(id);
		Employee employee2 =employeeService.fetchEmployee(employee);
		if(employee2==null) {
			 return ResponseEntity.notFound().build();
		 }else{
			 return ResponseEntity.ok(employee2.getProjects());
}
	}

	@RequestMapping(value = "/test")
	public Employee test() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Tahrindu");
		Address address = new Address();
		address.setId(1);
		address.setAddress1("dalugama");
		address.setCity("colombo");
		employee.setAddress(address);
		return employee;
	}
}
