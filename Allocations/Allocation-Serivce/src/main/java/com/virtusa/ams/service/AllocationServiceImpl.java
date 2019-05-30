package com.virtusa.ams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ams.modal.Allocation;
import com.virtusa.ams.repository.AllocationRepository;

@Service
public class AllocationServiceImpl implements AllocationService {

	@Autowired
	private AllocationRepository allocationRepository;
	
	@Override
	public List<Allocation> fetchAll() {
		return allocationRepository.findAll();
		
	}
	
	@Override
	public Optional<Allocation> fetchOne(Integer id) {
		return allocationRepository.findById(id);
	}
	
	@Override
	public Allocation save(Allocation allocation) {
		return allocationRepository.save(allocation);
	}
	
	@Override
	public List<Allocation> findByEmpId(Integer id){
		return allocationRepository.findByEmpId(id);
	}
	
}
