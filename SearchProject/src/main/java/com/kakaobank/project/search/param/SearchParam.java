package com.kakaobank.project.search.param;

public class SearchParam {
	
	private String query;
	
	private int size;
	
	private int page;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "SearchParam [query=" + query + ", size=" + size + ", page=" + page + "]";
	}
	
	
}
