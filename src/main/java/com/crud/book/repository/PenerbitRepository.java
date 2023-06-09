package com.crud.book.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.book.model.PenerbitEntity;


@Repository
public interface PenerbitRepository extends JpaRepository<PenerbitEntity, Long> {

	@Override
	public Page<PenerbitEntity> findAll(Pageable pageable);
}
