package com.nwt.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by glasshark on 01-Apr-15.
 */
public class Logs extends ArrayList<Log>
{
    public Logs()
    {
        super();
    }

    public Logs(Collection<? extends Log> c)
    {
        super(c);
    }

    public List<Log> getLogs()
    {
        return this;
    }

    public void setLogs(List<Log> logs)
    {
        this.addAll(logs);
    }
}