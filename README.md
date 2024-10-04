# product

## 구현 범위

1. **API 기능 구현**  
   - 고객에게 제공될 상품 API 제공
   - 운영자에게 브랜드, 카테고리, 상품의 가격을 관리 할수 있는 API 제공
   - 
2. **데이터 관리 기능**  
   - 데이터베이스에서 브랜드, 카테고리, 상품 가격 등의 데이터 관리 및 조회
   - JPA를 활용한 데이터 관리

3. **테스트 및 검증**  
   - 테스트를 통해 주요 기능 검증
   
---

## 코드 빌드, 테스트 실행 방법 및 API 문서

### 1. 빌드 방법
이 프로젝트는 [빌드 도구]를 사용하여 빌드할 수 있습니다.

- **Gradle**
```shell
  ./gradlew build
```
### 2. 테스트 실행 방법
local profile 을 사용하여 실행한다.
```shell
 $ ./gradlew api:bootRun --args='--spring.profiles.active=local'
```
### 3. API 테스트 방법
http://localhost:18080/swagger-ui/index.html#/
### 4. DB Data 확인방법
http://localhost:18080/h2-console


## 환경 정보 
- Java Version: 17
- Spring Boot Version: 3.3.0
- 데이터베이스: H2 (로컬 테스트)
