package com.java.framework.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BooleanEntry extends GenericEntry<Boolean> implements EntryInterface<Boolean> {
    private boolean value;

    public void setValue(Boolean newValue) {
        this.value = newValue;
    }

    public Boolean getValue() {
        return this.value;
    }

    public void addToXml(Document sourceDocument, Element sourceElement) {
        Element localNode = sourceDocument.createElement(super.getName());
        sourceElement.appendChild(localNode);
        localNode.appendChild(sourceDocument.createTextNode(String.valueOf(this.getValue())));
    }
}