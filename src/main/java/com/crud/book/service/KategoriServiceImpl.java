package com.crud.book.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crud.book.dto.FormatKategoriResponse;
import com.crud.book.dto.KategoriResponse;
import com.crud.book.mapper.KategoriMapper;
import com.crud.book.model.KategoriEntity;
import com.crud.book.repository.KategoriRepository;

@Service
public class KategoriServiceImpl implements KategoriService {

	private final KategoriRepository kategoriRepository;
	private final KategoriMapper kategoriMapper;

	@Autowired
	public KategoriServiceImpl(KategoriRepository kategoriRepository, KategoriMapper kategoriMapper) {
		this.kategoriRepository = kategoriRepository;
		this.kategoriMapper = kategoriMapper;
	}



	@Override
	public KategoriEntity addKategori(KategoriEntity request) {
		return kategoriRepository.save(request);
	}

	@Override
	public Boolean updateKategori(KategoriEntity request, Long id) {
		Optional<KategoriEntity> optionalKategori = kategoriRepository.findById(id);
		if (optionalKategori.isPresent()) {
			KategoriEntity existingKategori = optionalKategori.get();
			existingKategori.setNamaKategori(request.getNamaKategori());
			kategoriRepository.save(existingKategori);
			return true;
		}

		return false;

	}

	@Override
	public Boolean deleteKategori(long id) {
		Optional<KategoriEntity> optionalKategori = kategoriRepository.findById(id);
		if(optionalKategori.isPresent()) {
			kategoriRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public FormatKategoriResponse ListKategori() {
		// TODO Auto-generated method stub

		Pageable page = Pageable.ofSize(10);
		Page<KategoriEntity> kategori = kategoriRepository.findAll(page);

		List<KategoriResponse> kategoriList = kategori.stream()
				.map(map -> kategoriMapper.toDto(map))
				.collect(Collectors.toList());

		FormatKategoriResponse formatKategoriResponse = new FormatKategoriResponse();

		Date now = new Date();
        String timestamp= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",Locale.UK).format(now);
        formatKategoriResponse.setTimestamp(timestamp);
		formatKategoriResponse.setData(kategoriList);
		return formatKategoriResponse;



	}



}
