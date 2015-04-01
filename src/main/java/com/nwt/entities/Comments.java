package com.nwt.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by glasshark on 01-Apr-15.
 */
public class Comments extends ArrayList<Comment>
{
    public Comments()
    {
        super();
    }

    public Comments(Collection<? extends Comment> c)
    {
        super(c);
    }

    public List<Comment> getComments()
    {
        return this;
    }

    public void setComments(List<Comment> comments)
    {
        this.addAll(comments);
    }
}