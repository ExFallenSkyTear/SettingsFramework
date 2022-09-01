package com.java.framework.settings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class SettingsManager {
    private ArrayList<Category> settingsCategories = new ArrayList<>();

    public Category createCategory(String categoryName) throws IllegalArgumentException {
        if (categoryExist(categoryName)) throw new IllegalArgumentException();
        Category newCategory = new Category(categoryName);
        settingsCategories.add(newCategory);
        return newCategory;
    }

    public Category getCategory(String categoryName) {
        return settingsCategories.get(getCategoryIndex(categoryName));
    }

    public boolean categoryExist(String categoryName) {
        return getCategoryIndex(categoryName) >= 0;
    }

    private int getCategoryIndex(String categoryName) {
        for (int i = 0; i < settingsCategories.size(); i++) {
            if (settingsCategories.get(i).getName() == categoryName) return i;
        }
        return -1;
    }

    public void writeSettingsToFile(String fullFilePath) throws IOException {
        Properties prop = new Properties();
        InputStream in = getClass().getResourceAsStream("xyz.properties");
        prop.load(in);

        prop.setProperty("newkey", "newvalue");

        prop.store(new FileOutputStream("xyz.properties"), null);
    }
}