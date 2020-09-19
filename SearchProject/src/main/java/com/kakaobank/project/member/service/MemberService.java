package com.kakaobank.project.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakaobank.project.member.domain.Member;
import com.kakaobank.project.member.domain.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public void saveMember() {
		
		Member member = new Member();
		member.setEmail("minkr3321@naver.com");
		member.setName("민경률");
		member.setPassword("1234");
		
		memberRepository.save(member);
	}
}
