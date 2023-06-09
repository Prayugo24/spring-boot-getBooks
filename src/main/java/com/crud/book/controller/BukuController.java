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

import com.crud.book.dto.FormatBukuResponse;
import com.crud.book.model.BukuEntity;
import com.crud.book.service.BukuService;

@RestController
@RequestMapping("/buku")
public class BukuController {

	BukuService bukuService;

	@Autowired
	public BukuController(BukuService bukuService) {
		this.bukuService = bukuService;
	}

	@PostMapping("/add-buku")
	public BukuEntity addBuku(@RequestBody BukuEntity request) {
		return bukuService.addBuku(request);
	}

	@PutMapping("/edit-buku/{id}")
	public Boolean editBuku(@RequestBody BukuEntity request, @PathVariable Long id) {
		return bukuService.editBuku(request, id);
	}

	@DeleteMapping("/delete-buku/{id}")
	public Boolean deleteBuku(@PathVariable Long id) {
		return bukuService.deleteBuku(id);
	}

	@GetMapping("/")
	public FormatBukuResponse ListBuku() {
		return bukuService.ListBuku();
	}





}
