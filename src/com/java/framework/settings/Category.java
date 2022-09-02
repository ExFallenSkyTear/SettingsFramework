package com.java.framework.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class Category {
    private final ArrayList<GenericEntry> categoryEntries = new ArrayList<>();

    private final String name;

    private boolean ignoreExport;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isIgnoringExport() {
        return this.ignoreExport;
    }

    public void enableExport() {
        this.ignoreExport = true;
    }

    public void disableExport() {
        this.ignoreExport = false;
    }

    public <E extends GenericEntry> E createEntry(final Class<E> clazz, String entryName) throws Exception {
        if (!this.entryExist(entryName)) {
            E newEntry = clazz.newInstance();
            newEntry.setName(entryName);
            categoryEntries.add(newEntry);
            return newEntry;
        } else throw new IllegalArgumentException();
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

    public void addToXml(Document sourceDocument, Element sourceElement){
        Element localNode = sourceDocument.createElement(this.name);
        sourceElement.appendChild(localNode);

        for (int i = 0; i < categoryEntries.size(); i++) {
            categoryEntries.get(i).addToXml(sourceDocument, localNode);
        }
    }
}