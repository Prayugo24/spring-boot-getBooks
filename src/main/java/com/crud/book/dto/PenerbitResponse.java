package com.crud.book.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PenerbitResponse {

	public long id;
	public String namePenerbit;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNamePenerbit() {
		return namePenerbit;
	}
	public void setNamePenerbit(String namePenerbit) {
		this.namePenerbit = namePenerbit;
	}




}
