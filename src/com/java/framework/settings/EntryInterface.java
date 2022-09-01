package com.java.framework.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface EntryInterface<EntryType> {
    void setValue(EntryType newValue);
    EntryType getValue();

    public void addToXml(Document sourceDocument, Element sourceElement);
}