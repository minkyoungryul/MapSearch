package com.kakaobank.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kakaobank.project.member.service.MemberService;

@Component
public class StartRunner implements CommandLineRunner{

	@Autowired
	MemberService memberService;
	
	@Override
	public void run(String... args) throws Exception {
		//구동 시점에 회원 정보 저장
		memberService.saveMember();
	}
}
