<div align="center">
<p align='center'>
    <a href="http://43.200.255.34/swagger-ui/index.html">
     <img src="https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=Swagger&logoColor=white"/>
  </a> | 

<a href=" ">
      <img src="https://img.shields.io/badge/Notion-000000?style=flat&logo=Notion&logoColor=white"/>
  </a>
</p>
</div>

## AI 검증 비즈니스 프로젝트

### 1. 목표
- '배달의 민족'과 같은 주문 관리 플랫폼 설계
- 대부분 실생활에서 존재하던 과정을 온라인으로 구현하는 것을 목표
- 실제 업무에서 개발하는 것들은 종종 실생활의 행동을 대체하거나, 반복적이고 인력이 많이 소요되는 작업을 서비스로 자동화하는 것이 주된 목적

<br>

### 2. 환경
- Intellj
- Spring Boot 3.3.3
- Monolithic Architecture

<br>

### 3. 목적
- **백엔드 프로젝트** : 기획자, 웹디자이너,프론트 엔지니어의 기능/비기능 요구사항을 구체화 할 수 있다
- **팀** **프로젝트** : 백엔드개발 팀의 일원으로 팀원과 협업을 통해 통합된 어플리케이션을 개발할 수 있다
- **AI서비스** : 생성형 인공지능 서비스(API)와 연동하여 어플리케이션에 AI기능을 개발 할 수 있다
  
  <img src="https://github.com/user-attachments/assets/ecf7c22d-6aff-4cc8-8c57-7670e0d556d6" width="500" height="300">

<br>

## 사조참치 팀원

| 역할 | 이름 |
| --- | --- |
| 회원, 회원주소, EC2 | 김남혁 |
| 메뉴, 주문, 결제 | 안지연 |
| 가게, 카테고리, AI | 배지원 |

<br>

## 문서 자료
[🔗 API 명세서](https://lemon-postbox-fd8.notion.site/4ff14c9547b34710b6d2b09d5ed7ae39?v=6007ecd90b7347f0aa39ed3782de87e2&pvs=4)  <br>
[🔗 API 상세 명세서](https://lemon-postbox-fd8.notion.site/API-666e0e1853924e279b99b8996fbdb4a6?pvs=4)  <br>


<br>

## 개발환경

- JAVA 17
- Build : <img src="https://img.shields.io/badge/Gradle 6.8-02303A?style=flat-square&logo=Gradle&logoColor=white"/>
- AI : <img src="https://img.shields.io/badge/googlegemini -8E75B2?style=flat-square&logo=googlegemini&logoColor=white"/>
- Framework : <img src="https://img.shields.io/badge/Spring Boot 3.3.3 (JPA, Query DSL)-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/>, <img src="https://img.shields.io/badge/Spring Security(OAuth)-6DB33F?style=flat-square&logo=Spring Security&logoColor=white"/>
- DB : <img src="https://img.shields.io/badge/Postgres -4169E1?style=flat-square&logo=postgresql&logoColor=white"/>, <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=AWS RDS&logoColor=white"/>
- Server : <img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=MySql&logoColor=white"/>, <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/>
- CI&CD : <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white"/>
- IDE : <img src="https://img.shields.io/badge/IntelliJ-000000?style=flat-square&logo=IntelliJ IDEA&logoColor=white"/>
- API Test : <img src="https://img.shields.io/badge/swagger-85EA2D?style=flat-square&logo=swagger&logoColor=white"/>

### 라이브러리
```java
dependencies {
    // swagger
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'

    // queryDsl
    implementation "com.querydsl:querydsl-jpa:${querydslVersion}:jakarta"
    annotationProcessor "com.querydsl:querydsl-apt:${querydslVersion}:jakarta"
    annotationProcessor "jakarta.annotation:jakarta.annotation-api"
    annotationProcessor "jakarta.persistence:jakarta.persistence-api"

    // Validation
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    // JWT
    implementation 'io.jsonwebtoken:jjwt-api:0.12.3'
    implementation 'io.jsonwebtoken:jjwt-impl:0.12.3'
    implementation 'io.jsonwebtoken:jjwt-jackson:0.12.3'
    // .env파일을 읽기 위한 라이브러리
    implementation group: 'io.github.cdimascio', name: 'dotenv-java', version: '3.0.0'

    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-data-redis'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'org.postgresql:postgresql'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

<br>

## 인프라 아키텍처

<img src="https://github.com/user-attachments/assets/015adaef-2647-4b48-acbf-b16447bb5062" width="800" height="600" />

<br>

## ERD
<img src="https://github.com/user-attachments/assets/1f563fe3-d3f8-4717-8ca1-98536ccd8bfa" width="800" height="600" />

<br>

## 기능리스트

[🔗 API URL Swagger](http://43.200.255.34/swagger-ui/index.html)

<p>
  
<strong> 1. Auth </strong>
  <details>
  <summary><b>회원가입</b></summary>
  <div markdown="1">
    <ul>

### **회원가입 API (/**auth/signup**)**

| **메서드** | **요청 URL** |
| --- | --- |
| POST | http://{SERVER_URL}/api/v1/auth/signup |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 모두 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| userId | String | 필수 | 회원ID |
| password | String | 필수 | 비밀번호 |
| name | String | 필수 | 이름 |
| phone | String | 필수 | 휴대폰번호 |
| email | String | 필수 | 이메일 |
| role | String | 필수 | 권한 |
| currentAddress | String | 선택 | 주소 |

### **요청 예시**

```jsx
POST /api/v1/auth/signup
Content-Type: application/json

{
  "userId" : "tomas"
	"password" : "1234"
	"name" : "tom"
	"phone" : "01011111111"
	"email" : "toma@gmail.com"
	"role" : "MASTER"
	"currentAddress" : "abc"
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| userId | String | 필수 | 고유 번호 |
| name | String | 필수 | 이름 |
| email | String | 필수 | 이메일 |
| role | String | 필수 | 권한 |
| createdAt | String | 필수 | 생성일시 |

### **응답 예시**

```jsx
{
  "userId": "tomas",
  "name": "new_user",
  "email": "user@example.com",
  "role": "customer",
  "createdAt": "2024-08-19T12:00:00Z"
}
```
  </div>
</details>
  <details>
  <summary><b>로그인</b></summary>
  <div markdown="1">

### **로그인 API (/**auth/login**)**

| **메서드** | **요청 URL** |
| --- | --- |
| POST | http://{SERVER_URL}/api/v1/auth/login |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 모두 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| userId | String | 필수 | 회원ID |
| password | String | 필수 | 비밀번호 |

### **요청 예시**

```jsx
POST /api/v1/auth/login
Content-Type: application/json

{
  "userId" : "tomas"
	"password" : "1234"
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| token | String | 필수 | 인증 시 필요 토큰 |
| userId | String | 필수 | 고유 번호 |
| name | String |  필수 | 이름 |
| role | String | 필수 | 권한 |

### **응답 예시**

```jsx
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": "tom",
  "name": "new_user",
  "role": "customer"
}
```
  </div>
  </details>
</p>


<br>

<p>
<strong> 2. User / Address </strong>
  <details>
  <summary><b>회원 정보 조회 (고객)</b></summary>
  <div markdown="1">

### 회원 정보 조회 (고객) **API (/user)**

| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/users |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |
| Authorization | String | 필수 | JWT 토큰 |

### **Role Requirement**

**권한:** 이 작업은 CUSTOMER, MANAGER, MASTER가 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |

### **요청 예시**

```jsx
GET /api/v1/users HTTP/1.1
Host: example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| name | String | 필수 | 이름 |
| phone | String | 필수 | 휴대폰번호 |
| email | String | 필수 | 이메일 |
| role | String | 필수 | 권한 |
| currentAddress | String | 선택 | 주소 |

### **응답 예시**

```jsx
{
    "name": "string",
    "phone": "string",
    "email": "string",
    "role": "string",
    "currentAddress": "string"
}
```
  </div>
  </details>
    <details>
  <summary><b>회원 정보 수정</b></summary>
  <div markdown="1">


### 회원 정보 수정 **API (/users)**

| **메서드** | **요청 URL** |
| --- | --- |
| PATCH | http://{SERVER_URL}/api/v1/users |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |
| Authorization | String | 필수 | JWT 토큰 |

### **Role Requirement**

**권한:** 이 작업은 CUSTOMER, MANAGER, MASTER가 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| password | String | 선택 | 비밀번호 |
| name | String | 선택 | 이름 |
| phone | String | 선택 | 휴대폰번호 |
| email | String | 선택 | 이메일 |
| currentAddress | String | 선택 | 주소 |

### **요청 예시**

```jsx
PATCH /api/v1/users HTTP/1.1
Host: example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

{
    "password": "string",        // 선택 사항, 비밀번호 변경 시
    "name": "string",            // 선택 사항, 이름 변경 시
    "phone": "string",           // 선택 사항, 휴대폰번호 변경 시
    "email": "string",           // 선택 사항, 이메일 변경 시
    "currentAddress": "string"   // 선택 사항, 현재주소 변경 시
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| userId | String | 필수 | 회원ID |
| name | String | 필수 | 이름 |
| phone | String | 필수 | 휴대폰번호 |
| email | String | 필수 | 이메일 |
| role | String | 필수 | 권한 |
| currentAddress | String | 선택 | 주소 |

### **응답 예시**

```jsx
{
    "userId": "string",
    "name": "string",
    "phone": "string",
    "email": "string",
    "role": "string",
    "currentAddress": "string"
}
```
  </div>
  </details>
    <details>
  <summary><b> 회원 탈퇴</b></summary>
  <div markdown="1">


### 회원 탈퇴 **API (/users)**

| **메서드** | **요청 URL** |
| --- | --- |
| DELETE | http://{SERVER_URL}/api/v1/users |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |
| Authorization | String | 필수 | JWT 토큰 |

### **Role Requirement**

**권한:** 이 작업은 CUSTOMER, MANAGER, MASTER가 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |

### **요청 예시**

```jsx
DELETE /api/v1/users HTTP/1.1
Host: example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| userId | String | 필수 | 회원ID |
| deletedAt | timestamp | 필수 | 삭제날짜 |

### **응답 예시**

```jsx
{
    "userId": "12345",
    "deletedAt": "2024-08-22T15:03:23Z"
}
```
  </div>
  </details>
    <details>
  <summary><b>배송지 전체 조회</b></summary>
  <div markdown="1">


### 배송지 전체 조회 **API (/users/address)**

| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/users/address?page=<int>&size=<int> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |
| Authorization | String | 필수 | JWT 토큰 |

### **Role Requirement**

**권한:** 이 작업은 CUSTOMER, MANAGER, MASTER가 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |

### **요청 예시**

```jsx
GET /api/v1/users/address?page=<int>&size=<int> HTTP/1.1
Host: example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| address | List<String> | 필수 | 배송지 주소 |

### **응답 예시**

```jsx
{
	"content": [
		{
		    "address": "12345",
		},
		{
		    "address": "12345",
		}
	],
	"pageable": {
	        "sort": {
	            "empty": true,
	            "sorted": false,
	            "unsorted": true
	        },
	        "offset": 0,
	        "pageSize": 2,
	        "pageNumber": 0,
	        "paged": true,
	        "unpaged": false
	    },
	    "last": false,
	    "totalPages": 3,
	    "totalElements": 5,
	    "size": 2,
	    "number": 0,
	    "sort": {
	        "empty": true,
	        "sorted": false,
	        "unsorted": true
	    },
	    "first": true,
	    "numberOfElements": 2,
	    "empty": false
	}
```
  </div>
  </details>
    <details>
  <summary><b>배송지 추가</b></summary>
  <div markdown="1">


### 배송지 추가 **API (/users/address)**

| **메서드** | **요청 URL** |
| --- | --- |
| POST | http://{SERVER_URL}/api/v1/users/address |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |
| Authorization | String | 필수 | JWT 토큰 |

### **Role Requirement**

**권한:** 이 작업은 CUSTOMER, MANAGER, MASTER가 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| address | String | 필수 | 배송지 주소 |

### **요청 예시**

```jsx
DELETE /api/v1/users HTTP/1.1
Host: example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

{
    "address": "123 Main St"
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| address | List<String> | 필수 | 배송지 주소 |

### **응답 예시**

```jsx
{
    "addressId": "67890",
    "userId": "12345",
    "address": "Main St"
}
```
  </div>
  </details>
      <details>
  <summary><b>배송지 수정</b></summary>
  <div markdown="1">


### 배송지 수정 **API (/users/address/**<int:address_id>**)**

| **메서드** | **요청 URL** |
| --- | --- |
| PATCH | http://{SERVER_URL}/api/v1/users/address/<int:address_id> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |
| Authorization | String | 필수 | JWT 토큰 |

### **Role Requirement**

**권한:** 이 작업은 CUSTOMER, MANAGER, MASTER가 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| addressId | UUID | 필수 | 주소 id
Path param |

### **요청 예시**

```jsx
DELETE /api/v1/users HTTP/1.1
Host: example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| addressId | UUID | 필수 | 고유값 |
| address | String | 필수 | 배송지 주소 |

### **응답 예시**

```jsx
{
    "addressId": "67890",
    "address": "123 Main St"
}
```
  </div>
  </details>
      <details>
  <summary><b>회원가입</b></summary>
  <div markdown="1">
  
  </div>
  </details>
   <details>
  <summary><b>배송지 삭제</b></summary>
  <div markdown="1">


### 배송지 삭제 **API (/users/address/**<int:address_id>**)**

| **메서드** | **요청 URL** |
| --- | --- |
| DELETE | http://{SERVER_URL}/api/v1/users/address/<int:address_id> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |
| Authorization | String | 필수 | JWT 토큰 |

### **Role Requirement**

**권한:** 이 작업은 CUSTOMER, MANAGER, MASTER가 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| addressId | UUID | 필수 | 주소 id
Path param |

### **요청 예시**

```jsx
DELETE /api/v1/users HTTP/1.1
Host: example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| addressId | UUID | 필수 | 주소아이디 |
| deletedAt | String | 필수 | 삭제 날짜 |

### **응답 예시**

```jsx
{
    "userId": "12345",
    "deletedAt": "2024-08-22T15:03:23Z"
}
```
  </div>
  </details>
</p>

<br>

<p>
<strong> 3. Order </strong>
  <details>
  <summary><b>주문 내역 전체 출력</b></summary>
  <div markdown="1">


### **게시물 생성 API (/posts)**

| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/orders?page=<int>&size=<int> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰, role이 MASTER여야 함) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 CUSTOMER, OWNER, MANAGER, MASTER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| page | Integer | 필수 | 쿼리 스트링 page 값 |
| size | Integer | 필수 | 쿼리 스트링 size 값 |

### **요청 예시**

```jsx
GET /api/v1/orders?page=<int>&size=<int>
Authorization: Bearer jwt_token_string
Content-Type: application/json

```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| orderId | Long | 필수 | 주문 고유 번호 |
| storeName | String | 필수 | 주문 가게 이름 |
| menu | String | 필수 | 주문 메뉴 |
| totalPrice | Integer | 필수 | 주문 총 가격 |
| status | String | 필수 | 주문 진행 상태 |

### **응답 예시**

```jsx
{
    "content": [
        {
            "orderId": 1,
            "storeName":"도미노피자",
            "menu":"포테이토피자",
            "totalPrice":50000,
            "status":"조리 완료"
        },
        {
            "orderId": 2,
            "storeName":"BBQ",
            "menu":"황금올리브치킨",
            "totalPrice":25000,
            "status":"접수중"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageSize": 2,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 3,
    "totalElements": 5,
    "size": 2,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```
  </div>
  </details>
    <details>
  <summary><b>주문 세부 내역</b></summary>
  <div markdown="1">


### **게시물 생성 API (/posts)**

| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/orders/<int:order_id> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰, role이 MASTER여야 함) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 CUSTOMER, OWNER, MANAGER, MASTER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| orderId | Long | 필수 | 주문 고유 번호{ |
|  |  |  |  |
|  |  |  |  |

### **요청 예시**

```jsx
GET /api/v1/orders
Authorization: Bearer jwt_token_string
Content-Type: application/json

{
	"orderId":1
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| storeName | String | 필수 | 주문 가게 이름 |
| menu | String | 필수 | 주문 메뉴 |
| paymentMethod | String | 필수 | 결제 수단 |
| totalPrice | Integer | 필수 | 주문 총 가격 |
| requests | String | 필수 X | 요청사항 |
| status | String | 필수 | 주문 진행 상태 |
| createAt | TimeStamp | 필수 | 주문일시 |

### **응답 예시**

```jsx
{
   "storeName":"도미노피자",
   "menu":"포테이토피자",
   "paymentMethod":"CreditCard",
   "totalPrice":50000,
   "requests":"리뷰이벤트 참여",
   "status":"조리 완료",
   "createdAt":"2024.08.21"
}
```
  </div>
  </details>
    <details>
  <summary><b>음식 주문</b></summary>
  <div markdown="1">


| **메서드** | **요청 URL** |
| --- | --- |
| POST | http://{SERVER_URL}/api/v1/orders?page=0&size=9&sort=status,asc&sort=createdAt,desc |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰, role이 MASTER여야 함) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 CUSTOMER, OWNER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| paymentMethod | String | 필수 | 결제 수단 |
| totalPrice | Integer | 필수 | 주문 총 가격 |
| requests | String | 필수 X | 요청사항 |

### **요청 예시**

```jsx
GET /api/v1/orders?page=<int>&size=<int>
Authorization: Bearer jwt_token_string
Content-Type: application/json

{
    "storeId" : "28bc7706-19ca-44db-bccb-dc737283f124",
    "menuIds" : [
        {
            "menuId" : "5588bb39-4da7-4ed5-8853-fce146b01bc2",
            "quantity" : 1
        },
        {
            "menuId" : "42879067-af5c-4088-b2dd-048a390c1f59",
            "quantity" : 3
        }
    ],
    "requests" : "리뷰이벤트",
    "addressId" : "b7dd982c-2ae9-4a71-87d1-16e836f07aff"
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
|  |  |  |  |

### **응답 예시**

```jsx
{
    "resultCode": "SUCCESS",
    "result": {
        "orderId": "6f41ed37-fd2e-4e28-9ab8-492029a54568",
        "storeName": "맛있는 김밥집",
        "totalPrice": 22000,
        "status": "CREATED"
    }
}
```
  </div>
  </details>
    <details>
  <summary><b>주문 상태 변경</b></summary>
  <div markdown="1">


### **게시물 생성 API (/posts)**

| **메서드** | **요청 URL** |
| --- | --- |
| PATCH | http://{SERVER_URL}/api/v1/orders/<int:order_id> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰, role이 MASTER여야 함) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| orderId | Long | 필수 | 주문 고유 번호 |
| status | String | 필수 X | 주문 진행 상태 |

### **요청 예시**

```jsx
GET /api/v1/orders?page=<int>&size=<int>
Authorization: Bearer jwt_token_string
Content-Type: application/json

{
    "status" : "COMPLETED"
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| storeName | String | 필수 | 주문 가게 이름 |
| menu | String | 필수 | 주문 메뉴 |
| paymentMethod | String | 필수 | 결제 수단 |
| totalPrice | Integer | 필수 | 주문 총 가격 |
| requests | String | 필수 X | 요청사항 |
| status | String | 필수 | 주문 진행 상태 |
| createAt | TimeStamp | 필수 | 주문일시 |

### **응답 예시**

```jsx
{
    "resultCode": "SUCCESS",
    "result": "6f41ed37-fd2e-4e28-9ab8-492029a54568"
}
```
  </div>
  </details>
    <details>
  <summary><b>주문 취소</b></summary>
  <div markdown="1">


### **게시물 생성 API (/posts)**

| **메서드** | **요청 URL** |
| --- | --- |
| DELETE | http://{SERVER_URL}/api/v1/orders/<int:order_id> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰, role이 MASTER여야 함) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 CUSTOMER, OWNER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| orderId | Long | 필수 | 주문 고유 번호 |

### **요청 예시**

```jsx
GET /api/v1/orders?page=<int>&size=<int>
Authorization: Bearer jwt_token_string
Content-Type: application/json

```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
|  |  |  |  |

### **응답 예시**

```jsx
{
   "주문이 취소되었습니다"
}
```
  </div>
  </details>
</p>

<br>

<p>
<strong> 4. Payment </strong>
  <details>
  <summary><b> 결제</b></summary>
  <div markdown="1">


### 결제 **API (/payments)**

| **메서드** | **요청 URL** |
| --- | --- |
| POST | http://{SERVER_URL}/api/v1/payments |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |
| Authorization | String | 필수 | JWT 토큰 |

### **Role Requirement**

**권한:** 이 작업은 CUSTOMER, MANAGER가 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| orderId | String | 필수 | 주문id |

### **요청 예시**

```jsx
POST /api/v1/payments HTTP/1.1
Host: example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

{
    "orderId":"55ee4040-c634-4acc-991c-98fe6cb20dfa",
    "merchantUid" : "merchantUid",
    "impUid" : "impUid",
    "payMethod" : "CARD",
    "paidAmount" : 22000,
    "status" : "PAID"
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| status | String | 필수 | 결제 상태값 |
| payAmount | Integer | 필수 | 결제 금액 |

### **응답 예시**

```jsx
{
    "resultCode": "SUCCESS",
    "result": {
        "status": "PAID",
        "payAmount": 22000,
        "payMethod": "CARD"
    }
}
```
  </div>
  </details>
    <details>
  <summary><b>결제내역 리스트</b></summary>
  <div markdown="1">


### 결제 **API (/payments)**

| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/payments?page=<int>&size=<int> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |
| Authorization | String | 필수 | JWT 토큰 |

### **Role Requirement**

**권한:** 이 작업은 CUSTOMER, MANAGER 가 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |

### **요청 예시**

```jsx
POST /api/v1/payments?page=<int>&size=<int> HTTP/1.1
Host: example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| status | String | 필수 | 결제 상태값 |
| payAmount | Integer | 필수 | 결제 금액 |
| paymentMethod | String | 필수 | 결제 수단 |

### **응답 예시**

```jsx
{
    "resultCode": "SUCCESS",
    "result": {
        "content": [
            {
                "paymentId": "11c89f61-0d16-4fd3-804f-82c0d73798d4",
                "status": "PAID",
                "payAmount": 22000,
                "paymentMethod": "CARD"
            },
            {
                "paymentId": "6d0605a4-7495-423f-bf75-63e064e1a83d",
                "status": "PAID",
                "payAmount": 22000,
                "paymentMethod": "CARD"
            }
        ],
        "pageable": {
            "pageNumber": 0,
            "pageSize": 10,
            "sort": {
                "empty": false,
                "sorted": true,
                "unsorted": false
            },
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalElements": 2,
        "totalPages": 1,
        "size": 10,
        "number": 0,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "first": true,
        "numberOfElements": 2,
        "empty": false
    }
}
```
  </div>
  </details>
    <details>
  <summary><b>결제 상세 내역</b></summary>
  <div markdown="1">


### 결제 상세 내 **API (/payments/<int:payment_id>)**

| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/payments**/**<int:payment_id> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Content-Type | String | 필수 | application/json |
| Authorization | String | 필수 | JWT 토큰 |

### **Role Requirement**

**권한:** 이 작업은 CUSTOMER, MANAGER가 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| paymentId | String | 필수 | 결제ID
Path Param |

### **요청 예시**

```jsx
POST /api/v1/payments HTTP/1.1
Host: example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| status | String | 필수 | 결제 상태값 |
| payAmount | Integer | 필수 | 결제 금액 |

### **응답 예시**

```jsx
{
    "resultCode": "SUCCESS",
    "result": {
        "paymentId": "11c89f61-0d16-4fd3-804f-82c0d73798d4",
        "status": "PAID",
        "payAmount": 22000,
        "paymentMethod": "CARD"
    }
}
```
  </div>
  </details>
</p>

<br>

<p>
<strong> 5. Store </strong>
  <details>
  <summary><b>등록</b></summary>
  <div markdown="1">


| **메서드** | **요청 URL** |
| --- | --- |
| POST | http://{SERVER_URL}/api/v1/stores |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰, role이 OWNER여야 함) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| name | String | 필수 | 제목 |
| address | String | 필수 | 내용 |
| phone | String | 필수 | 전화번호 |
| content | String | 선택 | 소개글 |
| minDeliveryPrice | Integer | 필수 | 최소주문금액 |
| openTime | `LocalTime` | 필수 | 오픈시간 |
| closeTime | `LocalTime` | 필수 | 마감시간 |
| closedDays | String | 선택 | 휴무일 |
| deliveryAddress | String | 선택 | 배달지역 |

### **요청 예시**

```java
POST /api/v1/stores
Authorization: Bearer jwt_token_string
Content-Type: application/json

{
  "name": "맛있는 김밥집",
  "address": "서울특별시 강남구 테헤란로 123",
  "phone": "02-1234-5678",
  "content": "정성껏 만든 김밥을 판매하는 맛집입니다.",
  "minDeliveryPrice": 15000,
  "openTime":"09:00",
  "closeTime":"22:00",
  "closedDays": "일요일",
  "deliveryAddress": "강남구, 서초구"
}

```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
|  |  |  |  |

### **응답 예시**

```java

```
  </div>
  </details>
    <details>
  <summary><b>전체 조회</b></summary>
  <div markdown="1">


| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/stores?page=<int>&size=<int>&category=”pizza” &location=”seoul” |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 CUSTOMER, OWNER, MANAGER 인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** | 비고 |
| --- | --- | --- | --- | --- |
| page | Integer | 필수 | 쿼리 스트링 page 값 |  |
| size | Integer | 필수 | 쿼리 스트링 size 값 |  |
| category | String | 선택 | 쿼리 스트링 category 값 |  |
| location | String | 필수 | 쿼리 스트링 location | 지역이 없으면, 무수히 많은 가게들이 나오기 때문에 필수 |

### **요청 예시**

```java
GET /api/v1/stores?page=<int>&size=<int>&category=”pizza” &location=”seoul”

Authorization: Bearer jwt_token_string
Content-Type: application/json

```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| name | String | 필수 | 가게 이름 |
| minDeliveryPrice | Integer | 필수 | 최소 배달 금액 |
| List<MenuResponseDto> |  |  |  |
| — name | String | 필수 | 메뉴 이름 |
| — price | Integer | 필수 | 메뉴 가격 |

### **응답 예시**

```java
{
	"content": [
		{
			"name": "가게1",
			"minDeliveryPrice": 10000 
			"MenuResponseDto": [
				{
					"name": "메뉴1-1",
					"price": 15000
				},
				{
					"name": "메뉴1-2",
					"price": 10000
				}
			]
		},
		{
			"name": "가게2",
			"minDeliveryPrice": 15000
			"MenuResponseDto": [
				{
					"name": "메뉴2-1",
					"price": 20000
				},
				{
					"name": "메뉴2-2",
					"price": 30000
				}
			]
		}
	],
	 "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 50,
  "totalPages": 5,
  "last": false,
  "first": true,
  "numberOfElements": 10,
  "empty": false
	
}
```
  </div>
  </details>
    <details>
  <summary><b>상세 조회</b></summary>
  <div markdown="1">


| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/stores/int:store_id |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | JWT 토큰 |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 CUSTOMER, OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| storeId | UUID | 필수 | 가게 ID 쿼리 스트링 |

### **요청 예시**

```java
GET /api/v1/stores/int:store_id

Authorization: Bearer jwt_token_string
Content-Type: application/json
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| name | String | 필수 | 가게 이름 |
| content | String | 선택 | 소개 글 |
| minDeliveryPrice | Integer | 필수  | 최소 주문 금액 |
| operationHours | String | 필수 | 운영 시간 |
| closedDays | String | 선택 | 휴무일 |
| deliceryAddress | String | 필수 | 배달 지역 |
| List<MenuResponseDto> |  |  |  |
| — name | String | 필수  | 메뉴 이름 |
| — price | Integer | 필수  | 메뉴 가격 |

### **응답 예시**

```java
{
	"content":
	{
		"name": "가게1",
		"content": "소개 글입니다.",
		"minDeliveryPrice": 10000,
		"operationHours": "오전10시 ~ 오후 10시",
		"closedDays": "내일 쉬고 싶다...",
		"MenuResponseDto": [
			{
				"name": "메뉴1",
				"price": 15000
			},
			{
				"name": "메뉴2",
				"price": 10000
			},
			{
				"name": "메뉴3",
				"price": 20000
			}
		]
	},
 "pageable": {
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "pageNumber": 0,
  "pageSize": 10,
  "offset": 0,
  "paged": true,
  "unpaged": false
},
"totalElements": 50,
"totalPages": 5,
"last": false,
"first": true,
"numberOfElements": 10,
"empty": false

}
```
  </div>
  </details>
    <details>
  <summary><b>수정</b></summary>
  <div markdown="1">


| **메서드** | **요청 URL** |
| --- | --- |
| PATCH | http://{SERVER_URL}/api/v1/stores/int:store_id |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | JWT 토큰 |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| storeId | UUID | 필수 | 가게 ID 쿼리 스트링 |

### **요청 예시**

```java
PATCH /api/v1/stores/int:store_id

Authorization: Bearer jwt_token_string
Content-Type: application/json
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
|  |  |  |  |

### **응답 예시**

```java

```
  </div>
  </details>
    <details>
  <summary><b>삭제</b></summary>
  <div markdown="1">


| **메서드** | **요청 URL** |
| --- | --- |
| DELETE | /api/v1/stores/int:store_id |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰, role이 MASTER여야 함) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| storeId | UUID | 필수 | 가게 ID 쿼리 스트링 |

### **요청 예시**

```java
DELETE /api/v1/stores/int:store_id

Authorization: Bearer jwt_token_string
Content-Type: application/json
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
|  |  |  |  |

### **응답 예시**

```java

```
  </div>
  </details>
</p>

<br>

<p>
<strong> 6. Menu </strong>
  <details>
  <summary><b>메뉴 목록 조회</b></summary>
  <div markdown="1">


### **메뉴 목록 조회 API (**/stores/<int:store_id>/menus**)**

| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/stores/<int:store_id>/menus?page=<int>&size=<int> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | JWT 토큰 |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 CUSTOMER, OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| storeId | UUID | 필수 | 가게id |

### **요청 예시**

```jsx
GET /api/v1/stores/<int:store_id>/menus?page=<int>&size=<int>
Authorization: Bearer jwt_token_string
Content-Type: application/json
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| meunuId | String | 필수 | 메뉴ID |
| name | String | 필수 | 메뉴이름 |
| price | String | 필수 | 가격 |

### **응답 예시**

```jsx
{
    "content": [
        {
            "meunuId": 1
            "menu":"포테이토피자",
            "price":50000
        },
        {
            "orderId": 2,
            "storeName":"BBQ",
            "menu":"황금올리브치킨",
            "totalPrice":25000,
            "status":"접수중"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageSize": 2,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 3,
    "totalElements": 5,
    "size": 2,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```
  </div>
  </details>
    <details>
  <summary><b>메뉴 상세 조회</b></summary>
  <div markdown="1">


### **메뉴 목록 조회 API (**/stores/<int:store_id>/menus**)**

| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/stores/<int:store_id>/menus/<int:menu_id>?page=<int>&size=<int> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | JWT 토큰 |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 CUSTOMER, OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| storeId | UUID | 필수 | 가게id
Path Param |
| menuId | UUID | 필수 | menuid
Path Param |

### **요청 예시**

```jsx
GET /api/v1/stores/<int:store_id>/menus/<int:menu_id>?page=<int>&size=<int>
Authorization: Bearer jwt_token_string
Content-Type: application/json
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| meunuId | UUID | 필수 | 메뉴ID |
| name | String | 필수 | 메뉴이름 |
| price | String | 필수 | 가격 |

### **응답 예시**

```jsx
{
    "meunuId": 1
    "menu":"포테이토피자",
    "price":50000
}
```
  </div>
  </details>
    <details>
  <summary><b>메뉴 추가</b></summary>
  <div markdown="1">


### **메뉴 추가 API (**/stores/<int:store_id>/menus**)**

| **메서드** | **요청 URL** |
| --- | --- |
| POST | http://{SERVER_URL}/api/v1/stores/<int:store_id>/menus?page=<int>&size=<int> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | JWT 토큰 |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| storeId | UUID | 필수 | 가게id
Path Param |
| name | String | 필수 | 메뉴이름 |
| price | String | 필수 | 가격 |

### **요청 예시**

```jsx
PATCH /api/v1/stores/<int:store_id>/menus?page=<int>&size=<int>
Authorization: Bearer jwt_token_string
Content-Type: application/json

{
    "name" : "야채김밥",
    "price" : 5500,
    "quantity" : 100
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| meunuId | UUID | 필수 | 메뉴ID |
| name | String | 필수 | 메뉴이름 |
| price | String | 필수 | 가격 |

### **응답 예시**

```jsx
{
    "resultCode": "SUCCESS",
    "result": {
        "menuId": "1abd6d9a-353e-4445-89a4-c207e715736e",
        "name": "치즈김밥",
        "price": 5800
    }
}
```
  </div>
  </details>
    <details>
  <summary><b> 메뉴 수정</b></summary>
  <div markdown="1">


### **메뉴 수정 API (**/stores/<int:store_id>/menus**)**

| **메서드** | **요청 URL** |
| --- | --- |
| PATCH | http://{SERVER_URL}/api/v1/stores/<int:store_id>/menus/<int:menu_id>?page=<int>&size=<int> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | JWT 토큰 |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| storeId | UUID | 필수 | 가게id
Path Param |
| menuId | UUID | 필수 | menuid
Path Param |
| name | String | 필수 | 메뉴명 |
| price | Intger | 필수 | 가격 |

### **요청 예시**

```jsx
PATCH /api/v1/stores/<int:store_id>/menus/<int:menu_id>?page=<int>&size=<int>
Authorization: Bearer jwt_token_string
Content-Type: application/json

{
    "name" : "야채김밥",
    "price" : 5500,
    "quantity" : 100
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| meunuId | UUID | 필수 | 메뉴ID |
| name | String | 필수 | 메뉴이름 |
| price | String | 필수 | 가격 |

### **응답 예시**

```jsx
{
    "resultCode": "SUCCESS",
    "result": {
        "menuId": "1abd6d9a-353e-4445-89a4-c207e715736e",
        "name": "치즈듬뿍김빱",
        "price": 5800
    }
}
```
  </div>
  </details>
    <details>
  <summary><b>메뉴 삭제</b></summary>
  <div markdown="1">


### **메뉴 삭제 API (/stores)**

| **메서드** | **요청 URL** |
| --- | --- |
| DELETE | http://{SERVER_URL}/api/v1/stores/int:store_id/menus/int:menu_id |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | JWT 토큰 |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| storeId | UUID | 필수 | 쿼리 스트링 가게 ID |
| menuId | UUID | 필수 | 쿼리 스트링 가게 ID |

### **요청 예시**

```jsx
DELETE /api/v1/stores/int:store_id/menus/int:menu_id

Authorization: Bearer jwt_token_string
Content-Type: application/json
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
|  |  |  |  |

### **응답 예시**

```jsx

```
  </div>
  </details>
</p>

<br>

<p>
<strong> 7. Category </strong>
  <details>
  <summary><b>조회</b></summary>
  <div markdown="1">


| **메서드** | **요청 URL** |
| --- | --- |
| GET | http://{SERVER_URL}/api/v1/category |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | JWT 토큰 |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
|  |  |  |  |

### **요청 예시**

```java
GET /api/v1/store_categories

Authorization: Bearer jwt_token_string
Content-Type: application/json
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| name | String | 필수 | 내 가게의 카테고리 이름 |

### **응답 예시**

```java
{
	"content": [
		{
			"name": "한식"
		},
		{
			"name": "일식"
		}
	]
}
```
  </div>
  </details>
    <details>
  <summary><b>삭제</b></summary>
  <div markdown="1">


| **메서드** | **요청 URL** |
| --- | --- |
| DELETE | http://{SERVER_URL}/api/v1/category/int:store_category_id |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | JWT 토큰 |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| categoryId | UUID | 필수 | 카테고리 ID, 쿼리 스트링 |

### **요청 예시**

```java
DELETE /api/v1/store_categories/<int:store_category_id>

Authorization: Bearer jwt_token_string
Content-Type: application/json
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
|  |  |  |  |

### **응답 예시**

```java

```
  </div>
  </details>
    <details>
  <summary><b>음식점 카테고리 수정</b></summary>
  <div markdown="1">


| **메서드** | **요청 URL** |
| --- | --- |
| PATCH | http://{SERVER_URL}/api/v1/category/<int:store_category_id> |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰, role이 OWNER, MANAGER여야 함) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| name | String | 필수 | 제목 |
| storeCategoryId | UUID | 필수 | 카테고리 아이디 |

### **요청 예시**

```java
PATCH /api/v1/store_categories/<int:store_category_id>
Authorization: Bearer jwt_token_string
Content-Type: application/json

{
  "name": "한식"
}

```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| categoryId | UUID | 필수 | 카테고리 아이디 |
| name | String | 필수 | 이름 |

### **응답 예시**

```java
{
	"categoryId" : "UUID",
	"name:" : "한식"
}
```
  </div>
  </details>
    <details>
  <summary><b>음식점 카테고리 추가</b></summary>
  <div markdown="1">


| **메서드** | **요청 URL** |
| --- | --- |
| POST | http://{SERVER_URL}/api/v1/category |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰, role이 OWNER, MANAGER여야 함) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이 OWNER, MANAGER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| name | String | 필수 | 제목 |

### **요청 예시**

```java
POST /api/v1/store_categories
Authorization: Bearer jwt_token_string
Content-Type: application/json

{
  "name": "한식"
}

```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| categoryId | UUID | 필수 | 카테고리 아이디 |
| name | String | 필수 | 이름 |

### **응답 예시**

```java
{
	"categoryId" : "UUID",
	"name:" : "한식"
}
```
  </div>
  </details>
</p>

<br>

<p>
<strong> 8. AI </strong>
  <details>
  <summary><b>상품설명 자동 생성 AI</b></summary>
  <div markdown="1">


### **게시물 생성 API (/posts)**

| **메서드** | **요청 URL** |
| --- | --- |
| POST | http://{SERVER_URL}/api/v1/ai |

### **Request Header**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| Authorization | String | 필수 | (JWT 토큰, role이 MASTER여야 함) |
| Content-Type | String | 필수 | application/json |

### **Role Requirement**

**권한:** 이 작업은 role이  OWNER인 사용자만 수행할 수 있습니다.

### **Request Elements**

| **파라미터** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| text | String | 필수 | AI요청 메세지 |

### **요청 예시**

```jsx
GET /api/v1/orders?page=<int>&size=<int>
Authorization: Bearer jwt_token_string
Content-Type: application/json

{
	"contents":[
			{
				"parts":[
					{
						"text":"만두 상품의 이름을 추천해줘"
			}]
	}]
}
```

### **Response Elements**

| **필드** | **타입** | **필수여부** | **설명** |
| --- | --- | --- | --- |
| message | String |  | AI에서 반환해준 값 |

### **응답 예시**

```jsx
{
   "message" : "최강 만두"
}
```
  </div>
  </details>
</p>
