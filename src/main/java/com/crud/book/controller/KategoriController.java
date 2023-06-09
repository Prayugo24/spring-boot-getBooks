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

import com.crud.book.dto.FormatKategoriResponse;
import com.crud.book.model.KategoriEntity;
import com.crud.book.service.KategoriService;

@RestController
@RequestMapping("/kategori")
public class KategoriController {

	KategoriService kategoriSrvice;

	@Autowired
	public KategoriController(KategoriService kategoriSrvice) {
		this.kategoriSrvice = kategoriSrvice;
	}

	@GetMapping("/")
	public FormatKategoriResponse listKategori() {
		return kategoriSrvice.ListKategori();
	}

	@PostMapping("/add-kategori")
	public KategoriEntity addKategori(@RequestBody KategoriEntity body) {
		return kategoriSrvice.addKategori(body);
	}

	@PutMapping("/update-kategori/{id}")
	public Boolean updateKategori(@RequestBody KategoriEntity request, @PathVariable Long id) {
		return kategoriSrvice.updateKategori(request, id);
	}

	@DeleteMapping("/delete-kategori/{id}")
	public Boolean deleteKategori(@PathVariable Long id) {
		return kategoriSrvice.deleteKategori(id);
	}








}
