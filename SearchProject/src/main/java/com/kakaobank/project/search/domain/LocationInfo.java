package com.kakaobank.project.search.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kakaobank.project.common.domain.BaseModel;

import lombok.Data;

@Data
public class LocationInfo extends BaseModel{
	
	@JsonProperty("documents")
	private List<Documents> documents;
	
	@JsonProperty("meta")
	private Meta meta;
	
	private String keyword;
	
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Documents> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return "LocationInfo [documents=" + documents + ", meta=" + meta + "]";
	}
	
	
}
