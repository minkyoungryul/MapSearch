package com.kakaobank.project.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakaobank.project.common.SessionUtils;
import com.kakaobank.project.member.param.MemberParam;
import com.kakaobank.project.member.service.MemberService;


@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	
	//로그인 validation 체크 후 처리
	@RequestMapping(value="/loginAjax", method = RequestMethod.POST)
	@ResponseBody
	public boolean loginAjax(HttpServletRequest request, HttpServletResponse response, @RequestBody MemberParam param){
		
		try {
			if(memberService.loginCheck(request, param)) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	//로그아웃 처리
	@RequestMapping(value="/logoutAjax", method = RequestMethod.GET)
	@ResponseBody
	public boolean logoutAjax() {
		try {
			
			//로그인 세션 제거
			SessionUtils.removeAttribute("sessionName");
			
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
