# OpenBankingProject

팀원간 계좌 연동이 가능한 오픈뱅킹입니다.

카카오 로그인 기능을 지원합니다




## 프로젝트 활성화
1. 데이터베이스 테이블 생성
   - src/main/webapp/WEB-INF/sqlTable 참조
    
2. src/main/java/kr/ac/kopo/util/Secrets.java 작성
   - 해당 파일은 vo 형태의 파일이며 다음과 같은 요소가 필요합니다.
      - String kakaoKey
      - String NaverId
      - String NaverSecrets
   - 위 변수들을 선언한 후 자신의 kakao, naver API key 값으로 초기화 해야합니다.

3. 팀원간 연동
   - 특정 조원들간의 연결을 상정한 프로젝트기에 임시적으로 비활성화 되어있습니다.
