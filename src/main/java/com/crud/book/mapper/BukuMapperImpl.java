package com.crud.book.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crud.book.dto.BukuResponse;
import com.crud.book.model.BukuEntity;

@Component
public class BukuMapperImpl implements BukuMapper {


	@Autowired
	private KategoriMapper kategoriMapper;



	@Override
	public BukuResponse toDto(BukuEntity bukuEntity) {
		// TODO Auto-generated method stub
//		BukuResponse bukuResponse = new BukuResponse();
//		bukuResponse.setId(bukuEntity.getId());
//        bukuResponse.setJudulBuku(bukuEntity.getJudulBuku());
//        bukuResponse.setPengarang(bukuEntity.getPengarang());
//        bukuResponse.setKategori(kategoriMapper.toDto(bukuEntity.getKategori()));

//		menggunakan modelMapper
		ModelMapper modelMapper = new ModelMapper();
		BukuResponse bukuResponse = modelMapper.map(bukuEntity, BukuResponse.class);
        return bukuResponse;
	}


	@Override
	public BukuEntity toEntity(BukuResponse bukuResponse) {
		// TODO Auto-generated method stub
		BukuEntity bukuEntity = new BukuEntity();
        bukuEntity.setId(bukuResponse.getId());
        bukuEntity.setJudulBuku(bukuResponse.getJudulBuku());
        bukuEntity.setPengarang(bukuResponse.getPengarang());
        bukuEntity.setKategori(kategoriMapper.toEntity(bukuResponse.getKategori()));
        return bukuEntity;
	}


	@Override
	public BukuResponse toDtoWithKategori(BukuEntity bukuEntity) {
		// TODO Auto-generated method stub
		BukuResponse bukuResponse = toDto(bukuEntity);
        bukuResponse.setKategori(kategoriMapper.toDto(bukuEntity.getKategori()));
        return bukuResponse;
	}





}
