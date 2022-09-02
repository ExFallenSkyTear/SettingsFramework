package com.java.framework.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class IntegerEntry extends GenericEntry<Integer> implements EntryInterface<Integer> {
    private int value;

    private int lowerBound;

    private int upperBound;

    public void setValue(Integer newValue) {
        if (isNewValueValid(newValue)) this.value = newValue;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setLowerBound(int newValue) {
        if (isNewLBoundValid(newValue)) this.lowerBound = newValue;
        if (isValueLowerThanLBound()) this.value = this.lowerBound;
    }

    public int getLowerBound() {
        return this.lowerBound;
    }

    public void setUpperBound(int newValue) {
        if (isNewUBoundValid(newValue)) this.upperBound = newValue;
        if (isValueGreaterThanUBound()) this.value = this.upperBound;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public boolean isNewValueValid(int newValue) {
        return this.lowerBound <= newValue && newValue <= this.upperBound;
    }

    public boolean isNewLBoundValid(int newValue) {
        return newValue < this.upperBound;
    }

    public boolean isNewUBoundValid(int newValue) {
        return newValue > this.lowerBound;
    }

    public boolean isValueLowerThanLBound() {
        return this.value > this.lowerBound;
    }

    public boolean isValueGreaterThanUBound() {
        return this.value > this.upperBound;
    }

    public void addToXml(Document sourceDocument, Element sourceElement) {
        Element localNode = sourceDocument.createElement(super.getName());
        sourceElement.appendChild(localNode);
        localNode.appendChild(sourceDocument.createTextNode(String.valueOf(this.getValue())));
    }
}