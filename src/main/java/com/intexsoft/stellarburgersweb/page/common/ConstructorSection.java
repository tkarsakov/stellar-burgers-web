package com.intexsoft.stellarburgersweb.page.common;

public enum ConstructorSection {
    BUN("Булки"),
    SAUCE("Соусы"),
    FILLING("Начинки");

    private final String desc;

    ConstructorSection(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
