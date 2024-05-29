



<h1> <img src= "https://github.com/LeeInWang/campcok/assets/156161944/b0d6b332-76fe-40c5-a51f-b89d4401e91d" campcok </h1>  



## 📆  개발 기간
2024.04.24~2024.05.26

<br>

## 👥 멤버 구성

<p text align>
팀장: 김민곤 - MainPage, WeatherAPI <br> 
팀원: 박지혜 - MainPage, WeatherAPI <br>
팀원: 이인왕 - SearchPage, MapAPI <br>
팀원: 임요셉 - ReadPage, Weather API <br>
팀원: 최현철 - ReadPage, Weather API <br>
팀원: 한수정 - SearchPage, MapAPI <br>
</p>

<br>

## 🖥 프로젝트 소개
Open Weather API를 활용한 캠핑장 추천 웹사이트 구현

<br>

## 📚 프로젝트 주제 
날씨와 캠핑장 공공데이터를 활용하여
애완견과 함께 이용할 수 있는 캠핑장을 추천

<br>

## ♠ 선정 배경
날씨나 온도에 따라 주의할 점이 달라지기에 날씨나 온도를 고려하여, <br>애완견과 온라인예매를 중심으로 캠핑 장소를 선택하는 데 도움을 드리고자 이 프로젝트를 시작하게 되었습니다. 

<br>

## ❗기대 효과
날씨 상황을 실시간으로 확인하고 캠핑 시 날씨로 인한 안전사고에 대해 대응하고, <br> 여행 일정을 편리하게 계획 할 수 있도록 캠핑장의 여행 정보를 제공합니다. 

<br>

## 🖥  기술 스택
통합 개발 환경 (IDE) - intellij idea ultimate  Visual Studio Code <br>
Frontend 프레임워크 - React 18.3.1 Node.js 18.18.2 <br>
Backend 프레임워크 -  Spring Boot 3.1.11 <br>
DB MySQL 8.0.27 <br>
빌드 자동화 도구 Gradle 8.7 <br>
라이브러리 Axios 1.6.8 <br>
ORM(Object-Relational Mapping) 프레임워크 Hibernate <br>

<br>

## 🖥 개발 언어
Java(JDK17) JavaScript

<br>

## 🖥 React Library 
npm install @reduxjs/toolkit react-redux <br> 
npm i react-query <br>
npm install @tanstack/react-query <br>
npm install @tanstack/react-query-devtools <br>
<br>
문제있을시 설치 <br>
npm install @mui/material @mui/icons-material react-copy-to-clipboard
<br>
npm install @emotion/react @emotion/styled

<br>

## 📈 WBS & ERD
<details>
<summary> WBS </summary>
	<h3>간트차트</h3>
	<img src="https://github.com/LeeInWang/campcok/assets/156161944/e1a97bfc-1c06-4380-a4b9-039ded4d5d19">
	</details>

<details>
<summary> ERD </summary>
	<img src="https://github.com/LeeInWang/campcok/assets/156161944/36fa09d9-6dfe-4a79-afb5-30b252e67ca3">
</details>

<br>

## ⌨ 페이지
<details>
<summary> 메인 페이지 </summary>
	<img src="https://github.com/LeeInWang/campcok/assets/156161944/30f8345d-d598-4966-8e67-2c83eae743d6">
</details>

<details>
<summary> 검색 페이지 </summary>
	<img src="https://github.com/LeeInWang/campcok/assets/156161944/0b3e0bcb-5adf-4fc2-98da-e7ec9c60f05d">
</details>

<details>
<summary> 상세 페이지 </summary>
	<img src="https://github.com/LeeInWang/campcok/assets/156161944/54c07766-bece-4468-b701-0af144c5723a">
	<img src="https://github.com/LeeInWang/campcok/assets/156161944/8964cd50-cb87-4dc7-a34a-ebf04d13fce4">
	<img src="https://github.com/LeeInWang/campcok/assets/156161944/8bd61d7a-292f-40da-b4bf-10ed711ee7e8">
</details>

<details>
<summary> 지도 페이지 </summary>
	<img src="https://github.com/LeeInWang/campcok/assets/156161944/e26f294e-8ccd-4cb7-a16a-3fc15fe2e291">
	<img src="https://github.com/LeeInWang/campcok/assets/156161944/30e6e91d-1802-4088-9c37-021c6094ab6b" >
</details>

<br>

## ⚔ Trouble Shooting

<details>
<summary> 문제 1 : 캠핑장 위치에 있는 마커들을 클릭시 간략한 정보가 바로 뜨지 않는 현상 </summary>  <br>
해결 :  overlayRef.current.style.zIndex = 1000: 오버레이의 z-index를 설정하여 다른 요소들보다 위에 표시되도록 합니다. <br>
</details>
<br>
<br>
 <details>
<summary> 문제 2 : 스프링부트 could not execute statement [Data truncation: Data too long for column 'feature_nm' at row  </summary> <br>
해결 : column설정하지 않는 domain 변수는 기본 default값이 255까지 제공됩니다. <br>
feature_nm'  255자가 넘어 "Data too long for column" 오류가 발생한 것입니다. <br>
먼저 테스트에서 실행하게되면, 기존의 테이블이 남아있습니다. <br>
기존의 테이블을 삭제하고 그다음에 다시한번  column값을 2000으로 지정해주었습니다. <br>
</details>
<br>
<br>

<details>
<summary> 문제 3 : JPQL에서는 In 기능과 Like기능을 같이 사용하지 못한다. </summary>
<br>
해결 : JPQL 대신 Spring JPA Specifications 기능을 사용하여 In기능과 Like 기능을 같이 사용했다. <br>
<br>
public static Specification<Gocamping> lctClsIn(List<String> lctCls) { <br>
    return (root, query, criteriaBuilder) -> { <br>
        if (lctCls == null || lctCls.isEmpty()) { <br>
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // 무조건 참 <br>
        } else { <br>
            return criteriaBuilder.or( <br>
                    lctCls.stream() <br>
                            .map(cls -> criteriaBuilder.like(root.get("lctCl"), "%" + cls + "%")) <br>
                            .toArray(Predicate[]::new) <br>
            ); <br>
        } <br>
    }; <br>
} <br>
</details>
 
 
</details>
  <br></br>


  
## 📗 제안사항

로그인 기능(찜, 리뷰) - 캠핑장에 대한 간단한 리뷰를 넣고 가고 싶은 캠핑장을 저장해 놓을 수 있으면 좋겠습니다. <br>
메인 (지역축제) - 인기 지역축제를 배너로 만들었는데 지역축제 API를 사용했다면 더 많은 정보를 제공할 수 있을 것 같습니다. <br>
CQRS - 서버어플리케이션에서 읽기와 쓰기를 분리하고, Kafka로 구독을 시킨다면,<br> 
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  응집도는 높아지고, 결합도는 느슨해지는 좋은 구조가 있습니다. <br>

## YouTube
![유튜브](https://github.com/LeeInWang/campcok/assets/156080750/bb9cd649-7cf3-4ca3-b0ce-106e946f6e7b)
