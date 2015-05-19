package com.nwt.entities;

import com.nwt.AppMessages;

import javax.ws.rs.core.Response.Status;

/**
 * Created by Jasmin on 21-Apr-15.
 */
public class ResponseMessages {
    public static final ResponseMessage
            INVALID_LOGIN=new ResponseMessage(Status.UNAUTHORIZED,"1", AppMessages.INVALID_LOGIN);

    public static final ResponseMessage
            INVALID_USERNAME=new ResponseMessage(Status.BAD_REQUEST,"2", AppMessages.INVALID_USERNAME);

    public static final ResponseMessage
            USER_BLOCKED=new ResponseMessage(Status.BAD_REQUEST,"3", AppMessages.USER_BLOCKED);
}
