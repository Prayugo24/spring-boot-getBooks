package com.crud.book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "buku")
public class BukuEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public long id;

	@Column(name="judul_buku")
	public String judulBuku;

	@Column(name="pengarang")
	public String pengarang;

	@Column(name="penerbit")
	public String penerbit;

	@Column(name="jumlah_halaman")
	public Integer jumlahHalaman;

	@Column(name="nomor_rak")
	public String nomorRak;

	@Column(name="isbn")
	public String Isbn;

//	@Column(name="id_penerbit")
//	public Integer idPenerbit;


	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kategori", referencedColumnName = "id")
    private KategoriEntity kategori;

//	@ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_penerbit", referencedColumnName = "id")
//	private PenerbitEntity penerbitList;

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

	public String getPenerbit() {
		return penerbit;
	}

	public void setPenerbit(String penerbit) {
		this.penerbit = penerbit;
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

	public KategoriEntity getKategori() {
		return kategori;
	}

	public void setKategori(KategoriEntity kategori) {
		this.kategori = kategori;
	}

//	public PenerbitEntity getPenerbitList() {
//		return penerbitList;
//	}
//
//	public void setPenerbitList(PenerbitEntity penerbitList) {
//		this.penerbitList = penerbitList;
//	}






}
