package com.devsuperior.bds01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAllPaged(Pageable pageable) {
		Page<Employee> list = repository.findAll(pageable);
		return list.map(x -> new EmployeeDTO(x));
	}
	
	@Transactional
	public EmployeeDTO insert(EmployeeDTO dto) {
		Employee emp = new Employee();
		emp.setName(dto.getName());
		emp.setEmail(dto.getEmail());
		emp.setDepartment(new Department(dto.getDepartmentId(), null)); // nome do dep. pode ser null
		emp = repository.save(emp);
		return new EmployeeDTO(emp);
	}
}
