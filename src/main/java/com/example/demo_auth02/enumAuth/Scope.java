package com.example.demo_auth02.enumAuth;


public enum Scope {
    IMAGE("image"),
    PRODUCT("product"),
    USER("user");

    public final String label;

    private Scope(String label) {
        this.label = label;
    }
}
