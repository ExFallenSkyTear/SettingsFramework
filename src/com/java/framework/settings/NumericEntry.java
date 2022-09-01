package com.java.framework.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class NumericEntry<numericType extends Number> extends GenericEntry<numericType> implements EntryInterface<numericType> {
    private numericType value;

    private numericType lowerBound;

    private numericType upperBound;

    public void setValue(numericType newValue) {
        if (isNewValueValid(newValue)) this.value = newValue;
    }

    public numericType getValue() {
        return this.value;
    }

    public void setLowerBound(numericType newValue) {
        if (isNewLBoundValid(newValue)) this.lowerBound = newValue;
        if (isValueLowerThanLBound()) this.value = this.lowerBound;
    }

    public numericType getLowerBound() {
        return this.lowerBound;
    }

    public void setUpperBound(numericType newValue) {
        if (isNewUBoundValid(newValue)) this.upperBound = newValue;
        if (isValueGreaterThanUBound()) this.value = this.upperBound;
    }

    public numericType getUpperBound() {
        return this.upperBound;
    }

    public boolean isNewValueValid(numericType newValue) {
        return this.lowerBound.doubleValue() <= newValue.doubleValue() && newValue.doubleValue() <= this.upperBound.doubleValue();
    }

    public boolean isNewLBoundValid(numericType newValue) {
        return this.upperBound == null || newValue.doubleValue() < this.upperBound.doubleValue();
    }

    public boolean isNewUBoundValid(numericType newValue) {
        return this.lowerBound == null || newValue.doubleValue() > this.lowerBound.doubleValue();
    }

    public boolean isValueLowerThanLBound() {
        return this.lowerBound != null && this.value != null && this.value.doubleValue() > this.lowerBound.doubleValue();
    }

    public boolean isValueGreaterThanUBound() {
            return this.upperBound != null && this.value != null && this.value.doubleValue() > this.upperBound.doubleValue();
    }

    public void addToXml(Document sourceDocument, Element sourceElement) {
        Element localNode = sourceDocument.createElement(super.getName());
        sourceElement.appendChild(localNode);
        localNode.appendChild(sourceDocument.createTextNode(this.getValue().toString()));
    }
}