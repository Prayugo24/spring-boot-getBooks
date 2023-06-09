package com.crud.book.service;

import com.crud.book.dto.FormatBukuResponse;
import com.crud.book.model.BukuEntity;

public interface BukuService {

	BukuEntity addBuku(BukuEntity request);
	Boolean editBuku(BukuEntity request, Long id);
	Boolean deleteBuku(Long id);
	FormatBukuResponse ListBuku();
}
