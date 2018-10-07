package taotao.common.model;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-06
 * Time: 16:22
 */
public class UploadPicResult {
    private int error;
    private String url;
    private String message;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UploadPicResult{" +
                "error=" + error +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
