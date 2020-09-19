package com.kakaobank.project.search.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.kakaobank.project.common.ApiUtils;
import com.kakaobank.project.search.domain.LocationInfo;
import com.kakaobank.project.search.domain.Search;
import com.kakaobank.project.search.domain.SearchRepository;

@Service
public class SearchService {
	
	@Autowired
	private SearchRepository searchRepository;
	
	public LocationInfo getSearchList(String url, String key, Map<String, Object> paramMap) throws Exception{
		
		String jsonData;
		LocationInfo locationInfo;
		
		String apiUrl = url+"?";
		
		for(Map.Entry<String, Object> entry : paramMap.entrySet()) {
			apiUrl += entry.getKey()+"="+entry.getValue()+"&";
		}
		
		try {
			jsonData = ApiUtils.requestHttpGet(apiUrl, key);
			locationInfo = ApiUtils.unmarshallingJson(jsonData, LocationInfo.class);
		} catch (Exception e) {
			throw e;
		}
		
		return locationInfo; 
	}
	
	public void saveKeyword(String keyword) throws Exception {
		Search search = searchRepository.findByKeyword(keyword);
		try {

			if(!StringUtils.isEmpty(search)) {
				//검색어가 있을 경우 횟수 +1 UPDATE
				searchRepository.update(search.getCount(), search.getKeyword());
			}else {
				//검색어가 없을 경우 INSERT
				search = new Search();
				search.setKeyword(keyword);
				search.setCount(1);
				
				searchRepository.save(search);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Search> getPopKeywordList(){
		List<Search> popKeywordList = searchRepository.findAll(Sort.by("count").descending());
		
		return popKeywordList;
	}
}
