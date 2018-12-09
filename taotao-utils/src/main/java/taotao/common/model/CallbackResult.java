package taotao.common.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-07
 * Time: 17:45
 */
public class CallbackResult implements Serializable {
    private static final long serialVersionUID = 6627248123435606494L;
    private int status;
    private String message;
    private Object data;

    public CallbackResult(){}

    public CallbackResult(int status, String message) {
        this(status, message, null);
    }

    public CallbackResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
