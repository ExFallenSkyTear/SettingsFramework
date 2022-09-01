package com.java.framework.settings;

import java.util.HashMap;

public class Category {
    private HashMap<String, GenericEntry> categoryEntries = new HashMap<>();
    private final String name;

    Category(String name) {
        this.name = name;
    }

    public <E extends GenericEntry> E createEntry(final Class<E> clazz, String entryName) throws Exception {
        E newEntry = clazz.newInstance();
        newEntry.setName(entryName);
        categoryEntries.put(entryName, newEntry);
        return newEntry;
    }

    public <E extends GenericEntry> E getEntry(String entryName) {
        return (E) categoryEntries.get(entryName);
    }

    public String getName() {
        return this.name;
    }
}