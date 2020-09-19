<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>장소 검색 서비스</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="author" content="colorlib.com">
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Poppins:400,800"/>
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/css/main.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  </head>
  <body>
  	<script>
	$(function(){

		//검색 버튼 클릭 시 카카오 키워드 검색 API 호출
		$(".btn-search").click(function(){
			var searchWord = encodeURIComponent($("#searchWord").val());
			var page = 1;
			var size = 10;
			location.href="/map?query="+searchWord+"&page="+page+"&size="+size;
		});

		//엔터키 누를시 카카오 키워드 검색 API 호출
		$("form input").keydown(function(key){
			if(key.keyCode == 13){
				var searchWord = encodeURIComponent($("#searchWord").val());
				var page = 1;
				var size = 10;
				location.href="/map?query="+searchWord+"&page="+page+"&size="+size;
			}
		});

		
	});

	function keywordApi(searchWord){
		$.ajax({
			contentType:"application/json",
			dataType:"json",
			type:"GET",
			url: "/searchAjax",
			data: {"keyword" : searchWord},
			async: false,
			success: function(data){
				console.log(data);
			},
			fail: function(e){
				alert('예기치 않은 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
			},
			error: function(e){
				alert('예기치 않은 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
			}
		});
		
	}
  	</script>
    <div class="s006">
      <form onsubmit="return false">
        <fieldset>
          <legend>What are you looking for?</legend>
          <div class="inner-form">
            <div class="input-field">
              <button class="btn-search" type="button">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                  <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                </svg>
              </button>
              <input id="searchWord" type="text" placeholder="" value="Ladies" />
            </div>
          </div>
          <div class="suggestion-wrap">
          <c:forEach var="keyword" begin="0" end="9" items="${keywordList}">
            <span>${keyword.keyword} ${keyword.count}</span>
          </c:forEach> 
          </div>
        </fieldset>
      </form>
    </div>
  </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
