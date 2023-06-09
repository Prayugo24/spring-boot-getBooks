package com.crud.book.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.crud.book.dto.KategoriResponse;
import com.crud.book.model.KategoriEntity;

@Component
public class KategoriMapperImpl implements KategoriMapper{

	@Override
	public KategoriResponse toDto(KategoriEntity detailKategori) {
		// TODO Auto-generated method stub
		if(detailKategori == null) {
			return null;
		}
		KategoriResponse kategoriResponse = new KategoriResponse();

		kategoriResponse.setId( detailKategori.getId());
		kategoriResponse.setNamaKategori(detailKategori.getNamaKategori());

		return kategoriResponse;
	}

	@Override
	public KategoriEntity toEntity(KategoriResponse detailKategoriResponse) {
		// TODO Auto-generated method stub
		if(detailKategoriResponse == null) {
			return null;
		}

		KategoriEntity kategoriEntity = new KategoriEntity();

		kategoriEntity.setId(detailKategoriResponse.getId());
		kategoriEntity.setNamaKategori(detailKategoriResponse.getNamaKategori());

		return kategoriEntity;

	}

	@Override
	public List<KategoriEntity> toKategoriEntity(List<KategoriResponse> kategoriDTO) {
		// TODO Auto-generated method stub
		if(kategoriDTO == null) {
			return null;
		}

        return kategoriDTO.stream().map(this::toEntity).collect(Collectors.toList());


	}

	@Override
	public List<KategoriResponse> toKategoriDTO(List<KategoriEntity> kategoryEntity) {
		// TODO Auto-generated method stub
		if (kategoryEntity == null) {
            return null;
        }

        return kategoryEntity.stream().map(this::toDto).collect(Collectors.toList());
	}

}
