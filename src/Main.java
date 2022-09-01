import com.java.framework.settings.*;

public class Main {
    public static final SettingsManager globalSettings = new SettingsManager();
    public static void main(String[] args) throws Exception {

        Category databaseCategory = globalSettings.createCategory("Database");

        AddressEntry databaseAddressEntry = databaseCategory.createEntry(AddressEntry.class, "Address");
        StringEntry databaseEmailEntry = databaseCategory.createEntry(StringEntry.class, "Email");
        NumericEntry<Integer> databasePortEntry = databaseCategory.createEntry(NumericEntry.class, "Port");

        databaseEmailEntry.setValidationPattern("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        databasePortEntry.setLowerBound(0);
        databasePortEntry.setUpperBound(65525);

        globalSettings.importConfiguration("C:\\tmp\\test.xml");

        System.out.println(databaseAddressEntry.getValue());
        System.out.println(databaseEmailEntry.getValue());
        System.out.println(databasePortEntry.getValue());

        //globalSettings.exportConfiguration("C:\\tmp\\test.xml");
    }
}