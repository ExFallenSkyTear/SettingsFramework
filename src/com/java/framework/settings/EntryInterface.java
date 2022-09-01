package com.java.framework.settings;

public interface EntryInterface<EntryType> {
    void setValue(EntryType newValue);
    EntryType getValue();
}