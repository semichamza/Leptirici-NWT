package com.nwt.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by glasshark on 30-Mar-15.
 */
public class Messages extends ArrayList<Message> {
    public Messages() {
        super();
    }

    public Messages(Collection<? extends Message> c) {
        super(c);
    }

    public List<Message> getMessages() {
        return this;
    }

    public void setMessages(List<Message> messages) {
        this.addAll(messages);
    }
}
