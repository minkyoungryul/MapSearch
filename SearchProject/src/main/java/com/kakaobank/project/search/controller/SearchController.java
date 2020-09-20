package com.kakaobank.project.search.controller;

import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	//첫 페이지
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String searchMain(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
		
		//인기검색어 조회
		List<Search> popKeywordList = searchService.getPopKeywordList();
		
		model.addAttribute("keywordList",popKeywordList);
		return "searchMain";
	}
	
	//검색화면
	@RequestMapping(value="/map")
	public String searchMap(HttpServletRequest request, HttpServletResponse response, ModelMap model, SearchParam param) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		try {
			//검색한 내용 DB 저장
			searchService.saveKeyword(param.getQuery());
		} catch (Exception e) {
		}
		
		//검색 결과 조회 (카카오 API호출)
		LocationInfo locationInfo = searchCommonResult(param);
		
		model.addAttribute("locationInfo",locationInfo);
		
		return "map";
	}
	
	//검색 결과 조회 (카카오 API호출)
	public LocationInfo searchCommonResult(SearchParam param) {
		LocationInfo locationInfo;
		String resultCode = "";
		
		try {
			Map<String, Object> mapParam = new HashMap<>();
			mapParam.put("query",URLEncoder.encode(param.getQuery()));
			mapParam.put("size", param.getSize());
			mapParam.put("page", param.getPage());
			
			//카카오 API 연동
			locationInfo = searchService.getSearchList(keywordApiUrl, key, mapParam);
			locationInfo.setPage(param.getPage());
			
			//정상 호출시
			if(!StringUtils.isEmpty(locationInfo) && locationInfo.getDocuments().size() > 0) {
				resultCode = "0000";
			}else {
				//검색 데이터가 없을때
				locationInfo = new LocationInfo();
				resultCode = "1111";
			}
		} catch (Exception e) {
			locationInfo = new LocationInfo();
			resultCode = "9999";
		}
		
		locationInfo.setKeyword(param.getQuery());
		locationInfo.setResultCode(resultCode);
		
		return locationInfo;
	}
}
