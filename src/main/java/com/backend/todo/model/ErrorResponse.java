package com.backend.todo.model;

public class ErrorResponse {
    private String codemessage;
    private String message;
    private int status;

    public ErrorResponse(String codemessage,String message, int status) {
        this.codemessage = codemessage;
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
    public String getCodemessage() {
        return codemessage;
    }
}