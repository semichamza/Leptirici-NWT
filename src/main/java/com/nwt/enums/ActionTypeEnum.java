package com.nwt.enums;

/**
 * Created by Jasmin on 14-Apr-15.
 */
public enum ActionTypeEnum {
    ACTIVATION(0),
    PASSWORD_RECOVERY(1);

    private int id;

    ActionTypeEnum(int id) {
        this.id = id;
    }

    public static ActionTypeEnum fromId(Integer id) {
        for (ActionTypeEnum actionTypeEnum : ActionTypeEnum.values()) {
            if (actionTypeEnum.getId() == id)
                return actionTypeEnum;
        }
        throw new IllegalArgumentException("No ActionTypeEnum with id=" + id);
    }

    public int getId() {
        return id;
    }
}
