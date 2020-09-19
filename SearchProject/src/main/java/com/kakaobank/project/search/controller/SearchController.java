package com.kakaobank.project.search.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakaobank.project.common.ApiUtils;
import com.kakaobank.project.member.service.MemberService;
import com.kakaobank.project.search.domain.Documents;
import com.kakaobank.project.search.domain.LocationInfo;
import com.kakaobank.project.search.domain.Search;
import com.kakaobank.project.search.param.SearchParam;
import com.kakaobank.project.search.service.SearchService;

@Controller
public class SearchController {
	
	@Value("${kakao.key}")
	private String key;
	
	@Value("${kakao.keyword.url}")
	private String keywordApiUrl;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/")
	public String searchMain(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		
		//회원 정보 저장
		memberService.saveMember();
		
		//인기검색어 조회
		List<Search> popKeywordList = searchService.getPopKeywordList();
		
		model.addAttribute("keywordList",popKeywordList);
		return "searchMain";
	}
	
	@RequestMapping(value="/map")
	public String searchMap(HttpServletRequest request, HttpServletResponse response, ModelMap model, SearchParam param) {
		
		try {
			//검색한 내용 DB 저장
			searchService.saveKeyword(param.getQuery());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		LocationInfo locationInfo = searchCommonResult(param);
		
		model.addAttribute("locationInfo",locationInfo);
		
		return "map";
	}
	
	@RequestMapping(value="/searchAjax", method = RequestMethod.GET)
	@ResponseBody
	public LocationInfo searchAjax(HttpServletRequest request, HttpServletResponse response, SearchParam param){
		
		//검색 리스트 가져오기 (카카오 키워드 검색 API 호출)
		LocationInfo locationInfo = searchCommonResult(param);
		
		return locationInfo;
	}
	
	public LocationInfo searchCommonResult(SearchParam param) {
		LocationInfo locationInfo = new LocationInfo();
		String resultCode = "";
		
		try {
			Map<String, Object> mapParam = new HashMap<>();
			mapParam.put("query",URLEncoder.encode(param.getQuery()));
			mapParam.put("size", param.getSize());
			mapParam.put("page", param.getPage());
			
			locationInfo = searchService.getSearchList(keywordApiUrl, key, mapParam);
			locationInfo.setPage(param.getPage());
			System.out.println(locationInfo.toString());
			
			if(!StringUtils.isEmpty(locationInfo) && locationInfo.getDocuments().size() > 0) {
				resultCode = "0000";
			}else {
				//검색 데이터가 없을때
				resultCode = "1111";
			}
		} catch (Exception e) {
			resultCode = "9999";
		}
		
		locationInfo.setKeyword(param.getQuery());
		locationInfo.setResultCode(resultCode);
		
		return locationInfo;
	}
}
