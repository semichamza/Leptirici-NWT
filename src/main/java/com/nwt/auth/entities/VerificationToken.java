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
@Table(name = "VerificationToken")
@NamedQueries({
        @NamedQuery(name = VerificationToken.FIND_ALL, query = "SELECT v FROM VerificationToken v")
})
@EntityListeners(LifeCycleListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VerificationToken {

    public static final String FIND_ALL = "VerificationToken.findAll";

    String id;
    User user;
    Integer actiontTypeId;
    Integer tokenStatusId;

    public VerificationToken() {

    }

    public VerificationToken(String id, User user, Integer actiontTypeId, Integer tokenStatusId) {
        this.id = id;
        this.user = user;
        this.actiontTypeId = actiontTypeId;
        this.tokenStatusId = tokenStatusId;
    }

    public static VerificationToken generateToken() {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setId(UUID.randomUUID().toString());
        return verificationToken;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    public Integer getActiontTypeId() {
        return actiontTypeId;
    }

    public void setActiontTypeId(Integer actiontTypeId) {
        this.actiontTypeId = actiontTypeId;
    }

    @Column(nullable = false)
    public Integer getTokenStatusId() {
        return tokenStatusId;
    }

    public void setTokenStatusId(Integer tokenStatusId) {
        this.tokenStatusId = tokenStatusId;
    }

    @Override
    public String toString() {
        return "Token:" + id;
    }

    public TokenStatusEnum getTokenStatus() {
        return TokenStatusEnum.fromId(tokenStatusId);
    }

    public void setTokenStatus(TokenStatusEnum status) {
        this.tokenStatusId = status.getId();
    }

    public ActionTypeEnum getActionType() {
        return ActionTypeEnum.fromId(actiontTypeId);
    }

    public void setActionType(ActionTypeEnum type) {
        this.actiontTypeId = type.getId();
    }


}

