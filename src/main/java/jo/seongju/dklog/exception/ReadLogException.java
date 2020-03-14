package jo.seongju.dklog.exception;

/**
 * Created by Seongju Jo. On 2020-03-15 00:48:08
 */
public class ReadLogException extends LogException {

    public ReadLogException(Throwable cause) {
        super(cause);
    }

    public ReadLogException(String message, Throwable cause) {
        super(message, cause);
    }
}
