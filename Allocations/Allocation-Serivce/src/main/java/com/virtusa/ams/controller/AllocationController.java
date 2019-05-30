package com.virtusa.ams.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.ams.modal.Allocation;
import com.virtusa.ams.service.AllocationService;

@RestController
@RequestMapping(value = "/allocation")
public class AllocationController {

	@Autowired
	AllocationService allocationService;
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ResponseEntity<List<Allocation>> fetchAll() {
		return ResponseEntity.ok(allocationService.fetchAll());
	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Allocation>> fetchOne(@PathVariable Integer id){
		System.out.println("Request reached");
		return ResponseEntity.ok(allocationService.fetchOne(id));
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ResponseEntity<Allocation> save(@RequestBody Allocation allocation){
		return ResponseEntity.ok(allocationService.save(allocation));
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public List<Allocation> fetchByEmpId(@PathVariable Integer id){
		return allocationService.findByEmpId(id);
	}
	
}
