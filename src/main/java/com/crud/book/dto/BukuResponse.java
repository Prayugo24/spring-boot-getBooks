package com.crud.book.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class BukuResponse {

	public long id;
	private String judulBuku;
	private String pengarang;
	private Integer jumlahHalaman;
	private String nomorRak;
	private String Isbn;
	private KategoriResponse kategori;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJudulBuku() {
		return judulBuku;
	}
	public void setJudulBuku(String judulBuku) {
		this.judulBuku = judulBuku;
	}
	public String getPengarang() {
		return pengarang;
	}
	public void setPengarang(String pengarang) {
		this.pengarang = pengarang;
	}
	public Integer getJumlahHalaman() {
		return jumlahHalaman;
	}
	public void setJumlahHalaman(Integer jumlahHalaman) {
		this.jumlahHalaman = jumlahHalaman;
	}
	public String getNomorRak() {
		return nomorRak;
	}
	public void setNomorRak(String nomorRak) {
		this.nomorRak = nomorRak;
	}
	public String getIsbn() {
		return Isbn;
	}
	public void setIsbn(String isbn) {
		Isbn = isbn;
	}
	public KategoriResponse getKategori() {
		return kategori;
	}
	public void setKategori(KategoriResponse kategori) {
		this.kategori = kategori;
	}






}
