package com.crud.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crud.book.model.BukuEntity;

@Repository
public interface BukuRepository extends JpaRepository<BukuEntity, Long>{

	@Query("SELECT b FROM BukuEntity b JOIN FETCH b.kategori")
    List<BukuEntity> findAllWithKategori();
}
