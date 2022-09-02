package com.java.framework.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DoubleEntry extends GenericEntry<Double> implements EntryInterface<Double> {
    private double value;

    private double lowerBound;

    private double upperBound;

    public void setValue(Double newValue) {
        if (isNewValueValid(newValue)) this.value = newValue;
    }

    public Double getValue() {
        return this.value;
    }

    public void setLowerBound(double newValue) {
        if (isNewLBoundValid(newValue)) this.lowerBound = newValue;
        if (isValueLowerThanLBound()) this.value = this.lowerBound;
    }

    public double getLowerBound() {
        return this.lowerBound;
    }

    public void setUpperBound(double newValue) {
        if (isNewUBoundValid(newValue)) this.upperBound = newValue;
        if (isValueGreaterThanUBound()) this.value = this.upperBound;
    }

    public double getUpperBound() {
        return this.upperBound;
    }

    public boolean isNewValueValid(double newValue) {
        return this.lowerBound == this.upperBound || this.lowerBound <= newValue && newValue <= this.upperBound;
    }

    public boolean isNewLBoundValid(double newValue) {
        return newValue < this.upperBound;
    }

    public boolean isNewUBoundValid(double newValue) {
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

    public Double parse(String value) {
        return Double.parseDouble(value);
    }
}