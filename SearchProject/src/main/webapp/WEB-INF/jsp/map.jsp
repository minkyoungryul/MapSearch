<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/css/map.css"/>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=264f61fee55395dff416e6583a709a07"></script>

<!DOCTYPE html>
<html>
	<head>
		<title>장소 검색 서비스</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<!-- <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.js"></script>
		 -->
	</head>
	<body>
	
	<script>
		$(function(){

			var page = 1;
	
			//검색 버튼 클릭 시 카카오 키워드 검색 API 호출
			$(".input-group-prepend").click(function(){
				var searchWord = encodeURIComponent($("#searchWord").val());
				var page = 1;
				var size = 10;
				location.href="/map?query="+searchWord+"&page="+page+"&size="+size;
			});
	
			//엔터키 누를시 카카오 키워드 검색 API 호출
			$("#searchWord").keydown(function(key){
				if(key.keyCode == 13){
					var searchWord = encodeURIComponent($("#searchWord").val());
					var page = 1;
					var size = 10;
					location.href="/map?query="+searchWord+"&page="+page+"&size="+size;
				}
			});
		
			var placeNm = '${locationInfo.documents[0].placeNm}';
			var category = '${locationInfo.documents[0].ctegryNm}';
			var x = '${locationInfo.documents[0].x}';
			var y = '${locationInfo.documents[0].y}';
			var address = '${locationInfo.documents[0].addressNm}';
			var roadAddress = '${locationInfo.documents[0].roadAddressNm}';
			var phone = '${locationInfo.documents[0].phone}';
			var placeUrl = '${locationInfo.documents[0].placeUrl}';
			var placeId = '${locationInfo.documents[0].id}';
			
			changeLocation(x, y, placeNm, category, address, roadAddress, phone, placeUrl, placeId);

			$(".home").click(function(){
				location.href="/";
			});
		});
	
		
	
		function mapInit(x,y){
			if(x == "" || y == ""){
	
				//값이 없을 시 기본 카카오 위도경도 설정
				x=126.570667;
				y=33.450701;
			}
			
			var mapContainer = document.getElementById('kakao_map');
			var options = {
				center: new kakao.maps.LatLng(y, x),
				level: 3
			};
	
			var map = new kakao.maps.Map(mapContainer, options);
	
			 // 이동할 위도 경도 위치를 생성합니다 
		    var moveLatLon = new kakao.maps.LatLng(y, x);
		    
		    // 지도 중심을 부드럽게 이동시킵니다
		    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
		    map.panTo(moveLatLon); 
	
		 	// 마커가 표시될 위치입니다 
		    var markerPosition  = new kakao.maps.LatLng(y, x); 
	
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        position: markerPosition
		    });
	
		    // 마커가 지도 위에 표시되도록 설정합니다
		    marker.setMap(map); 
		}

		function movePage(page){
			location.href="/map?query="+'${locationInfo.keyword}'+"&page="+page+"&size="+10;
		}      
	
		/* function keywordApi(searchWord,page){
			var searchParam = {
				"query" : searchWord,
				"size" : 10,
				"page" : page
			};
			
			$.ajax({
				contentType:"application/json",
				dataType:"json",
				type:"GET",
				url: "/searchAjax",
				data: searchParam,
				async: false,
				success: function(data){

					
					
					var placeNm = data.documents[0].place_name;
					var category = data.documents[0].category_name;
					var x = data.documents[0].x;
					var y = data.documents[0].y;
					var address = data.documents[0].address_name;
					var roadAddress = data.documents[0].road_address_name;
					var phone = data.documents[0].phone;
					var placeUrl = data.documents[0].place_url;
					var placeId = data.documents[0].id;
					changeLocation(x,y,placeNm,category,address,roadAddress,phone,placeUrl,placeId);
				},
				fail: function(e){
					alert('예기치 않은 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
				},
				error: function(e){
					alert('예기치 않은 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
				}
			});
			
		} */

		function changeLocation(x,y,placeNm,category,address,roadAddress,phone,placeUrl,placeId){

			
			$(".placeNm_detail").text(placeNm);
			$(".category_detail").text(category);
			$(".address_detail").text(address);
			$(".road_address_detail").text(address);
			$(".location_detail_footer").text(phone);
			$(".info_detail a").attr("href",placeUrl);
			$(".move_map_detail a").attr("href","https://map.kakao.com/link/map/"+placeId);
			$(".move_find_road_map_detail a").attr("href","https://map.kakao.com/link/to/"+placeId);
			$(".move_road_map_detail a").attr("href","https://map.kakao.com/link/roadview/"+placeId);
			
			mapInit(x, y);
		} 
	  </script>
		<div class="container-fluid h-100">
			<div class="row h-100">
				<div class="chat"><div class="card mb-sm-3 mb-md-0 contacts_card">
					<div class="card-header">
						<div class="input-group">
							<input type="text" placeholder="장소 검색" name="" id="searchWord" class="form-control search">
							<div class="input-group-prepend">
								<span class="input-group-text search_btn"><i class="fas fa-search"></i></span>
							</div>
						</div>
					</div>
					<div class="card-body contacts_body">
						<div class="search_result">
							<div><strong>'${locationInfo.keyword}' 검색결과</strong></div>
							<div><string>총 ${locationInfo.meta.totalCount} 건</div>
						</div>
						<ui class="contacts">
						<c:forEach var="document" items="${locationInfo.documents}" varStatus="docCount">
							<li>
								<div class="location_info" onclick="changeLocation(${document.x},${document.y},'${document.placeNm}', '${document.ctegryNm}', '${document.addressNm}', '${document.roadAddressNm}', '${document.phone}', '${document.placeUrl}','${document.id}')">
									<div class="location">
										<div class="placeNm"><strong>${document.placeNm}</strong></div>
										<div class="address" style="color:black">${document.roadAddressNm}</div>
										<div class="address">(지번) ${document.addressNm}</div>
										<div class="location_footer">
											<span class="phone">${document.phone}</span>
											<span class="detail"><a href="${document.placeUrl}">상세보기</a></span>
										</div>
										
									</div>
								</div>
							</li>
						</c:forEach>
						</ui>
						
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
							<c:if test="${locationInfo.meta.pageableCount / 10 > 5}">
							    <li class="page-item disabled">
							      <a class="page-link" href="#" tabindex="-1">Previous</a>
							    </li>
							</c:if>    
						    <c:forEach var="page" begin="1" end="${locationInfo.meta.pageableCount % 10 > 0 ? locationInfo.meta.pageableCount / 10 + 1 : locationInfo.meta.pageableCount / 10}" varStatus="idx">
						    	<li class="${locationInfo.page == idx.index ? 'page-item active' : 'page-item'}"><a class="page-link" onclick="movePage(${idx.index})">${idx.index}</a></li>
						    </c:forEach>
						    <c:if test="${locationInfo.meta.pageableCount / 10 > 5}">
							    <li class="page-item">
							      <a class="page-link" href="#">Next</a>
							    </li>
						    </c:if>
						  </ul>
						</nav>
					</div>
					<div class="card-footer"></div>
				</div></div>
				<div class="map">
					<div class="map_header">
						<div class="home">
							<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-house-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							  <path fill-rule="evenodd" d="M8 3.293l6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6zm5-.793V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
							  <path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
							</svg>
						</div>
						<div class="map_div">
							<div class="input_login">
								<p class="txt">ID <input type="text" name="" class="id"></p>
							</div>
							<div class="input_login">
								<p class="txt">PW <input type="text" name="" class="pw"></p>
							</div>
							<div class="input_login">
								<button type="button" class="btn btn-primary" id="btn_login">Login</button>
							</div>
						</div>
					</div>
					<div class="map_body" id="map_body">
						<div class="kakao_map" id="kakao_map"></div>
						<div class="location_detail">
							<div class="location_detail_div">
								<div class="placeNm_detail"><strong>장교빌딩</strong></div>
								<div class="category_detail">서비스,산업 > 인터넷,IT > 포털서비스</div>
								<div class="address_detail">
									<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-map" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									  <path fill-rule="evenodd" d="M15.817.113A.5.5 0 0 1 16 .5v14a.5.5 0 0 1-.402.49l-5 1a.502.502 0 0 1-.196 0L5.5 15.01l-4.902.98A.5.5 0 0 1 0 15.5v-14a.5.5 0 0 1 .402-.49l5-1a.5.5 0 0 1 .196 0L10.5.99l4.902-.98a.5.5 0 0 1 .415.103zM10 1.91l-4-.8v12.98l4 .8V1.91zm1 12.98l4-.8V1.11l-4 .8v12.98zm-6-.8V1.11l-4 .8v12.98l4-.8z"/>
									</svg>
									서울 중구 삼일대로 363
								</div>
								<div class="road_address_detail">(지번) 서울 중구 장교동 1</div>
								<div class="location_detail_footer">
									<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-telephone" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									  <path fill-rule="evenodd" d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/>
									</svg>
									<span class="phone">02-776-1924</span>
								</div>
								<div class="move_detail">
									<div class="info_detail">
										<div>
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-binoculars" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
											  <path fill-rule="evenodd" d="M3 2.5A1.5 1.5 0 0 1 4.5 1h1A1.5 1.5 0 0 1 7 2.5V5h2V2.5A1.5 1.5 0 0 1 10.5 1h1A1.5 1.5 0 0 1 13 2.5v2.382a.5.5 0 0 0 .276.447l.895.447A1.5 1.5 0 0 1 15 7.118V14.5a1.5 1.5 0 0 1-1.5 1.5h-3A1.5 1.5 0 0 1 9 14.5v-3a.5.5 0 0 1 .146-.354l.854-.853V9.5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v.793l.854.853A.5.5 0 0 1 7 11.5v3A1.5 1.5 0 0 1 5.5 16h-3A1.5 1.5 0 0 1 1 14.5V7.118a1.5 1.5 0 0 1 .83-1.342l.894-.447A.5.5 0 0 0 3 4.882V2.5zM4.5 2a.5.5 0 0 0-.5.5V3h2v-.5a.5.5 0 0 0-.5-.5h-1zM6 4H4v.882a1.5 1.5 0 0 1-.83 1.342l-.894.447A.5.5 0 0 0 2 7.118V13h4v-1.293l-.854-.853A.5.5 0 0 1 5 10.5v-1A1.5 1.5 0 0 1 6.5 8h3A1.5 1.5 0 0 1 11 9.5v1a.5.5 0 0 1-.146.354l-.854.853V13h4V7.118a.5.5 0 0 0-.276-.447l-.895-.447A1.5 1.5 0 0 1 12 4.882V4h-2v1.5a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5V4zm4-1h2v-.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5V3zm4 11h-4v.5a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5V14zm-8 0H2v.5a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5V14z"/>
											</svg>
										</div>
										<div>
											<a href="" style="color:black">상세 보기</a>
										</div>
									</div>
									<div class="move_map_detail">
										<div>
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-geo-alt" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
											  <path fill-rule="evenodd" d="M12.166 8.94C12.696 7.867 13 6.862 13 6A5 5 0 0 0 3 6c0 .862.305 1.867.834 2.94.524 1.062 1.234 2.12 1.96 3.07A31.481 31.481 0 0 0 8 14.58l.208-.22a31.493 31.493 0 0 0 1.998-2.35c.726-.95 1.436-2.008 1.96-3.07zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z"/>
											  <path fill-rule="evenodd" d="M8 8a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
											</svg>
										</div>
										<div><a href="" style="color:black">지도 상세</a></div>
									</div>
									<div class="move_find_road_map_detail">
										<div>
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-90deg-left" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
											  <path fill-rule="evenodd" d="M1.146 4.854a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 4H12.5A2.5 2.5 0 0 1 15 6.5v8a.5.5 0 0 1-1 0v-8A1.5 1.5 0 0 0 12.5 5H2.707l3.147 3.146a.5.5 0 1 1-.708.708l-4-4z"/>
											</svg>
										</div>
										<div><a href="" style="color:black">길찾기</a></div>
									</div>
									<div class="move_road_map_detail">
										<div>
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-geo" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
											  <path fill-rule="evenodd" d="M8 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zM4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999zm2.493 8.574a.5.5 0 0 1-.411.575c-.712.118-1.28.295-1.655.493a1.319 1.319 0 0 0-.37.265.301.301 0 0 0-.057.09V14l.002.008a.147.147 0 0 0 .016.033.617.617 0 0 0 .145.15c.165.13.435.27.813.395.751.25 1.82.414 3.024.414s2.273-.163 3.024-.414c.378-.126.648-.265.813-.395a.619.619 0 0 0 .146-.15.148.148 0 0 0 .015-.033L12 14v-.004a.301.301 0 0 0-.057-.09 1.318 1.318 0 0 0-.37-.264c-.376-.198-.943-.375-1.655-.493a.5.5 0 1 1 .164-.986c.77.127 1.452.328 1.957.594C12.5 13 13 13.4 13 14c0 .426-.26.752-.544.977-.29.228-.68.413-1.116.558-.878.293-2.059.465-3.34.465-1.281 0-2.462-.172-3.34-.465-.436-.145-.826-.33-1.116-.558C3.26 14.752 3 14.426 3 14c0-.599.5-1 .961-1.243.505-.266 1.187-.467 1.957-.594a.5.5 0 0 1 .575.411z"/>
											</svg>
										</div>
										<div><a href="" style="color:black">로드뷰</a></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
