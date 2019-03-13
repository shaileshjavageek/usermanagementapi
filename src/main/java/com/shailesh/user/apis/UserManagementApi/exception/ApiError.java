package com.shailesh.user.apis.UserManagementApi.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ApiError implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
    private List<String> errors;
    private Date date;

    public ApiError(HttpStatus status, String message, String error, Date date) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
        this.date = date;
    }

	public ApiError(HttpStatus status, String message, List<String> errors, Date date) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
		this.date = date;
	}
    
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
    
}