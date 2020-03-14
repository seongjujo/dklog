### 설명
* input.log 파일이 `src/main/resources` 폴더에 들어 있습니다.
* `Application` 클래스의 main 메소드를 실행하면 input.log 파일 읽어 최종결과를 출력합니다.
* 실행 후 output.log 파일이 현재 위치한 목록에 생성이 됩니다.
* jar 실행 방법은 아래 내용 참조.

### jar 실행 방법
* 우선 `mvn clean package` 명령어를 실행합니다. 실행 후 `target` 폴더에 dklog.jar 생성이 됩니다.
* dklog.jar 파일을 원하는 목록에 복사합니다.
* `java -jar dklog.jar` 명령어를 실행합니다. 현재 위치한 목록에 output.log 파일이 생성이 됩니다.
