package com.nwt.auth.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by glasshark on 28-Mar-15.
 */
public class VerificationTokens extends ArrayList<VerificationToken> {
    public VerificationTokens() {
        super();
    }

    public VerificationTokens(Collection<? extends VerificationToken> c) {
        super(c);
    }

    public List<VerificationToken> getTokens() {
        return this;
    }

    public void setTokens(List<VerificationToken> tokens) {
        this.addAll(tokens);
    }
}
