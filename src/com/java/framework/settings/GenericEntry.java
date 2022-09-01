package com.java.framework.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class GenericEntry<entryType> {
    private String name = null;

    public void setName(String name) {
        if (this.name == null) this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addToXml(Document sourceDocument, Element sourceElement) {};

    public void setValue(entryType newValue) {};
    public entryType getValue() {
        return null;
    }
}