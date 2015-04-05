package com.nwt.util;

/**
 * Created by Jasmin on 04-Apr-15.
 */

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.nwt.auth_entities.Token;
import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.List;


public class AuthHelper {

    private static final String AUDIENCE = "Leptirici";

    private static final String ISSUER = "NWTApplication";

    private static final String SIGNING_KEY = "Almin_Jasmin_Hamza_Alem@^($%*$%";

    public static String createJsonWebToken(String username, String passwordHash, Long durationMinutes) {
        //Current time and signing algorithm
        Calendar cal = Calendar.getInstance();
        HmacSHA256Signer signer;
        try {
            signer = new HmacSHA256Signer(ISSUER, null, SIGNING_KEY.getBytes());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        JsonToken token = new JsonToken(signer);
        token.setAudience(AUDIENCE);
        token.setIssuedAt(new org.joda.time.Instant(cal.getTimeInMillis()));
        token.setExpiration(new org.joda.time.Instant(cal.getTimeInMillis() + 1000L * 60L * durationMinutes));

        JsonObject request = new JsonObject();
        request.addProperty("userId", username);
        request.addProperty("passwordHash", passwordHash);
        JsonObject payload = token.getPayloadAsJsonObject();
        payload.add("info", request);

        try {
            return token.serializeAndSign();
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    public static Token verifyToken(String token) {
        try {
            final Verifier hmacVerifier = new HmacSHA256Verifier(SIGNING_KEY.getBytes());

            VerifierProvider hmacLocator = new VerifierProvider() {

                @Override
                public List<Verifier> findVerifier(String id, String key) {
                    return Lists.newArrayList(hmacVerifier);
                }
            };
            VerifierProviders locators = new VerifierProviders();
            locators.setVerifierProvider(SignatureAlgorithm.HS256, hmacLocator);
            net.oauth.jsontoken.Checker checker = new net.oauth.jsontoken.Checker() {

                @Override
                public void check(JsonObject payload) throws SignatureException {
                }

            };
            JsonTokenParser parser = new JsonTokenParser(locators,
                    checker);
            JsonToken jt;
            try {
                jt = parser.verifyAndDeserialize(token);
            } catch (SignatureException e) {
                throw new RuntimeException(e);
            }
            JsonObject payload = jt.getPayloadAsJsonObject();
            Token t = new Token();
            String issuer = payload.getAsJsonPrimitive("iss").getAsString();
            String userIdString = payload.getAsJsonObject("info").getAsJsonPrimitive("userId").getAsString();
            String passHash = payload.getAsJsonObject("info").getAsJsonPrimitive("passwordHash").getAsString();
            if (issuer.equals(ISSUER) && !StringUtils.isBlank(userIdString)) {
                t.setUsername(userIdString);
                t.setPasswordHash(passHash);
                t.setIssued(new DateTime(payload.getAsJsonPrimitive("iat").getAsLong() * 1000L));
                t.setExpires(new DateTime(payload.getAsJsonPrimitive("exp").getAsLong() * 1000L));
                return t;
            } else {
                return null;
            }
        } catch (InvalidKeyException e1) {
            throw new RuntimeException(e1);
        }
    }

}

