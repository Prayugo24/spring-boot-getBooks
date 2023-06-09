package com.crud.book.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.crud.book.dto.KategoriResponse;
import com.crud.book.model.KategoriEntity;


@Mapper(componentModel = "spring")
@Component
public interface KategoriMapper {

	KategoriMapper INSTANCE = Mappers.getMapper(KategoriMapper.class);

	@Mapping(source = "namaKategori", target = "kategori")
	public KategoriResponse toDto(KategoriEntity detailKategori);
	public KategoriEntity toEntity(KategoriResponse detailKategoriResponse);

	public List<KategoriEntity> toKategoriEntity(List<KategoriResponse> kategoriDTO);
	public List<KategoriResponse> toKategoriDTO(List<KategoriEntity> kategoryEntity);

}
