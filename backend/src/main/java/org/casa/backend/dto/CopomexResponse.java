package org.casa.backend.dto;

public class CopomexResponse {

    private boolean error;
    private int code_error;
    private String error_message;
    private CopomexData response;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode_error() {
        return code_error;
    }

    public void setCode_error(int code_error) {
        this.code_error = code_error;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public CopomexData getResponse() {
        return response;
    }

    public void setResponse(CopomexData response) {
        this.response = response;
    }
}
