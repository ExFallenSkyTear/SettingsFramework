import com.java.framework.settings.*;

public class Main {
    public static final SettingsManager globalSettings = new SettingsManager();

    public static void main(String[] args) throws Exception {
        Category databaseCategory = globalSettings.createCategory("Database");

        AddressEntry databaseAddressEntry = databaseCategory.createEntry(AddressEntry.class, "Address");
        StringEntry databaseEmailEntry = databaseCategory.createEntry(StringEntry.class, "Email");
        IntegerEntry databasePortEntry = databaseCategory.createEntry(IntegerEntry.class, "Port");

        Category loggingCategory = globalSettings.createCategory("Log");

        DoubleEntry loggingLevelEntry = loggingCategory.createEntry(DoubleEntry.class, "Level");
        BooleanEntry loggingEnabledEntry = loggingCategory.createEntry(BooleanEntry.class, "Enabled");

        databaseEmailEntry.setValidationPattern("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        databasePortEntry.setLowerBound(0);
        databasePortEntry.setUpperBound(65525);

        //globalSettings.importConfiguration("C:\\tmp\\test.xml");

        System.out.println(databaseAddressEntry.getValue());
        System.out.println(databaseEmailEntry.getValue());
        System.out.println(databasePortEntry.getValue());
        System.out.println(loggingLevelEntry.getValue());
        System.out.println(loggingEnabledEntry.getValue());

        globalSettings.exportConfiguration("C:\\tmp\\");
    }
}