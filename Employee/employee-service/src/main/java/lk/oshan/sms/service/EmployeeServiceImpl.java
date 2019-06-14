package lk.oshan.sms.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

import lk.oshan.sms.hystrix.AllocationCommand;
import lk.oshan.sms.model.Allocation;
import lk.oshan.sms.model.Employee;
import lk.oshan.sms.model.Project;
import lk.oshan.sms.model.Telephone;
import lk.oshan.sms.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RestTemplate restTemplate;
	
	public Employee save(Employee employee) {
		
		for (Telephone telephone : employee.getTelephones()) {
			telephone.setEmployee(employee);
		}
		
		return employeeRepository.save(employee);
	}
	
	public List<Employee> fetchAllEmployee(){
		return employeeRepository.findAll();
	}
	
	@Override
	//@Hystrix
	public Employee fetchEmployee(Employee employee, HttpHeaders headers) {
		Optional<Employee> optional = employeeRepository.findById(employee.getId());
				
		if(optional.isPresent()) {
			
			Employee employee2 = optional.get();
			AllocationCommand allocationCommand = new AllocationCommand(employee, headers, restTemplate);
			Allocation[] allocations = allocationCommand.execute();
			
			employee2.setAllocations(allocations);
			
			return employee2;
			
		}else {
			return null;
		}
	}
	
	
//	@HystrixCommand(fallbackMethod = "fetchEmployeeFallBack")
	public Employee fetchEmployeeBack(Employee employee, HttpHeaders headers) {
		
		Employee employee2 = new Employee();
		employee2.setName("Error");
		return employee2;
	}
	
	
//	@HystrixCommand(fallbackMethod = "fetchEmployeeFallBack")
//	public Allocation[] fetchEmployeeBack(Employee employee) {
//		
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders httpHeaders = new HttpHeaders();
//		
//		OAuth2AuthenticationDetails details =(OAuth2AuthenticationDetails)
//				SecurityContextHolder.getContext().getAuthentication().getDetails();
//		httpHeaders.add("Authorization","bearer ".concat(details.getTokenValue()));
//		System.out.println(details.getTokenValue());
//		
//		ResponseEntity<Allocation[]>responseEntity;
//		HttpEntity<String>entity = new HttpEntity<>("",httpHeaders);
//		responseEntity = restTemplate.exchange("http://Allocation-Service/allocation/employee/"
//				.concat(String.valueOf(employee.getId())),HttpMethod.GET,entity,Allocation[].class);
//		
//		return responseEntity.getBody();
//		
//	}
//	public Allocation[] fetchEmployeeFallBack(Employee employee, HttpHeaders httpHeaders) {
//		return new Allocation[1]; 
//	}

}
