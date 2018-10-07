package taotao.common.model;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-06
 * Time: 17:01
 */
public class UploadPicException extends Exception{
    public UploadPicException(String message) {
        super(message);
    }

    public UploadPicException(String message, Throwable cause) {
        super(message, cause);
    }
}
