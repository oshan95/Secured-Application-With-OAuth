package lk.oshan.sms.service;

import java.util.List;

import org.springframework.http.HttpHeaders;

import lk.oshan.sms.model.Employee;

public interface EmployeeService {

		Employee save(Employee employee);
		List<Employee> fetchAllEmployee();
		Employee fetchEmployee(Employee employee, HttpHeaders headers);
}
