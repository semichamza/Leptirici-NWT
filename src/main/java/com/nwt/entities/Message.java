package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jasmin on 17-May-15.
 */
@Entity
@Table(name = "messages")
@NamedQueries ({
        @NamedQuery (name = Message.FIND_ALL, query = "SELECT m FROM Message m"),
        @NamedQuery (name = Message.USER_MESSAGES, query = "SELECT m FROM Message m WHERE m.receiver.id=:"+Message.USER_MESSAGE_RECEIVER_PARAM),
        @NamedQuery (name = Message.USER_SENT_MESSAGES, query = "SELECT m FROM Message m WHERE m.sender.id=:"+Message.USER_MESSAGE_SENDER_PARAM),
        @NamedQuery (name = Message.USER_MESSAGES_UNREAD, query = "SELECT m FROM Message m WHERE m.seen is false AND m.receiver.id=:"+Message.USER_MESSAGE_RECEIVER_PARAM)
})

@JsonIdentityInfo (generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Message.class)
public class Message implements Serializable {
    public static final String TABLE_NAME="messages";
    public static final String FIND_ALL="Message.findAll";
    public static final String USER_MESSAGES="Message.userMessages";
    public static final String USER_SENT_MESSAGES="Message.userSentMessages";
    public static final String USER_MESSAGES_UNREAD="Message.userUnreadMessages";
    public static final String USER_MESSAGE_RECEIVER_PARAM="receiverId";
    public static final String USER_MESSAGE_SENDER_PARAM="senderId";
    private Integer id;
    private String text;
    private User sender;
    private User receiver;
    private Boolean seen;
    private Date date;
    public Message() {
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column (nullable = false, columnDefinition = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @NotNull
    @Column (nullable = false)

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    @Temporal (TemporalType.TIMESTAMP)
    @Column (nullable = false)
    @JsonSerialize(using = DateSerializer.class)
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

}

