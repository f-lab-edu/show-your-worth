# 목차

1. [프로젝트 개요](#-프로젝트-개요)
2. [프로젝트 기술 스택](#-프로젝트-기술-스택)
3. [Use Case / 유저 시나리오](#-use-case--유저-시나리오)
4. [Flow Chart](#-flow-chart)
5. [Git Branch Strategy](#-git-branch-strategy)

# 📜 프로젝트 개요

> *Show your worth! 당신의 가치를 보여주세요.*

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

1. JDK 17
2. Spring Boot 2.6.15
3. MyBatis 2.3.1
4. JPA 2.6.15
5. MySQL 8.0.33
6. Gradle
7. IntelliJ

# 🎭 Use Case / 유저 시나리오

## 1. COMMON

### USE CASE 1 - 버스킹 조회
1. 애플리케이션이 실행되면 홈 화면이 표시된다
2. 홈 화면에 지도가 표시된다
   - 지도는 현재 위치를 기준으로 표시되어 있다
3. 지도에 사용자 중심으로 개설된 `버스킹`이 표시된다
4. 지도의 특정 버튼을 통해 확장하거나, 이동시키면 표시된 지도를 기준으로 `버스킹`이 표시된다

### USE CASE 2 - 버스킹 검색
1. 검색 탭을 클릭한다
2. 찾고 싶은 모임 제목을 작성하고 확인 버튼을 누른다
3. 온라인 `버스킹`과 오프라인 `버스킹`으로 구분된 리스트가 출력된다
   - `GUEST`는 시작 전 또는 활동 중인 `버스킹`만 검색할 수 있다
   - 그 외 사용자는 과거에 활동했던, 내가 참석했던 그리고 내가 만든 `버스킹`도 검색할 수 있다

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

### USE CASE 1 - 회원 탈퇴
1. `USER`가 로그인한다
2. 마이 페이지 탭을 누르고 회원 탈퇴 버튼을 누른다
3. 탈퇴 신청 시 24시간 이내에 복구할 수 있다는 메시지를 응답한다
4. 확인을 누르면 비밀번호를 입력한다
   - 비밀번호 일치 시 회원 탈퇴 여부 선택 화면이 표시된다
     - 예를 누르면 5번 흐름으로 이동한다
     - 아니오를 누르면 마에 페이지로 이동한다
5. 회원 탈퇴가 완료되었다는 메시지를 응답한다
6. 24시간이 지나면 탈퇴 신청한 유저의 정보를 일괄 삭제한다

### USE CASE 2 - 버스킹 참여

1. 지도에 표시되거나 검색한, 시작 전 또는 진행 중인 버스킹을 선택한다
2. 버스킹에 대한 정보 UI가 표시된다
3. 버스킹 참여 버튼을 누른다
    - 인원이 가득찬 경우, 인원이 가득찼다는 응답을 한다
    - 참석에 성공한 경우, 참석에 성공했다는 응답을 한 후 4번 흐름으로 간다
4. 홈 화면으로 돌아가며 진행 중인 버스킹이 표시된다
5. `HOST`에게 해당 `USER`가 참석했다는 알림을 전송한다

### USE CASE 3 - 버스킹 참여 취소

1. 진행 중인 버스킹 버튼을 누른다
2. 버스킹에 대한 정보 UI가 표시된다
3. 참여 취소 버튼을 누르면, `HOST`에게 알리지 않고 참여를 취소한다는 응답을 한다
   - 취소를 누르면 그대로 유지된다
   - 확인을 누르면 4번 흐름으로 간다
4. 참여 중인 버스킹 정보를 삭제한다

### USE CASE 4 - 버스킹 생성

1. 홈 화면에 있는 + 버튼을 누른다
2. 제목을 적는다
3. 사진을 등록하고 내용을 적는다(Opt.)
4. 시작 날짜 및 시간을 적는다
5. 생성 버튼을 누르면 정말 생성하겠는지 응답한다
   - 취소를 누르면 4번 흐름으로 이동한다
   - 확인을 누르면 6번 흐름으로 이동한다
6. 홈 화면으로 돌아가고 생성한 버스킹이 표시된다

### USE CASE 5 - 버스킹 생성 취소

1. 생성한 버스킹 버튼을 누른다
2. 시작 전인 버스킹에 대한 정보 UI가 표시된다
3. 생성 취소 버튼을 누르면 정말 취소하겠는지 응답한다
   - 취소를 누르면 2번 흐름으로 이동한다
   - 확인을 누르면 4번 흐름으로 이동한다
4. 홈 화면으로 돌아가고 생성한 버스킹 정보를 삭제한다
5. 참여한 `USER`에게 버스킹이 취소되었다고 알림을 전송한다

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

### USE CASE 8 - 같은 버스킹에 참여 중인 USER 조회

1. 진행 또는 참여 중인 버스킹을 선택한다
2. 버스킹에 대한 정보 UI에 참여한 `USER` 목록이 조회된다
3. 보고 싶은 `USER` 프로필을 선택한다
4. `USER`의 정보와 피드를 확인한다

### USE CASE 9 - HOST 검색

1. 검색 탭을 클릭한다
2. `HOST`를 이름으로 검색한다
3. 해당 닉네임을 가진 `HOST`가 조회된다
4. 프로필에 있는 구독 버튼을 누른다
5. `HOST`가 구독된다

### USE CASE 10 - HOST 구독

1. 검색한 `HOST` 또는 참여한 `HOST`의 프로필을 선택한다
2. `HOST`에 대한 정보 UI가 표시된다

## 4. ADMIN

### USE CASE 1 - 유저 목록 조회

1. 검색 탭을 클릭한다
2. 현재 접속 중인 `USER` 순으로 모든 유저 프로필이 표시된다

### USE CASE 2 - 유저 목록 검색

1. 검색 탭을 클릭한다
2. `USER`를 이름으로 검색한다
3. 해당 닉네임을 가진 `USER`가 조회된다

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

## 1. COMMON

### 버스킹 조회

![버스킹 조회](https://www.planttext.com/api/plantuml/png/b99DJy9048Rl-ol66noCqICSvEf5pnekJQpGGDkaBV7Qa4eGJ8J8TnO2IHL9UcWG8qI8_wSx6_-2MsZylCXXDvkTcMVUl3Flhsyfqv8Cgw3coOwLbLKP95N9I8IOEKK3GpTbyGmp24bWSyWRD-p1mwMD_HbUTG2hBczT9qMGTyl0kmqscM6x1ZYssQq3l4wnIfD4qoqP32MJjHB0EmuEF43guAB3xfSGp7rqRQHBDg9H1I6NMIKlnxwWKU0m3ee9iLFPZ1E0nARDkk4QnviKiEXXRu83yUVMiUfjm6ajy2awBgjHO5C7AoulBZxr2TQ5RkH29EpjYZYlBB4lz9UUUNEMrivqNOKZRU-aO2MZN90oagA55MnSnfulYC3f473auw3-x-838GkmL0jyc_LNW4CgG3XT6xDbTtP7sQ0JlCo_rqQ3sDW3xCaEfgk_aFsVAhOdoTev4MSpjMCz_diXq0bjRsDhnegFmkymdwJ4BHRl0m00__y30000)

### 버스킹 검색

![버스킹 검색](https://www.planttext.com/api/plantuml/png/H511Ji9G4Dt_KuoxadW2MCZ62sZSiI7neurG4eeBTmX_8Q2HHe8b-IKrGK4YIO5AC3PwdyxyU0K7JNGpC-_DPDwy-Nh_pBlrOgr-NYcBdVgfh4WeLkoJeX3kcUr0hUfABhSJGcJJPODQRJ2jEPZB69_cEIOf-aXN8L1tJK43S3QbaG8ADSwUWKOUgSNFL5SpO5u1-Ztiz0iEJPGPj8767WKRmAMYpjWqtoY8kUTFCKYOJrS9a3ysVVMBIIVue0jE-lg2QuKzdmOnq5fZ-CWoTWcieyFzWxqCi1S7h0lfPWH0bjUaYuZkUs266c-7Pd25D_uk2qKRKi4Vn6QSHknYSertuVzDO3pD3d92bbq9bbFD2EaSsoMnJKBaEV83lm400F__0m00)

## 2. GUEST

### 회원가입

![회원가입](https://www.planttext.com/api/plantuml/png/UhzxlqDnIM9HIMbk3fTYIKPgRgf2OcvcUY8N5rYOYylgzVAEDtEtABpjcm6atYnlK7ZRl3Ocx-sidZUp9xpQqF1cNgirK9Lxg6jmo9jPAnMG9HGyNVG3mXNUzky1AYbABKXDBE6oUZEr1MZGwobJ580gNYzOQWsLKIZFoCn9rK18lzhKy6RPN4s5p685ZJTDQu26lzxM0JHO4s7MwvqjhpSqlDxKyNR63kp6eQZ0Oo8E3xpkxdpTikJDl1OqJo34iTiAboR3IXG_pTq1r0wrtEhDWfQtCwUWUHVc9g0qM35K-lM6EM_Q5bgZ2wFfWWKwiX9a9qFO2c-wbxpjMG5oCufJttGlUBDfhJLSM04cFhFbzU8Ue7XgNWeNbmEG12Ol00000F__0m00)

## 3. USER/HOST

### 로그인

![로그인](https://www.planttext.com/api/plantuml/png/UhzxlqDnIM9HIMbk3fTYIKPgRgf2OcvcUY8NLt5sPfv2KNvnAf09Ki75PVLwPylhXNDURT_nPkuEXJVDZM-Rvm3vCNblfYvuEwNdzR8rR_Oql9cxvKth38KttKlUJkkq1cbQtW4KKtZRDmCajRn1uMtpsfWyk3b09KMf1Qc99LnMdYxw0U4AR-Qrlbwmr1eggb2UaPcJgl5cpeAtirQyQTcey6fJmvkcDPeAcSKA6Y1BUbPeWYn1E6jco-l5FK1JK_DIkBWSW2JGQm00003__mC0)

### 회원탈퇴

![회원탈퇴](https://www.planttext.com/api/plantuml/png/R931IiD048Rl-nJZBRcAf_RI1_4IS6iNarIo4V5MJSH0WtW914qaWbeF2g5DCUB1zydEu2iuGGKBNdQ6tOz_ljdFzu-H3fqWFF9SiHLEf2V1yTI-8uG-K3u4KosvxGaX1kQkw5vRB5lA2i3ebA9Ys5yl4YmReCkS2ovcGD7Bha_NATvSKPJGHGCOLnIL30VoK3gX01YOjzZKCxDCA6y1RyzDzJ3yUORZYNAbjK5qona-bZOe3TPtZ_DxIfzi5jWSzUkbnc1jx-2ywEguzsCpSvPtRGrOnM1MwsxzR0Flx8Dr8hNTI_sJ19GrM76WTBK4ov_-0R7Ap8etJrEEPSBVKsFH5o56VFAdVW400F__0m00)

### 버스킹 참여

![버스킹 참여](https://www.planttext.com/api/plantuml/png/R971IiD048Rl-nJZhNq5Ux0NmPjWzTPBm5I3pHQQUl0ckeUQ1jf2Ooqa4Y5M1G-rhP1YmFV9pE8hE3MLMl2ok_pp_xFVx7vzV9QTbj5idTjriTKwDMqJ3DiwCOHmpYm9pOPZyd7f46AREW7Tzu4Y1NZNGtS0zAJqxGrGtASmgKgSAd9ZVRK0Kf6-3aiS-gD5FixceBlfKgT9GYe4Bcit1Mmh72lMhHeKLgLiSedkuquHc4j2uS9qYWBWDqZgBPkz0_aUZhcXMNTCACZ6XgKJQ-yPA9nJ4AsTyjYgCO0UjK6F1lZ2rHGV0i2kHxxA9hrDZFoYVp66hwlHOJsbxWVyJgMbPM-_SaZ3NZRrGFjpx2pWgB9x07dVgio1E93y15PSl0bHvfM_v1i00F__0m00)

### 버스킹 참여 취소

![버스킹 참여 취소](https://www.planttext.com/api/plantuml/png/UhzxlqDnIM9HIMbk3fTYIKPgRgf2OcvcUY8NLt5sPfv2KNvnAf09Ki75PVLcUSlRQPqARvPCV3DtXyBhJIrlkfQyRTgfyAPbmTlckTP05KXY2wQ-thn5uMt_7g1uPfg2nfiDEzvCNwFmPlkADsqzK2tscWf0E_CKD2fJYpMv51IiO8emR6XQ-sR176kkrBoKp3Gk4CN5vG0aWSu60000__y30000)

### 버스킹 생성

![버스킹 생성](https://www.planttext.com/api/plantuml/png/J8z13i5G48RtxnHZnnLOS9KcNcXeAst5ljv2a21OL1wfH5B4eiH6IDp7JECAfeZOJ2R_zqtop_DwhtY-uVfjgoboVbrQ4WpBh1b2U0tJ1jVn9A-P8KGHJuhwcpIu04KQzpkWrOJKiSI8WWF52d1wnFKIC1a11cTQ_2ckR4d35m156cXEMOWerc-NaqUYVkv7XKxTR4h8KpJ7U8nh3THTCYyNmFGWRpk5hD9-Iolr9LcHDEn16ivmToPzouvmDAIvUYHZfjAk2b7XoU--0000__y30000)

### 버스킹 생성 취소

![버스킹 생성 취소](https://www.planttext.com/api/plantuml/png/UhzxlqDnIM9HIMbk3fTYIKPgRgf2OcvcUY8N5rYOYylgJVFSDoqRtqwTe_1wKykRhYLlctOgl6bPyBPvhZLG5abimTJNcxSelEtV0nJFJ5FGW6XKUBDznPks7djD1Q3fUGeQbQd5cbmA2jYDXHZnfcdjcmLphC6gDknuCtqDI5Lei6lGc-aJNctgYSb3qW4sNE7Dr9xNo_Oel9suuqqtK6DgNafc6j1nRsTsABoTEUNroYrlvknvlH1eLN_FcwajhpPC03eHg8YBom580hqE0000__y30000)

### 버스킹 종료

![버스킹 종료](https://www.planttext.com/api/plantuml/png/R971IW9H58RtzobEEtq5NUIc5oZQkHdmQeDw1SSMxMIyWPcWufWPCv4bYE1YB96YWTx7SowzGcVKeiBDuLxk_t__EUT-ldzad9fLhPsNI-AWTYRB4gooNR24S8gsWch5aNoC5KBaAzMY04XH8o2zC0CVQAhDpHNGf4V1QrRXiwRMnBXlGFh1D8BqnW9MmRBLN-EFCgk-6TV-EjI0qpO-jNT6gN92f7vH7o_v4MWunrKTJ2TA2s7d8V5VSPW47aL1ua8woKsmE-SOmFu2nm5Ws05qbtGt0xhlSKTfriISxGrefFT066CyZMFV33GDks2y2FkyWfMt3kinGPOS2GbLsObFJuwEUHh0bmYeUSjHJMnvUsmgP-V5jiP6aJAZ9fYHXxCb-H470dRQDD3hiClpRdQIuSe_zWK00F__0m00)

### 같은 버스킹에 참여 중인 USER 조회
![같은 버스킹에 참여 중인 USER 조회](https://www.planttext.com/api/plantuml/png/UhzxlqDnIM9HIMbk3fTYIKPgRgf2OcvcUY8NLt5sPfv2KNvnAf09Ki75PVLcUSlRQPqAhpVqAxpPiEFDz3KARvPCV3DtHqpUwqqjRxgMl6tQgV1cmTJNcxSelEtVOmtIWQnC8JJODIWcx-sKbjSBvxoTsgBmUjNArmldWjI1P1GG4czQ5hnjHaX0pGJP-tRAdjUJvoYydJZdJVSSe8BKl1GkBWSW2VG00000__y30000)

### HOST 검색

![HOST 검색](https://www.planttext.com/api/plantuml/png/UhzxlqDnIM9HIMbk3fTYIKPgRgf2OcvcUY8NLt5sPfv2KNvnAf09Ki75PVLgKyERvaw5jyrh5TwqB7ZRFDSQAEZX7noYy6RkbjVBMXGW2a2YRwTkUTszK-5rT-VhbYLlvhMycGkKtT3mPdcB0aJ3mWrlP_K0LQRcfN1nEG19e0i00000__y30000)

### HOST 구독

![HOST 구독](https://www.planttext.com/api/plantuml/png/UhzxlqDnIM9HIMbk3fTYIKPgRgf2OcvcUY8NLt5sPfv2KNvnAf09Ki75PVLgKyERvaw5jyrh5TwqB7ZRFDSQAEZX7noYy6RkbjVBMXGW2a2YRwTkUTszK-5rT-VhbYLlvhMycGkKtT3mPdcB0aJ3mWrlP_K0LQRcfN1nEG19e0i00000__y30000)

### 유저 목록 조회 / 검색

![유저 목록 조회](https://www.planttext.com/api/plantuml/png/UhzxlqDnIM9HIMbk3fTYIKPgRgf2OcvcUY8NLt5sPfv2KNvnAf09Ki75PUNeuklff_1gSykR1LCLNY-SyshxZZTpTrW3PLvjQdZJtAdmjdcjmfkM1M-Rvu84tywSyxf_fiAR9HE1gXH2Wrs355vlw5Vmy0yEKNYpSCFRMJqWLM_cRdczh4K1OW9OszGjhxjtAhpkxdpTikJDl9OtSu6o6nhUB6_1PKPgNWeNbmEG1Fe20000__y30000)

### 공지사항 등록

![공지사항 등록](https://www.planttext.com/api/plantuml/png/X9B1IiD054NtynNFNVeBPc45Doxq2zmKJ3NOfD3KXRjIeoWLBPYWBKcPW5fRAWHRjL31_ybxmL_m9KQbZU9ccBbppt3lpBpFtefMlLIhxniLiLJVrGmDIeQ-KnB2sjDDg5KjZQU9GuZbrRMDzKs8nZP95p3mefSf-LELT-BE2SGT1mSJ87-25vr4ZCPFr6zGSnIx3q2sZ0_zBNDEZC-lsSxhFwbd9meR6PpVxHsXl6LPBuD2TwVKyW13Zuw3bG9m6HEK0yqg2805JjhiKOLMiJJ5hAQ6JmsmAQdlGUALhfedPpSOjfWqj_NoOfnVsgJ1KWPmEC301mecr1lzas-UoChdN3zwxDhKTUYg3UY4wBz2TXdVHREuEBp7Imby07K7r6ip4JsUBJpA5vHGGXHvvC_n0G00__y30000)

# 🌲 Git Branch Strategy

![](https://github.com/f-lab-edu/show-your-worth/assets/11500877/68605232-e78f-4ff3-88cb-9cbbba9943c0)
