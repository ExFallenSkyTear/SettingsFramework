import java.util.HashMap;

public class SettingsManager {
    private HashMap<String, Category> settingsCategories = new HashMap<>();

    public Category createCategory(String categoryName) {
        Category newCategory = new Category(categoryName);
        settingsCategories.put(categoryName, newCategory);
        return newCategory;
    }

    public Category getCategory(String categoryName) {
        return settingsCategories.get(categoryName);
    }
}