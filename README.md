# 목차

1. [프로젝트 개요](#-프로젝트-개요)
2. [프로젝트 기술 스택](#-프로젝트-기술-스택)
3. [Use Case / 유저 시나리오](#-use-case--유저-시나리오)
4. [Flow Chart](#-flow-chart)
5. [기능 구조도](#-기능-구조도)
6. [메뉴 구조도](#-메뉴-구조도)
7. [ERD](#-erd)
8. [Git Branch Strategy](#-git-branch-strategy)

# 📜 프로젝트 개요
![header](https://capsule-render.vercel.app/api?type=rect&color=transparent&height=200&section=header&text=Show%20your%20worth!&desc=당신의%20가치를%20보여주세요.&descAlign=70&descAlignY=70&fontSize=70&fontColor=703ee5&animation=fadeIn)

본 프로젝트는 `버스킹` 서비스를 제공하는 시스템을 개발합니다. 
`버스킹` 서비스는 기본적으로 `HOST`가 지도에 `버스킹` 장소와 날짜를 지정하면, 서비스 이용자 모두가 볼 수 있게 합니다.
이를 통해 자유롭게 사람들이 모여 `버스킹` 활동을 합니다.
`버스킹`은 '거리 공연'을 의미하나, 서비스에선 공연이 아닌, **사람들이 모여 할 수 있는 모든 모임 활동**을 의미합니다.

`버스킹` 서비스를 이용하는 사용자는 `GUEST`, `USER`, `HOST`, `ADMIN`으로 나뉩니다.
`GUEST`는 비가입회원으로 `버스킹` 조회 및 검색만 가능합니다.
`USER`는 가입회원으로 `버스킹` 조회, 검색, 참여, 개설, 후기 작성, `HOST` 검색 등 서비스를 이용할 수 있습니다. 
`HOST`는 `버스킹`을 한 번 개설한 적이 있는 `USER`로, `HOST` 검색 시 노출됩니다.
`ADMIN`은 관리자로, 서비스를 관리합니다.

# 🖥 프로젝트 기술 스택

- <img src="https://img.shields.io/badge/Java-17-007396?style=flat&logo=java&logoColor=007396" />
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.15-6DB33F?logo=spring%20boot&logoColor=6DB33F)
- ![MyBatis](https://img.shields.io/badge/MyBatis-2.3.1-000000?logo=&logoColor=000000)
- ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-2.6.15-6DB33F?logo=&logoColor=6DB33F)
- ![MySQL](https://img.shields.io/badge/MySQL-8.0.33-4479A1?logo=mysql&logoColor=4479A1)
- ![Gradle](https://img.shields.io/badge/Gradle-7.4-02303A?logo=gradle&logoColor=02303A)
- ![IntelliJ](https://img.shields.io/badge/IntelliJ-2023.1-000000?logo=intellijidea&logoColor=000000)

# 🎭 Use Case / 유저 시나리오

대표 케이스만 기입되어 있습니다. 모든 케이스를 확인하려면 [WIKI - Use Case / 유저 시나리오](https://github.com/f-lab-edu/show-your-worth/wiki/03.-Use-Case---%EC%9C%A0%EC%A0%80-%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4)를 참고합니다.

## 1. COMMON

### USE CASE 1 - 버스킹 조회
1. 애플리케이션이 실행되면 홈 화면이 표시된다
2. 홈 화면에 지도가 표시된다
   - 지도는 현재 위치를 기준으로 표시되어 있다
3. 지도에 사용자 중심으로 개설된 `버스킹`이 표시된다
4. 지도의 특정 버튼을 통해 확장하거나, 이동시키면 표시된 지도를 기준으로 `버스킹`이 표시된다

## 2. GUEST

### USE CASE 1 - 회원가입

1. 마이 페이지 탭을 누른다
2. 회원가입 버튼을 누르면 아이디와 비밀번호를 입력하는 UI가 표시된다
3. 아이디를 입력한다
   - 아이디는 영문과 숫자 조합 6~15자로 제한한다
   - 존재하는 아이디가 있으면 중복 아이디 존재 메시지를 응답한다
   - 존재하는 아이디가 없으면 사용 가능 메시지를 응답한다
4. 비밀번호를 입력한다
   - 비밀번호는 6~20자로 제한하며, 특수 문자는 `~,!,@,#,$,%,^,&,*`만 포함한다
5. 아이디와 비밀번호가 입력이 완료되고 다음을 누르면 닉네임을 입력하는 UI가 표시된다
6. 닉네임을 입력한다
   - 닉네임은 2~15자로 제한하며, 특수 문자는 포함할 수 없다
   - 존재하는 닉네임이 있으면 중복 아이디 존재 메시지를 응답한다
   - 존재하는 닉네임이 없으면 사용 가능 메시지를 응답한다
7. 닉네임 입력이 완료되고 다음을 누르면 전화번호 인증 UI가 표시된다
8. 전화번호를 입력하고 인증 번호 발송 버튼을 누른다
9. 3분 내에 수신한 인증번호를 입력하고 인증하기 버튼을 누른다
10. 회원가입이 성공적으로 마치면 자동으로 로그인되어 홈 화면으로 돌아간다

## 3. USER / HOST

### USE CASE 2 - 버스킹 참여

1. 지도에 표시되거나 검색한, 시작 전 또는 진행 중인 버스킹을 선택한다
2. 버스킹에 대한 정보 UI가 표시된다
3. 버스킹 참여 버튼을 누른다
    - 인원이 가득찬 경우, 인원이 가득찼다는 응답을 한다
    - 참석에 성공한 경우, 참석에 성공했다는 응답을 한 후 4번 흐름으로 간다
4. 홈 화면으로 돌아가며 진행 중인 버스킹이 표시된다
5. `HOST`에게 해당 `USER`가 참석했다는 알림을 전송한다

### USE CASE 4 - 버스킹 생성

1. 홈 화면에 있는 + 버튼을 누른다
2. 지도를 움직여 생성할 위치를 선택한다
3. 제목을 적는다
4. 사진을 등록하고 내용을 적는다(Opt.)
5. 시작 날짜 및 시간을 적는다
6. 생성 버튼을 누르면 정말 생성하겠는지 응답한다
   - 취소를 누르면 4번 흐름으로 이동한다
   - 확인을 누르면 6번 흐름으로 이동한다
7. 홈 화면으로 돌아가고 생성한 버스킹이 표시된다

### USE CASE 6 - 버스킹 종료(자동)

1. 생성한 진행 중인 버스킹 진행 시간이 만료된다
2. 애플리케이션이 활성화되었을 때 또는 홈 화면에서 생성한 진행 중인 버스킹을 선택했을 때 버스킹 종료 여부를 묻는다
   - 취소를 누르면 홈 화면으로 돌아가 모임이 계속 진행된다
   - 확인을 누르면 3번 흐름으로 진행한다
3. 버스킹 후기를 작성한다
   - 사진을 등록하거나 내용을 적는다
   - 중간에 취소를 누르면 개인 피드에 `USER`만 볼 수 있는 피드로 등록된다
   - `private` 설정 값에 따라 공개 피드로 등록된다
4. 홈 화면으로 돌아간다

### USE CASE 7 - 버스킹 종료(수동)

1. 생성한 진행 중인 버스킹을 선택한다
2. 버스킹에 대한 정보 UI가 표시된다
3. 버스킹 종료 버튼을 누르며 종료할 것인지 응답한다
   - 취소를 누르면 홈 화면으로 돌아가 모임이 계속 진행된다
   - 확인을 누르면 3번 흐름으로 진행한다
4. 버스킹 후기를 작성한다
   - 사진을 등록하거나 내용을 적는다
   - 중간에 취소를 누르면 개인 피드에 `USER`만 볼 수 있는 피드로 등록된다
   - `private` 설정 값에 따라 공개 피드로 등록된다
5. 홈 화면으로 돌아간다

### USE CASE 10 - HOST 구독

1. 검색한 `HOST` 또는 참여한 `HOST`의 프로필을 선택한다
2. `HOST`에 대한 정보 UI가 표시된다

## 4. ADMIN

### USE CASE 3 - 공지사항 등록

1. 마이 페이지 탭을 클릭한다
2. 공지사항 추가를 누른다
3. 공지사항을 입력하고 확인을 누른다
   - 즉시 공지 속성을 선택 후 누르면 즉시 모든 `USER`에게 공지사항이 전송되고 마이 페이지 탭으로 이동한다
   - 선택하지 않으면 4번 흐름으로 진행한다
4. 마이 페이지 탭으로 이동한다
5. 대기 중인 공지사항을 누르면 전송 안된 공지사항 목록이 보인다
6. 공지사항 우측에 보내기 버튼을 누른다
7. 모든 `USER`에게 공지사항이 전송된다

# 🎞 Flow Chart

대표 케이스만 기입되어 있습니다. 모든 케이스를 확인하려면 [WIKI - Flow Chart](https://github.com/f-lab-edu/show-your-worth/wiki/02.-Flow-Chart)를 참고합니다.

## 1. COMMON

### 버스킹 조회

![버스킹 조회](https://www.planttext.com/api/plantuml/png/b99DJy9048Rl-ol66noCqICSvEf5pnekJQpGGDkaBV7Qa4eGJ8J8TnO2IHL9UcWG8qI8_wSx6_-2MsZylCXXDvkTcMVUl3Flhsyfqv8Cgw3coOwLbLKP95N9I8IOEKK3GpTbyGmp24bWSyWRD-p1mwMD_HbUTG2hBczT9qMGTyl0kmqscM6x1ZYssQq3l4wnIfD4qoqP32MJjHB0EmuEF43guAB3xfSGp7rqRQHBDg9H1I6NMIKlnxwWKU0m3ee9iLFPZ1E0nARDkk4QnviKiEXXRu83yUVMiUfjm6ajy2awBgjHO5C7AoulBZxr2TQ5RkH29EpjYZYlBB4lz9UUUNEMrivqNOKZRU-aO2MZN90oagA55MnSnfulYC3f473auw3-x-838GkmL0jyc_LNW4CgG3XT6xDbTtP7sQ0JlCo_rqQ3sDW3xCaEfgk_aFsVAhOdoTev4MSpjMCz_diXq0bjRsDhnegFmkymdwJ4BHRl0m00__y30000)

## 2. GUEST

### 회원가입

![회원가입](https://www.planttext.com/api/plantuml/png/UhzxlqDnIM9HIMbk3fTYIKPgRgf2OcvcUY8N5rYOYylgzVAEDtEtABpjcm6atYnlK7ZRl3Ocx-sidZUp9xpQqF1cNgirK9Lxg6jmo9jPAnMG9HGyNVG3mXNUzky1AYbABKXDBE6oUZEr1MZGwobJ580gNYzOQWsLKIZFoCn9rK18lzhKy6RPN4s5p685ZJTDQu26lzxM0JHO4s7MwvqjhpSqlDxKyNR63kp6eQZ0Oo8E3xpkxdpTikJDl1OqJo34iTiAboR3IXG_pTq1r0wrtEhDWfQtCwUWUHVc9g0qM35K-lM6EM_Q5bgZ2wFfWWKwiX9a9qFO2c-wbxpjMG5oCufJttGlUBDfhJLSM04cFhFbzU8Ue7XgNWeNbmEG12Ol00000F__0m00)

## 3. USER/HOST

### 버스킹 참여

![버스킹 참여](https://www.planttext.com/api/plantuml/png/R971IiD048Rl-nJZhNq5Ux0NmPjWzTPBm5I3pHQQUl0ckeUQ1jf2Ooqa4Y5M1G-rhP1YmFV9pE8hE3MLMl2ok_pp_xFVx7vzV9QTbj5idTjriTKwDMqJ3DiwCOHmpYm9pOPZyd7f46AREW7Tzu4Y1NZNGtS0zAJqxGrGtASmgKgSAd9ZVRK0Kf6-3aiS-gD5FixceBlfKgT9GYe4Bcit1Mmh72lMhHeKLgLiSedkuquHc4j2uS9qYWBWDqZgBPkz0_aUZhcXMNTCACZ6XgKJQ-yPA9nJ4AsTyjYgCO0UjK6F1lZ2rHGV0i2kHxxA9hrDZFoYVp66hwlHOJsbxWVyJgMbPM-_SaZ3NZRrGFjpx2pWgB9x07dVgio1E93y15PSl0bHvfM_v1i00F__0m00)

### 버스킹 생성

![버스킹 생성](https://www.planttext.com/api/plantuml/png/L93FIiD04CRl-nJZBNc5zc0lle3Y0mHSRB199Oc8Dqdtu3z8YrQiR4eAXTZY8PM2BGg-JsQ2h-2iX5elo-mtttxpc_t--6g5aHD4Prv7x4HjwKbmFFVO4I8ySNq8kg7aqZY4Q1mUxEq3JdJvlg9qrMG5tnJTJAju3THBIIs0L5RrKcu5ybGwaG1e0EMNc2WWhMZzP6wK3Pkr0SxRRaTQbAlgyHfeEk1WsAJksk26O5t8q1R_fsKQvpEWyO276X2ANpa3y7w1anImI03Z9JrlMVw0w5QpoeQCSlthPQKirCQxZGOMqsBUvxNh7MielsiRfFaBZLNTCI3Lw0ggqGFEbgG_pIDCxcYeogBFNUaV2T7Yax_x1m00__y30000)

### 버스킹 종료

![버스킹 종료](https://www.planttext.com/api/plantuml/png/R971IW9H58RtzobEEtq5NUIc5oZQkHdmQeDw1SSMxMIyWPcWufWPCv4bYE1YB96YWTx7SowzGcVKeiBDuLxk_t__EUT-ldzad9fLhPsNI-AWTYRB4gooNR24S8gsWch5aNoC5KBaAzMY04XH8o2zC0CVQAhDpHNGf4V1QrRXiwRMnBXlGFh1D8BqnW9MmRBLN-EFCgk-6TV-EjI0qpO-jNT6gN92f7vH7o_v4MWunrKTJ2TA2s7d8V5VSPW47aL1ua8woKsmE-SOmFu2nm5Ws05qbtGt0xhlSKTfriISxGrefFT066CyZMFV33GDks2y2FkyWfMt3kinGPOS2GbLsObFJuwEUHh0bmYeUSjHJMnvUsmgP-V5jiP6aJAZ9fYHXxCb-H470dRQDD3hiClpRdQIuSe_zWK00F__0m00)

## 4. ADMIN
### 공지사항 등록

![공지사항 등록](https://www.planttext.com/api/plantuml/png/X9B1IiD054NtynNFNVeBPc45Doxq2zmKJ3NOfD3KXRjIeoWLBPYWBKcPW5fRAWHRjL31_ybxmL_m9KQbZU9ccBbppt3lpBpFtefMlLIhxniLiLJVrGmDIeQ-KnB2sjDDg5KjZQU9GuZbrRMDzKs8nZP95p3mefSf-LELT-BE2SGT1mSJ87-25vr4ZCPFr6zGSnIx3q2sZ0_zBNDEZC-lsSxhFwbd9meR6PpVxHsXl6LPBuD2TwVKyW13Zuw3bG9m6HEK0yqg2805JjhiKOLMiJJ5hAQ6JmsmAQdlGUALhfedPpSOjfWqj_NoOfnVsgJ1KWPmEC301mecr1lzas-UoChdN3zwxDhKTUYg3UY4wBz2TXdVHREuEBp7Imby07K7r6ip4JsUBJpA5vHGGXHvvC_n0G00__y30000)

# 📑 기능 구조도

[WIKI - 기능 구조도](https://github.com/f-lab-edu/show-your-worth/wiki/04.-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%EC%A1%B0%EB%8F%84)를 참고하시면 됩니다.

# 📱 메뉴 구조도

![μ'us - 메뉴 구조도-1](https://github.com/f-lab-edu/show-your-worth/assets/11500877/2318b8d3-6243-48a2-ac63-d3e1184cbcf6)

# 📑 ERD

![μ'us - ERD](https://github.com/f-lab-edu/show-your-worth/assets/11500877/a79ead74-efa0-4eff-9576-9dfc468595ad)

# 🌲 Git Branch Strategy

![](https://github.com/f-lab-edu/show-your-worth/assets/11500877/68605232-e78f-4ff3-88cb-9cbbba9943c0)
