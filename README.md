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

# 🎭 Use Case / 유저 시나리오

## 1. COMMON

### USE CASE 1 - 모임 조회
1. 홈 화면에 표시된 지도에 개설된 모임이 표시된다
2. 지도를 확장하거나 이동시키면 표시된 지도를 기준으로 모임이 표시된다

### USE CASE 2 - 모임 검색
1. 홈 화면의 검색 창을 선택한다
2. 검색 탭이 활성화되면서 찾고 싶은 모임 제목을 작성하고 확인 버튼을 누른다
3. 온라인 `버스킹`과 오프라인 `버스킹`으로 구분된 리스트가 출력된다
   - `GUEST`는 시작 전 또는 활동 중인 `버스킹`만 검색할 수 있다
   - 그 외 사용자는 과거에 활동했던, 내가 참석했던 그리고 내가 만든 `버스킹`도 검색할 수 있다

## 2. GUEST

### USE CASE 1 - 회원가입

1. 마이 페이지 탭을 누른다
2. 회원가입 버튼을 누른다
3. 아이디, 비밀번호 그리고 비밀번호 확인 란을 입력하고 다음을 누른다
   - 존재하는 아이디가 있으면 중복 아이디 존재 메시지를 응답한다
   - 존재하는 아이디가 없으면 사용 가능 메시지를 응답한다
4. 닉네임 란을 입력하고 다음을 누른다
   - 존재하는 닉네임이 있으면 중복 아이디 존재 메시지를 응답한다
   - 존재하는 닉네임이 없으면 사용 가능 메시지를 응답한다
5. 전화번호를 입력하고 인증 번호 발송 버튼을 누른다
6. 3분 내에 수신한 인증번호를 입력하고 인증하기 버튼을 누른다
7. 회원가입이 성공적으로 마치면 자동으로 로그인되어 홈 화면으로 돌아간다

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

1. `HOST` 검색 탭을 클릭한다
2. `HOST`를 이름으로 검색한다
3. 해당 닉네임을 가진 `HOST`가 조회된다

### USE CASE 10 - HOST 구독

1. 검색한 `HOST` 또는 참여한 `HOST`의 프로필을 선택한다
2. `HOST`에 대한 정보 UI가 표시된다

## 4. ADMIN

### USE CASE 1 - 유저 목록 조회
### USE CASE 2 - 유저 목록 검색
### USE CASE 3 - 공지사항 등록

