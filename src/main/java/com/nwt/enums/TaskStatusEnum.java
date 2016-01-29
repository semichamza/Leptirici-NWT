package com.nwt.enums;

import java.io.Serializable;

/**
 * Created by glasshark on 04-Apr-15.
 */
public enum TaskStatusEnum implements Serializable
{
    OPEN("OPEN"),
    IN_PROGRESS("IN_PROGRESS"),
    FIXED("FIXED"),
    RESOLVED("RESOLVED"),
    CLOSED("CLOSED");

    String id;

    TaskStatusEnum(String id)
    {
        this.id=id;
    }
    public String getId() {
        return id;
    }

    public static TaskStatusEnum getStatus(String id)
    {
        for(TaskStatusEnum status:values())
        {
            if(status.getId().equals(id))
                return status;
        }

        return null;
    }
}
