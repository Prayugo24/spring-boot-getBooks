package com.crud.book.dto;

import java.util.List;

public class FormatBukuResponse {
	private String timestamp;
	private List<BukuResponse> data;

	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public List<BukuResponse> getData() {
		return data;
	}
	public void setData(List<BukuResponse> data) {
		this.data = data;
	}



}
