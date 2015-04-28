package com.nwt.entities;

/**
 * Created by Jasmin on 21-Apr-15.
 */
import javax.ws.rs.core.Response;
public class ResponseMessage {
    private String code;
    private String message;
    private int status;
    public ResponseMessage()
    {

    }
    public ResponseMessage(int status,String code,String message)
    {
        this.status=status;
        this.code=code;
        this.message=message;
    }
    public ResponseMessage(Response.Status status,String code,String message)
    {
        this.status=status.getStatusCode();
        this.code=code;
        this.message=message;
    }
    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response getResponse()
    {
        return Response.status(status).entity(this).build();
    }
}
