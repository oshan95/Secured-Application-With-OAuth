package com.virtusa.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.ams.modal.Allocation;

public interface AllocationRepository extends JpaRepository<Allocation, Integer> {
	List<Allocation> findByEmpId(Integer id);
}
