package com.crud.book.service;

import com.crud.book.dto.FormatKategoriResponse;
import com.crud.book.model.KategoriEntity;

public interface KategoriService {

	KategoriEntity addKategori(KategoriEntity request);
	Boolean updateKategori(KategoriEntity request, Long id);
	Boolean deleteKategori(long id);
	FormatKategoriResponse ListKategori();

}
