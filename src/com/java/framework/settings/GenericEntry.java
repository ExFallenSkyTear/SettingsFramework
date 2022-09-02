package com.java.framework.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class GenericEntry<entryType> {
    private String name;

    private boolean ignoreExport;

    public void setName(String name) {
        if (this.name == null) this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isIgnoringExport() {
        return this.ignoreExport;
    }

    public void enableExport() {
        this.ignoreExport = true;
    }

    public void disableExport() {
        this.ignoreExport = false;
    }

    public void addToXml(Document sourceDocument, Element sourceElement) {}

    public void setValue(entryType newValue) {}

    public entryType getValue() {
        return null;
    }

    public entryType parse(String value) { return null; }
}