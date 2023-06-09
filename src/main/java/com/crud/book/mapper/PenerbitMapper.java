package com.crud.book.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.crud.book.dto.PenerbitResponse;
import com.crud.book.model.PenerbitEntity;

@Component
@Mapper(componentModel = "spring")
public interface PenerbitMapper {
	PenerbitMapper INSTANCE = Mappers.getMapper(PenerbitMapper.class);

//	@Mapping(source ="namaPenerbit", target="penerbit")
	public PenerbitResponse toDto(PenerbitEntity detailPenerbit);
	public PenerbitEntity toEntity(PenerbitResponse detailPenerbitResponse);

	public List<PenerbitEntity> toPenerbitEntity(List<PenerbitResponse> penerbitDTO);
	public List<PenerbitResponse> toPenerbitDTO(List<PenerbitEntity> penerbitEntity);
}
