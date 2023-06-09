package com.crud.book.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.book.dto.BukuResponse;
import com.crud.book.dto.FormatBukuResponse;
import com.crud.book.mapper.BukuMapper;
import com.crud.book.model.BukuEntity;
import com.crud.book.repository.BukuRepository;

@Service
public class BukuServiceImpl implements BukuService {

	private final BukuRepository bukuRepository;
	private final BukuMapper bukuMapper;

	@Autowired
	public BukuServiceImpl(BukuRepository bukuRepository, BukuMapper bukuMapper) {
		this.bukuRepository = bukuRepository;
		this.bukuMapper = bukuMapper;
	}

	@Override
	public BukuEntity addBuku(BukuEntity request) {
		// TODO Auto-generated method stub
		return bukuRepository.save(request);
	}

	@Override
	public Boolean editBuku(BukuEntity request, Long id) {
		Optional<BukuEntity> optionalBuku =  bukuRepository.findById(id);
		if(optionalBuku.isPresent()) {
			BukuEntity existingBuku = optionalBuku.get();
//			existingBuku.setIsbn(request.getIsbn());
//			existingBuku.setJudulBuku(request.getJudulBuku());
//			existingBuku.setJumlahHalaman(request.getJumlahHalaman());
//			existingBuku.setNomorRak(request.getNomorRak());
//			existingBuku.setPenerbit(request.getPenerbit());
//			existingBuku.setIdPenerbit(request.getIdPenerbit());
//			bukuRepository.save(existingBuku);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteBuku(Long id) {
		Optional<BukuEntity> optionalBuku = bukuRepository.findById(id);
		if(optionalBuku.isPresent()) {
			bukuRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public FormatBukuResponse ListBuku() {
		// TODO Auto-generated method stub
//			List<BukuEntity> bukuEntities = bukuRepository.findAll();
//			List<BukuResponse> responseList = bukuEntities.stream()
//                .map(bukuMapper::toDto)
//                .collect(Collectors.toList());

			List<BukuEntity> bukuEntities = bukuRepository.findAllWithKategori();
	        List<BukuResponse> bukuResponses = new ArrayList<>();
	        for (BukuEntity bukuEntity : bukuEntities) {
	            bukuResponses.add(bukuMapper.toDtoWithKategori(bukuEntity));
	        }


	        FormatBukuResponse formatBukuResponse = new FormatBukuResponse();
	        Date now = new Date();
	        String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",Locale.UK).format(now);
	        formatBukuResponse.setTimestamp(timestamp);
	        formatBukuResponse.setData(bukuResponses);
	        return formatBukuResponse;

	}

}
