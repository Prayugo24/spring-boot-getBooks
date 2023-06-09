package com.crud.book.dto;

import java.util.List;

public class FormatPenerbitResponse {

	private String timestamp;
	private List<PenerbitResponse> data;

	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public List<PenerbitResponse> getData() {
		return data;
	}
	public void setData(List<PenerbitResponse> data) {
		this.data = data;
	}


}
