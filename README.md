# ES9 Log Generator (Spring Boot + Logback → Elasticsearch Bulk)

간단한 Spring Boot 애플리케이션이 주기적으로 ERROR 로그를 발생시키고,
Logback Elasticsearch Appender를 통해 **Elasticsearch 9**로 전송합니다.

## 요구사항
- JDK 17+
- Maven 3.9+
- (선택) 로컬 ES9 (`ELASTIC_URL` 기본값은 `http://localhost:9200/_bulk`)

## 실행
```bash
# 환경 변수 (예시)
set ELASTIC_URL=http://localhost:9200/_bulk
set APP_NAME=demo-es9
set SPRING_PROFILE=local

# 실행
mvn -q spring-boot:run
```

또는 Windows PowerShell:
```powershell
$env:ELASTIC_URL="http://localhost:9200/_bulk"
$env:APP_NAME="demo-es9"
$env:SPRING_PROFILE="local"
mvn -q spring-boot:run
```

## 인덱스 패턴
- `application-log-YYYYMMDD`

## 필드(로그백 설정)
- `springApplicationName`, `springProfile`, `level`, `thread`, `logger`, `stacktrace`

## 동작
앱 시작 후 5초마다 의도적으로 NPE를 발생시켜 `ERROR` 로그를 전송합니다.

## 참고
- 실제 ES 인증/보안 환경이면 리버스 프록시/인증 헤더 또는 VPC 내 엔드포인트를 사용하세요.
- `logback-spring.xml`의 URL, 인덱스, 필드를 필요에 맞게 조정 가능합니다.
