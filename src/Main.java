import com.java.framework.settings.*;

public class Main {
    public static final SettingsManager globalSettings = new SettingsManager();
    public static void main(String[] args) throws Exception {
        Category databaseCategory = globalSettings.createCategory("Database");

        AddressEntry databaseAddressEntry = databaseCategory.createEntry(AddressEntry.class, "Address");
        StringEntry databaseEmailEntry = databaseCategory.createEntry(StringEntry.class, "Email");

        databaseEmailEntry.setValidationPattern("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

        System.out.println(databaseAddressEntry.getValue());
        databaseAddressEntry.setValue("192.168.1.1");
        System.out.println(databaseAddressEntry.getValue());
        System.out.println(databaseEmailEntry.isNewValueValid("name.surname@domain.net"));

        databaseEmailEntry.setValue("name.surname@domain.net");

        System.out.println(databaseEmailEntry.getValue());

        NumericEntry<Integer> databasePortEntry = databaseCategory.createEntry(NumericEntry.class, "Port");

        System.out.println(databasePortEntry.getLowerBound());
        System.out.println(databasePortEntry.getUpperBound());
        System.out.println(databasePortEntry.getValue());

        databasePortEntry.setLowerBound(0);
        databasePortEntry.setUpperBound(65525);
        databasePortEntry.setValue(203);

        System.out.println(databasePortEntry.getLowerBound());
        System.out.println(databasePortEntry.getUpperBound());
        System.out.println(databasePortEntry.getValue());

        databasePortEntry.setValue(65525);
        System.out.println(databasePortEntry.getValue());

        globalSettings.exportConfiguration("C:\\tmp\\test.xml");
    }
}