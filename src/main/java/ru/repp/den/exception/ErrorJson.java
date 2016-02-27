package ru.repp.den.exception;

import java.util.Map;

/**
 * this class used by Exception handler to show exception details in a comprehensive format.
 */
public class ErrorJson {

    public Integer status;
    public String error;
    public String message;
    public String timeStamp;

    public ErrorJson(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.error = (String) errorAttributes.get("error");
        this.message = (String) errorAttributes.get("message");
        this.timeStamp = errorAttributes.get("timestamp").toString();
    }

}