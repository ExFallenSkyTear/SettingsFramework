package com.java.framework.settings;

public class NumericEntry<numericType> extends GenericEntry implements EntryInterface<numericType> {
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

    public numericType GetLowerBound() {
        return this.lowerBound;
    }

    public void setUpperBound(numericType newValue) {
        if (isNewUBoundValid(newValue)) this.upperBound = newValue;
        if (isValueGreaterThanUBound()) this.value = this.upperBound;
    }

    public numericType GetUpperBound() {
        return this.upperBound;
    }

    public boolean isNewValueValid(numericType newValue) {
        return this.lowerBound <= newValue && newValue <= this.upperBound;
    }

    public boolean isNewLBoundValid(numericType newValue) {
        return newValue < this.upperBound;
    }

    public boolean isNewUBoundValid(numericType newValue) {
        return newValue > this.upperBound;
    }

    public boolean isValueLowerThanLBound() {
        return this.value > this.lowerBound;
    }

    public boolean isValueGreaterThanUBound() {
            return this.value > this.upperBound;
        }
}