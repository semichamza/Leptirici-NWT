package com.nwt.auth.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nwt.entities.User;
import com.nwt.enums.ActionTypeEnum;
import com.nwt.enums.TokenStatusEnum;
import com.nwt.util.LifeCycleListener;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Jasmin on 14-Apr-15.
 */
@Entity
@Table (name = "VerificationToken")
@NamedQueries ({
        @NamedQuery (name = VerificationToken.FIND_ALL, query = "SELECT v FROM VerificationToken v")
})
@EntityListeners (LifeCycleListener.class)
@JsonIdentityInfo (generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VerificationToken
{

    public static final String FIND_ALL = "VerificationToken.findAll";

    String id;
    User user;
    ActionTypeEnum actionType;
    TokenStatusEnum tokenStatus;

    public VerificationToken()
    {
    }

    public VerificationToken(String id, User user, ActionTypeEnum actionType, TokenStatusEnum tokenStatus)
    {
        this.id = id;
        this.user = user;
        this.actionType = actionType;
        this.tokenStatus = tokenStatus;
    }

    public static VerificationToken generateToken()
    {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setId(UUID.randomUUID().toString());
        return verificationToken;
    }

    @Id
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @OneToOne (cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Enumerated (EnumType.STRING)
    public TokenStatusEnum getTokenStatus()
    {
        return tokenStatus;
    }

    public void setTokenStatus(TokenStatusEnum tokenStatus)
    {
        this.tokenStatus = tokenStatus;
    }

    @Enumerated (EnumType.STRING)
    public ActionTypeEnum getActionType()
    {
        return actionType;
    }

    public void setActionType(ActionTypeEnum actionType)
    {
        this.actionType = actionType;
    }

    @Override
    public String toString()
    {
        return "Token:" + id;
    }
}

