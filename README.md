# spring4-sample
spring4 + hibernate + swagger를 이용한 API 시스템 구축 예 입니다.

## 개요
spring boot에서 가려진 각종 설정들을 보다 가시적으로 보기 위해서 만들었습니다.

해당 소스에는 스프링4를 기반으로 JPA를 이용한 API 서버를 개발할 때 사용하는 기본 설정들이 되어있다.

### Framework에 추가된 설정들
* MVC 중 Exception 관련된 Handler 설정 (exception package 안에 있는 각종 Handler들)
* Servlet Filter를 이용한 Request, Response 로깅
* 404 Not found 시 Spring에서 이를 처리는 설정
* Swagger를 이용한 API Doc 생성
* Spring Jpa를 이용한 Hbernate entityManager, transaction 설정
* ApiRes 객체를 이용해서 객체를 바로 Json으로 return 하기 위한 설정
* Exception을 Human, Process, System으로 나누어 에러코드를 로깅 할 수 있도록 Exception 개발
* Gradle Build 시 profile을 선택해서 Build 할 수 있도록 설정
* HikariCp를 이용한 DataSource 설정


### 테스트를 위한 정보
* http://localhost:8080/swagger-ui.html 을 이용
* SampleController를 이용해서 테스트 할 수 있도록 코드 추가
* 기본적으로 H2DB를 인메모리로 돌려서 테스트 하게끔 되어있음.

### 설정관련 안내
* 기본 설정값과 같아도 코드구현이 되어있는 부분은 일반적으로 해당 옵션들을 사용하고 있다고 명시하기 위함입니다.
* Spring이 계속 버전업이 되고 있기 때문에 더 쉬운 방법으로 설정 가능합니다.
* 

test
