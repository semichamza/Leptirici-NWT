package com.nwt.enums;

import com.nwt.entities.UserRole;

/**
 * Created by Jasmin on 15-Apr-15.
 */
public enum UserRoleEnum {
    ADMINISTRATOR(0, "Administrator"), NORMAL(1, "Normal");

    private int id;
    private String name;

    UserRoleEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserRole getRole() {
        UserRole role = new UserRole();
        role.setId(id);
        role.setName(name);
        return role;
    }

}
