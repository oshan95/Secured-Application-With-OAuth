package lk.oshan.sms.hystrix;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import lk.oshan.sms.model.Allocation;
import lk.oshan.sms.model.Employee;

public class AllocationCommand extends HystrixCommand<Allocation[]> {
	
	Employee employee;
	HttpHeaders headers;
	RestTemplate restTemplate;
	
	public AllocationCommand(Employee employee, HttpHeaders headers,RestTemplate restTemplate) {
		super(HystrixCommandGroupKey.Factory.asKey("default"));
		this.employee = employee;
		this.headers = headers;
		this.restTemplate = restTemplate;
	}
	
	@Override
	protected Allocation[] run() throws Exception{
		
		ResponseEntity<Allocation[]> responseEntity;
		HttpEntity<String> entity = new HttpEntity<>(headers);
		responseEntity = restTemplate.exchange("http://Allocation-Service/allocation/employee/"
				.concat(String.valueOf(employee.getId())),HttpMethod.GET,entity,Allocation[].class);
		
		return responseEntity.getBody();
		
	}
	
	@Override
	protected Allocation[] getFallback() {
		return new Allocation[1];
	}
	
	

}
