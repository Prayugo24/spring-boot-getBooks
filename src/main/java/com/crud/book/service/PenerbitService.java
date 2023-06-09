package com.crud.book.service;

import com.crud.book.dto.FormatPenerbitResponse;
import com.crud.book.model.PenerbitEntity;

public interface PenerbitService {
	PenerbitEntity addPenerbit(PenerbitEntity request);
	boolean updatePenerbit(PenerbitEntity request, Long id);
	boolean deletePenerbit(Long id);
	FormatPenerbitResponse listPenerbit();
}
