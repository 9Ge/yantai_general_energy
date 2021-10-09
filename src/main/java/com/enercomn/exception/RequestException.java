package com.enercomn.exception;

/**
 * @Date: 2021/10/9 11:15<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public class RequestException extends RuntimeException {
    public RequestException() {
        super();
    }

    public RequestException(String message) {
        super(message);
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestException(Throwable cause) {
        super(cause);
    }
}
