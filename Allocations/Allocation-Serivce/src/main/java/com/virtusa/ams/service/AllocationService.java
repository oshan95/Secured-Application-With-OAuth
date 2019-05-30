package com.virtusa.ams.service;

import java.util.List;
import java.util.Optional;

import com.virtusa.ams.modal.Allocation;

public interface AllocationService {

	List<Allocation> fetchAll();

	Optional<Allocation> fetchOne(Integer id);

	Allocation save(Allocation allocation);
	
	List<Allocation> findByEmpId(Integer id);

}