package com.crud.book.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.crud.book.dto.PenerbitResponse;
import com.crud.book.model.PenerbitEntity;

@Component
public class PenerbitMapperImpl implements PenerbitMapper {

	@Override
	public PenerbitResponse toDto(PenerbitEntity detailPenerbit) {
		// TODO Auto-generated method stub
		if (detailPenerbit == null) {
			return null;
		}

		PenerbitResponse penerbitResponse = new PenerbitResponse();

		penerbitResponse.setId(detailPenerbit.getId());
		penerbitResponse.setNamePenerbit(detailPenerbit.getNamaPenerbit());
		return penerbitResponse;
	}

	@Override
	public PenerbitEntity toEntity(PenerbitResponse detailPenerbitResponse) {
		// TODO Auto-generated method stub
		if(detailPenerbitResponse == null) {
			return null;
		}

		PenerbitEntity penerbiEntity = new PenerbitEntity();
		penerbiEntity.setId(detailPenerbitResponse.getId());
		penerbiEntity.setNamaPenerbit(detailPenerbitResponse.getNamePenerbit());
		return penerbiEntity;

	}

	@Override
	public List<PenerbitEntity> toPenerbitEntity(List<PenerbitResponse> penerbitDTO) {
		// TODO Auto-generated method stub
		if(penerbitDTO == null) {
			return null;
		}

		return penerbitDTO.stream().map(this::toEntity).collect(Collectors.toList());
	}

	@Override
	public List<PenerbitResponse> toPenerbitDTO(List<PenerbitEntity> penerbitEntity) {
		// TODO Auto-generated method stub
		if(penerbitEntity == null) {
			return null;
		}

		return penerbitEntity.stream().map(this::toDto).collect(Collectors.toList());
	}

}
