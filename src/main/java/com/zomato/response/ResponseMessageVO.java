package com.zomato.response;

import java.util.List;

public class ResponseMessageVO {
	private String message;    
    private boolean success;
    private String code;
    private List<String> details;
    
    public ResponseMessageVO() {
        
    }
    
    public ResponseMessageVO(String message, List<String> details, String code) {
        super();
        this.message = message;
        this.details = details;
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }     

}
