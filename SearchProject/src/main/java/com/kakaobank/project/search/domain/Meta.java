package com.kakaobank.project.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Meta {
	
	@JsonProperty("is_end")
	private boolean isEnd;
	
	@JsonProperty("pageable_count")
	private int pageableCount;
	
	@JsonProperty("total_count")
	private int totalCount;
	
	@JsonProperty("same_name")
	private SameName sameName;
	
	public SameName getSameName() {
		return sameName;
	}
	public void setSameName(SameName sameName) {
		this.sameName = sameName;
	}
	public boolean isEnd() {
		return isEnd;
	}
	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
	public int getPageableCount() {
		return pageableCount;
	}
	public void setPageableCount(int pageableCount) {
		this.pageableCount = pageableCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "Meta [isEnd=" + isEnd + ", pageableCount=" + pageableCount + ", totalCount=" + totalCount
				+ ", sameName=" + sameName + "]";
	}
}
