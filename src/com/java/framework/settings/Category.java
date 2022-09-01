package com.java.framework.settings;

import java.util.ArrayList;

public class Category {
    private ArrayList<GenericEntry> categoryEntries = new ArrayList<>();
    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public <E extends GenericEntry> E createEntry(final Class<E> clazz, String entryName) throws Exception {
        E newEntry = clazz.newInstance();
        newEntry.setName(entryName);
        categoryEntries.add(newEntry);
        return newEntry;
    }

    public <E extends GenericEntry> E getEntry(String entryName) {
        return (E) categoryEntries.get(getEntryIndex(entryName));
    }

    public boolean entryExist(String entryName) {
        return getEntryIndex(entryName) >= 0;
    }

    private int getEntryIndex(String entryName) {
        for (int i = 0; i < categoryEntries.size(); i++) {
            if (categoryEntries.get(i).getName() == entryName) return i;
        }
        return -1;
    }
}