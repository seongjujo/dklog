package jo.seongju.dklog;

import jo.seongju.dklog.exception.ReadLogException;
import jo.seongju.dklog.exception.WriteLogException;
import jo.seongju.dklog.service.LogService;
import jo.seongju.dklog.service.ServiceFactory;

import java.io.*;

/**
 * Created by Seongju Jo. On 2020-03-15 02:58:37
 */
public class Application {

    public static void main(String[] args) {

        LogService logService = ServiceFactory.getLogService();

        try {

            InputStream inputStream = getInputStream();

            logService.aggregate(inputStream);

        } catch (FileNotFoundException e) {
            System.err.println("### 찾을 수 없는 log 파일경로입니다.");

        } catch (IOException e) {
            System.err.println("### log 파일 처리 실패.");

        } catch (ReadLogException e) {
            System.err.println("### log 파일 읽기 실패.");

        } catch (WriteLogException e) {
            System.err.println("### log 파일 출력 실패.");

        } catch (Exception e) {
            System.err.println("### 알 수 없는 시스템 장애입니다.");

        } finally {
            System.out.println("### 실행 완료.");
            System.exit(0);
        }
    }

    private static InputStream getInputStream() {

        return Application.class.getResourceAsStream(File.separator + "input.log");
    }
}
