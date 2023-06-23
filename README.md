# OECD보건통계 입력시스템 FO서비스

#### 작성자: (주)아이브로
#### 사업관리자: 박지연 부장

## 환경구성

- Java 8
- Spring 5.1.9
- DB: Oracle Database 12c Enterprise Edition Release 12.2.0.1.0 - 64bit Production

- WAS & WEB: Tomcat 9.0.74
- Maven 외 lib 폴더도 참조
---

## 유저 가이드

### 개발 URL
#### WEB

```
  http://221.158.185.26:8001/oecdstat
     
```


#### DB
````
- 개발서버 도커 컨테이너에 구축

- jdbc:oracle:thin:@221.158.185.26:1522:ORCL
- OECDSTAT/OECDSTAT
  
````

#### VCS - git
- https://github.com/kangbyounghoon/oecdstat.git
- 초대 후 다운로드 가능.

### git branch 구성
```bash
Local
- main 
- develop/oecdstat, feature/oecdstat

Remote
- origin/main
- origin/develop/oecdstat
```

### git branch 작업 절차
```bash
'main' - 해당 프로젝트는 사용하지 않는다.
'bcstat' - 방송통계 운영서버 배포 영역
'paciis' - 지능정보사회 운영서 배포 영역
'develop/bcstat' - 방송통계 개발서버배포 영역 
'develop/paciis' - 지능정보사회 개발서버배포 영역 
'feature/xxx' - 로컬환경에서의 기능개발을 위한 브랜치로 유저의 작업공간이며 각 유저가 작업 전에 feature/xxx 브랜치영역을 생성하여 작업공간을 확보하여 진행한다. 
*** 중요한것은 develop 브랜치로부터 분기하여 진행해야 한다.

- feature 브랜치 프로세스
  1. 'develop' 브랜치에서 새로운 기능에 대한 feature 브랜치를 분기한다.
  2. 새로운 기능에 대한 작업 수행한다.
  3. 작업이 끝나면 'develop' 브랜치로 병합(merge)한다.
  4. 더 이상 필요하지 않은 'feature' 브랜치는 삭제한다.
  5. 새로운 기능에 대한 'feature' 브랜치를 중앙 원격 저장소에 올린다.(push)
```
### 브랜치 설정정보 파일
```bash
src/main/resources/properties/file.properties
src/main/resources/config/spring/context-datasource.xml

src/main/java/nurim/cms/common/bean/CommonBean.java
지능정보사회
 	private String tblUser = "";
	private String tblOrgId = "";
방송통계
    private String tblUser = "NSI_IN_005";
  	private String tblOrgId = "005";	

*** 브랜치 병합(merge)시 반드시 제외

제외 명령어
git merge --no-commit --no-ff feature/xxx -X theirs
git reset HEAD src/main/resources/properties/file.properties src/main/resources/config/spring/context-datasource.xml src/main/java/nurim/cms/common/bean/CommonBean.java
git restore src/main/resources/properties/file.properties src/main/resources/config/spring/context-datasource.xml src/main/java/nurim/cms/common/bean/CommonBean.java
git clean -fd
git commit

```

### feature 브랜치 생성 및 종료 과정

```bash
// feature 브랜치(feature/xxx)를 'develop' 브랜치에서 분기
$ git checkout -b feature/xxx develop

/* ~ 새로운 기능에 대한 작업 수행 ~ */

/* feature 브랜치에서 모든 작업이 끝나면 */
.
.
.

'develop' 브랜치로 이동한다.
$ git checkout develop
// 'develop' 브랜치에 feature/xxx 브랜치 내용을 병합(merge)한다.
# --no-ff 옵션: 아래에 추가 설명
$ git merge --no-ff feature/xxx
// -d 옵션: feature/xxx에 해당하는 브랜치를 삭제한다.
$ git branch -d feature/xxx
// 'develop' 브랜치를 원격 중앙 저장소에 올린다.
$ git push origin develop

--no-ff 옵션
새로운 커밋 객체를 만들어 'develop' 브랜치에 merge 한다.
이것은 'feature' 브랜치에 존재하는 커밋 이력을 모두 합쳐서 하나의 새로운 커밋 객체를 만들어 'develop' 브랜치로 병합(merge)하는 것이다.
```

### release 브랜치 생성 및 종료 과정
```bash
// release 브랜치(release-1.2)를 'develop'에서 분기
$ git checkout -b release-1.2 develop

/* ~ 배포 사이클이 시작 ~ */

/* release 브랜치에서 배포 가능한 상태가 되면 */
// 'main' 브랜치로 이동한다.
$ git checkout main
// 'main' 브랜치에 release-1.2 브랜치 내용을 병합(merge)한다.

# --no-ff 옵션: 위의 추가 설명 참고
$ git merge --no-ff release-1.2

// 병합한 커밋에 Release 버전 태그를 부여한다.
$ git tag -a 1.2

/* 'release' 브랜치의 변경 사항을 'develop' 브랜치에도 적용 */
// 'develop' 브랜치로 이동한다.
$ git checkout develop
// 'develop' 브랜치에 release-1.2 브랜치 내용을 병합(merge)한다.
$ git merge --no-ff release-1.2
// -d 옵션: release-1.2에 해당하는 브랜치를 삭제한다.
$ git branch -d release-1.2
```
