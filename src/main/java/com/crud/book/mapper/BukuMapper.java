package com.crud.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.crud.book.dto.BukuResponse;
import com.crud.book.model.BukuEntity;

@Component
@Mapper(componentModel = "spring")
public interface BukuMapper {
	 BukuMapper INSTANCE = Mappers.getMapper(BukuMapper.class);

	 BukuResponse toDto(BukuEntity bukuEntity);

	 BukuEntity toEntity(BukuResponse bukuResponse);
	 BukuResponse toDtoWithKategori(BukuEntity bukuEntity);
}
