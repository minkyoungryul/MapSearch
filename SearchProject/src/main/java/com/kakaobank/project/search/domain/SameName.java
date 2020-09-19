package com.kakaobank.project.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SameName {
	
	@JsonProperty("keyword")
	private String keyword;
	
	@JsonProperty("selected_region")
	private String selectedRegion;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSelectedRegion() {
		return selectedRegion;
	}

	public void setSelectedRegion(String selectedRegion) {
		this.selectedRegion = selectedRegion;
	}

	@Override
	public String toString() {
		return "SameName [keyword=" + keyword + ", selectedRegion=" + selectedRegion + "]";
	}
}
