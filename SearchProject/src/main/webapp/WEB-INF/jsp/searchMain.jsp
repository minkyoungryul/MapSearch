<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>장소 검색 서비스</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css"  href="/resources/css/main.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  </head>
  <body>
  	<script>
	$(function(){

		//검색 버튼 클릭 시 카카오 키워드 검색 API 호출
		$(".btn-search").click(function(){
			search();
		});

		//엔터키 누를시 카카오 키워드 검색 API 호출
		$("form input").keydown(function(key){
			if(key.keyCode == 13){
				search();
			}
		});
	});

	function search(){
		var searchWord = encodeURIComponent($("#searchWord").val());
		var page = 1;
		var size = 10;
		location.href="/map?query="+searchWord+"&page="+page+"&size="+size;
	}
  	</script>
    <div class="s006">
      <form onsubmit="return false">
        <fieldset>
          <legend>Where are you looking for?</legend>
          <div class="inner-form">
            <div class="input-field">
              <button class="btn-search" type="button">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                  <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                </svg>
              </button>
              <input id="searchWord" type="text" placeholder="" value="카카오" />
            </div>
          </div>
          <div class="suggestion-wrap">
          <c:if test="${not empty keywordList}">
          	<span>인기검색어</span>
          </c:if>
          <c:forEach var="keyword" begin="0" end="9" items="${keywordList}">
            <span>${keyword.keyword} ${keyword.count}</span>
          </c:forEach> 
          </div>
        </fieldset>
      </form>
    </div>
  </body>
</html>
