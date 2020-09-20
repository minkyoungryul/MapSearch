package com.kakaobank.project.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	//아이디, 패스워드로 계정 조회
	Member findByEmailAndPassword(String email, String password);
}
