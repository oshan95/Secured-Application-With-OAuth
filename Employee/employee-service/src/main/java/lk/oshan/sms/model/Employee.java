package lk.oshan.sms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer id;
	 String name;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 Address address;
	 
	 @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 List<Telephone> telephones;
	 
	 //tells not to put on db
	 @Transient
	 Allocation[] allocations;
	 
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "employee_project",
	joinColumns = @JoinColumn(name="eid",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="pid", referencedColumnName = "id"))
	@Fetch(value = FetchMode.SUBSELECT)
	 List<Project> projects;
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Telephone> getTelephones() {
		return telephones;
	}
	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
//	public List<Allocation> getAllocations() {
//		return allocations;
//	}
//	public void setAllocations(List<Allocation> allocations) {
//		this.allocations = allocations;
//	}
	public Allocation[] getAllocations() {
		return allocations;
	}
	public void setAllocations(Allocation[] allocations) {
		this.allocations = allocations;
	}
	
	
	
	
	
	
	
	 
	 
}
