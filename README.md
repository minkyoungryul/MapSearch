# 장소 검색 
오픈 API를 활용한 장소 검색 서비스 구현

## 개발환경
- IDE : Spring Tool Suite 4
- Java8
- MAVEN 3.6.3
- Spring Boot 2.3.3
- JPA
- H2

## 라이브러리
- Boot Strap CSS 활용

## 빌드 및 실행 방법
Git, Java 는 설치되어 있다고 가정한다.

- 빌드 방법
  - 소스 다운로드
  - IDE에서 File-> Import -> Existing Maven Projects -> 내려 받은 소스의 SearchProject 폴더 선택 후 Finish
  - SearchProject의 SearchProjectApplication.java 파일 Spring Boot App 실행
  - 기본 접속 Base URI: <http://localhost:8080>

## 기능
- 로그인
  - 로그인은 세션을 사용하여 구현
  - 사용자 데이터는 구동 시점에 인메모리 DB를 사용하여 저장. 비밀번호는 SHA256방식으로 저장.
  - 비밀번호는 SHA256 방식을 사용하여 로그인 시 클라이언트에서 전송하여 서버에서 인메모리 DB의 패스워드와 비교
  - 로그아웃 기능을 구현
  - URL : <http://localhost:8080/map?query=%EC%B9%B4%EC%B9%B4%EC%98%A4&page=1&size=10>
  
- 장소 검색
  - 키워드 API Parameter의 page와 size를 이용하여 Pagination 처리.
    - API 호출시 노출되는 장소가 한정되어 있기 때문에 개수는 전체를 노출하되 데이터는 존재하는 데이터만 노출.
  - 하나의 함수로 구현하여 재활용 가능하게 구현  
  - URL : <http://localhost:8080/map?query=%EC%B9%B4%EC%B9%B4%EC%98%A4&page=1&size=10>
  - URL : <http://localhost:8080>
  
- 검색된 장소의 상세 조회
  - 간단한 지도와 상세 데이터 제공
  - 검색된 장소의 지도 뿐만 아니라 길찾기, 상세정보, 로드뷰 바로 가기 링크 제공
  - URL : <http://localhost:8080/map?query=%EC%B9%B4%EC%B9%B4%EC%98%A4&page=1&size=10>
  
- 인기 검색어 목록
  - 검색 데이터를 인메모리 DB로 관리하여 최대 10개의 검색 내용과 검색 횟수 제공
  - 인기 검색어 클릭시 해당 키워드로 검색
  - URL : <http://localhost:8080> (검색 후 해당 화면에서 확인 가능)
