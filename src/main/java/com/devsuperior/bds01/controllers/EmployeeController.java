package com.devsuperior.bds01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.services.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@GetMapping
	public ResponseEntity<Page<EmployeeDTO>> findAllPaged(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "name") String sort
			) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
		Page<EmployeeDTO> listDto = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(listDto);
	}
}
