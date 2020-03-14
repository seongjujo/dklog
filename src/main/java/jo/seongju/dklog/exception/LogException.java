package jo.seongju.dklog.exception;

/**
 * Created by Seongju Jo. On 2020-03-15 00:47:14
 */
public class LogException extends RuntimeException {

    public LogException(Throwable cause) {
        super(cause);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }
}
