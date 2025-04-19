package com.tawson.i18n.enums;

import lombok.Getter;

@Getter
public enum I18nEnum {
    A00001("A00001", ""),
    ;
    private final String code;
    private final String defaultMessage;

    I18nEnum(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
