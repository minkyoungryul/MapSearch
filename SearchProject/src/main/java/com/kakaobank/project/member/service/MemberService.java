package com.kakaobank.project.member.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.kakaobank.project.common.SessionUtils;
import com.kakaobank.project.common.Sha256Utils;
import com.kakaobank.project.member.domain.Member;
import com.kakaobank.project.member.domain.MemberRepository;
import com.kakaobank.project.member.param.MemberParam;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public void saveMember() throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		
		Member member = new Member();
		member.setEmail("minkr3321@naver.com");
		member.setName("민경률");

		//패스워드 암호화
		String password = Sha256Utils.encrypt("1234");
		member.setPassword(password);
		
		//DB insert
		memberRepository.save(member);
	}

	public boolean loginCheck(HttpServletRequest request, MemberParam param) throws Exception{
		Member member = null;
		
		//ID,PW로 회원 조회 
		member = memberRepository.findByEmailAndPassword(param.getEmail(),param.getPassword());
		
		if(!StringUtils.isEmpty(member)) {
			
			//비밀번호가 틀렸을경우
			if(!param.getPassword().equals(member.getPassword())) {
				return false;
			}else {
				
				//로그인 후 세션 추가ㅣ
				SessionUtils.setAttribute("sessionName", member.getName());
				
				return true;
			}
		}else {
			return false;
		}
	}
}
