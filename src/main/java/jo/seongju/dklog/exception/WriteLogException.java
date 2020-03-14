package jo.seongju.dklog.exception;

/**
 * Created by Seongju Jo. On 2020-03-15 02:02:20
 */
public class WriteLogException extends LogException {

    public WriteLogException(Throwable cause) {
        super(cause);
    }

    public WriteLogException(String message, Throwable cause) {
        super(message, cause);
    }
}
