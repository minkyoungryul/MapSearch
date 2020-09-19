package com.kakaobank.project.search.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long>{
	
	//검색어로 조회
	Search findByKeyword(String keyword);
	
	//검색 횟수 UPDATE
	@Transactional
	@Modifying
	@Query(value="UPDATE Search s SET s.count = :count+1 WHERE s.keyword = :keyword", nativeQuery = false)
	int update(@Param("count") int count, @Param("keyword") String keyword);
}
