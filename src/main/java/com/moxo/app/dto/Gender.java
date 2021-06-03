package com.moxo.app.dto;

public enum Gender {
    MALE("m"),
    FEMALE("f");

    private String genderId;

    Gender(String f) {
        this.genderId = f;
    }

    public String getGenderId() {
        return genderId;
    }
}
