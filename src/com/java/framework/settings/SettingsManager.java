package com.java.framework.settings;

import java.util.ArrayList;

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
}