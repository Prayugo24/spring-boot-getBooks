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

import com.crud.book.dto.FormatPenerbitResponse;
import com.crud.book.dto.PenerbitResponse;
import com.crud.book.mapper.PenerbitMapper;
import com.crud.book.model.PenerbitEntity;
import com.crud.book.repository.PenerbitRepository;

@Service
public class PenerbitServiceImpl implements PenerbitService {

	private final PenerbitRepository penerbitRepository;
	private final PenerbitMapper penerbitMapper;

	@Autowired
	public PenerbitServiceImpl(PenerbitRepository penerbitRepository, PenerbitMapper penerbitMapper) {
		this.penerbitRepository = penerbitRepository;
		this.penerbitMapper = penerbitMapper;
	}

	@Override
	public PenerbitEntity addPenerbit(PenerbitEntity request) {
		// TODO Auto-generated method stub
		return penerbitRepository.save(request);
	}

	@Override
	public boolean updatePenerbit(PenerbitEntity request, Long id) {
		Optional<PenerbitEntity> optionalPenerbit = penerbitRepository.findById(id);
		if (optionalPenerbit.isPresent()) {
			PenerbitEntity existPenerbit = optionalPenerbit.get();
			existPenerbit.setNamaPenerbit(request.getNamaPenerbit());
			penerbitRepository.save(existPenerbit);
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePenerbit(Long id) {
		Optional<PenerbitEntity> optionalPenerbit = penerbitRepository.findById(id);
		if (optionalPenerbit.isPresent()) {
			penerbitRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public FormatPenerbitResponse listPenerbit() {
		// TODO Auto-generated method stub
		Pageable page = Pageable.ofSize(10);
		Page<PenerbitEntity> penerbit = penerbitRepository.findAll(page);

		List<PenerbitResponse> penerbitList = penerbit.stream()
				.map(map -> penerbitMapper.toDto(map))
				.collect(Collectors.toList());

		FormatPenerbitResponse formatPenerbitResponse = new FormatPenerbitResponse();
		Date now = new Date();
		String timestamp= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",Locale.UK).format(now);
		formatPenerbitResponse.setTimestamp(timestamp);
		formatPenerbitResponse.setData(penerbitList);

		return formatPenerbitResponse;
	}



}
