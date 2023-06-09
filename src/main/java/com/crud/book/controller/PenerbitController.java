package com.crud.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.book.dto.FormatPenerbitResponse;
import com.crud.book.model.PenerbitEntity;
import com.crud.book.service.PenerbitService;

@RestController
@RequestMapping("/penerbit")
public class PenerbitController {

	PenerbitService penerbitService;


	@Autowired
	public PenerbitController(PenerbitService penerbitService) {
		this.penerbitService = penerbitService;
	}

	@PostMapping("/add-penerbit")
	public PenerbitEntity addPenerbit(@RequestBody PenerbitEntity request) {
		return penerbitService.addPenerbit(request);
	}

	@PutMapping("/update-penerbit/{id}")
	public boolean updatePenerbit(@RequestBody PenerbitEntity request,@PathVariable Long id) {
		return penerbitService.updatePenerbit(request, id);
	}

	@DeleteMapping("/delete-penerbit/{id}")
	public boolean deletePenerbit(@PathVariable Long id) {
		return penerbitService.deletePenerbit(id);
	}

	@GetMapping("/")
	public FormatPenerbitResponse listPenerbit() {
		return penerbitService.listPenerbit();
	}



}
