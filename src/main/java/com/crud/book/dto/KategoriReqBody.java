package com.crud.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KategoriReqBody {

	private Long id;
	private String namaKategori;

}
