import com.java.framework.settings.*;

public class Main {
    public static final SettingsManager globalSettings = new SettingsManager();
    public static void main(String[] args) throws Exception {
        Category databaseCategory = globalSettings.createCategory("Database");

        AddressEntry databaseAddressEntry = databaseCategory.createEntry(AddressEntry.class, "Address");
        StringEntry databaseEmailEntry = databaseCategory.createEntry(StringEntry.class, "Address");

        databaseEmailEntry.setValidationPattern("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

        System.out.println(databaseAddressEntry.getValue());
        databaseAddressEntry.setValue("192.168.1.1");
        System.out.println(databaseAddressEntry.getValue());
        System.out.println(databaseEmailEntry.isNewValueValid("name.surname@domain.net"));
    }
}