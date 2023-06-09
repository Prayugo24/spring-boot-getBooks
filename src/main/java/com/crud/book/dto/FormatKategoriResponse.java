package com.crud.book.dto;

import java.util.List;


public class FormatKategoriResponse {

	private String timestamp;
	private List<KategoriResponse> data;

	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public List<KategoriResponse> getData() {
		return data;
	}
	public void setData(List<KategoriResponse> data) {
		this.data = data;
	}


}
