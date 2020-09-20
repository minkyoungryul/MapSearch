# test-kakaobank
카카오뱅크 / 오픈 API를 활용한 장소 검색 서비스 구현

## 개발환경
- IDE : Spring Tool Suite 4
- Java8
- MAVEN 3.6.3
- Spring Boot 2.3.3
- JPA
- H2


## 빌드 및 실행 방법
Git, Java 는 설치되어 있다고 가정한다.

- 빌드 방법
  - 소스 다운로드
  - IDE에서 File-> Import -> Existing Maven Projects -> 내려 받은 소스의 SearchProject 폴더 선택 후 Finish
  - SearchProject의 SearchProjectApplication.java 파일 Spring Boot App 실행

접속 Base URI: http://localhost:8080

## 서비스 기능

1. 로그인
- 아이디 비밀번호 로그인, 로그아웃 기능 구현.
- 사용자 데이터는 애플리케이션 실행 시점에 생성.
- 비밀번호는 SHA256 암호화

URL : http://localhost:8080/map?query=%EC%B9%B4%EC%B9%B4%EC%98%A4&page=1&size=10

2. 장소 검색
- 키워드를 통해 장소를 검색.
- 검색 결과는 Pagination 형태로 제공.
- 검색 소스는 카카오 API의 키워드로 장소 검색을 활용.
      (https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-keyword)
- 추후 카카오 API 이외에 새로운 검색 소스가 추가될 수 있음을 고려하여 검색시 메서드 구현.

URL : http://localhost:8080/map?query=%EC%B9%B4%EC%B9%B4%EC%98%A4&page=1&size=10
URL : http://localhost:8080

3. 검색된 장소의 상세 조회

    - 각 검색 결과의 상세 정보(지번, 도로명주소, 전화번호 등)를 확인 가능.
    - 상세 정보에는 지도, 길찾기, 상세정보, 로드뷰 바로 가기 제공.
      ※ Daum 지도 Web API의 바로 가기(http://apis.map.daum.net/web/guide/#bigmapurl)를 참고.

URL : http://localhost:8080/map?query=%EC%B9%B4%EC%B9%B4%EC%98%A4&page=1&size=10

4. 인기 검색어 목록

    - 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공.
    - 검색어 별로 검색된 횟수도 함께 표기.
    - 키워드 클릭시 검색 이동.
    
URL : http://localhost:8080 (검색 후 해당 화면에서 확인 가능)
