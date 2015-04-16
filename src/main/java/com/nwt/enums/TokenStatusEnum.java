package com.nwt.enums;

/**
 * Created by Jasmin on 14-Apr-15.
 */
public enum TokenStatusEnum {
    ACTIVE(0), USED(1);

    private int id;

    TokenStatusEnum(int id) {
        this.id = id;
    }

    public static TokenStatusEnum fromId(Integer id) {
        for (TokenStatusEnum tokenStatusEnum : TokenStatusEnum.values()) {
            if (tokenStatusEnum.getId() == id)
                return tokenStatusEnum;
        }
        throw new IllegalArgumentException("No TokenStatusEnum with id=" + id);
    }

    public int getId() {
        return id;
    }
}
