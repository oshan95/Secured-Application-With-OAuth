package lk.oshan.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lk.oshan.sms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
