package com.java.framework.settings;

public abstract class GenericEntry {
    private String name = null;

    public void setName(String name) {
        if (this.name == null) this.name = name;
    }

    public String getName() {
        return this.name;
    }
}