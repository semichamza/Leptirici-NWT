package com.nwt.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by glasshark on 30-Mar-15.
 */
public class Tasks extends ArrayList<Task> {
    public Tasks() {
        super();
    }

    public Tasks(Collection<? extends Task> c) {
        super(c);
    }

    public List<Task> getTasks() {
        return this;
    }

    public void setTasks(List<Task> tasks) {
        this.addAll(tasks);
    }
}
