# OpenBankingProject
팀원간 계좌 연동이 가능한 오픈뱅킹 시스템입니다.<br>
연동된 서버에서 같은 이메일의 유저 계좌를 하나의 웹서버에서 보고 관리할 수 있습니다.

## 핵심기능
- 송금과정의 트랜젝션을 지원합니다.
- 카카오, 네이버 API를 활용한 로그인과 뉴스 출력을 지원합니다.
- 금액을 원화단위 (1234억 567만 89 원)로 보조 표기하는 기능을 구현했습니다.

## 프로젝트 활성화 방법
1. 데이터베이스 테이블 생성
   - webapp/WEB-INF/sqlTable 참조
    
2. util/Secrets.java 작성
   - 해당 파일은 vo 형태의 파일이며 다음과 같은 요소가 필요합니다.
      - String kakaoKey
      - String NaverId
      - String NaverSecrets
   - 위 변수들을 선언한 후 자신의 kakao, naver API key 값을 할당해야 합니다.

3. 팀원간 연동
   - 특정 조원들간의 연결을 상정한 프로젝트기에 연동 없을경우의 오류를 방지하기 위해 비활성화 시켜두었습니다.
   - 해당 기능을 활성화 시키기 위해선 controller/pages/ToIndexMainController.java 파일의 수정이 필요합니다.
  
## 기타
보안 문제로 레파지토리를 변경했습니다...

![500](https://oqooo.github.io/LimKiHong-Portfolio/assets/img/loginAndTransfer2.gif)
