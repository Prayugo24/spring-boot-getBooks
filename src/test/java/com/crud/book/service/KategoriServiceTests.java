package com.crud.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.crud.book.model.KategoriEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class KategoriServiceTests {

	@Autowired
	private KategoriService kategoriService;

	@Test
	public void testAddKategori() {
		KategoriEntity kategoriEntity = new KategoriEntity();
		kategoriEntity.setNamaKategori("Action");
		KategoriEntity kategoriResponse = kategoriService.addKategori(kategoriEntity);
		assertEquals("Action", kategoriResponse.getNamaKategori());
	}

}
