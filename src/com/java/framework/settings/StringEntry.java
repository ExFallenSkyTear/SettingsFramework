package com.java.framework.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringEntry extends GenericEntry<String> implements EntryInterface<String> {
    private String value;
    private Pattern validationPattern;

    public void setValue(String newValue) {
        if (isNewValueValid(newValue)) this.value = newValue;
    }

    public String getValue() {
        return this.value;
    }

    public void setValidationPattern(String newValidationPattern) {
        this.validationPattern = Pattern.compile(newValidationPattern);
    }

    public boolean isNewValueValid(String newValue) {
        Matcher matcher = this.validationPattern.matcher(newValue);
        return matcher.find();
    }

    public void addToXml(Document sourceDocument, Element sourceElement) {
        Element localNode = sourceDocument.createElement(super.getName());
        sourceElement.appendChild(localNode);
        localNode.appendChild(sourceDocument.createTextNode(this.getValue()));
    }

    public String parse(String value) {
        return value;
    }
}