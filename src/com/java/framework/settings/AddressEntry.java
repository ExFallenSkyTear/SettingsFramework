package com.java.framework.settings

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressEntry extends GenericEntry implements EntryInterface<String> {
    private int[] subClasses = new int[] {0, 0, 0, 0};

    private final Pattern validationPattern = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$");

    public void setValue(String newValue) {
        if (this.isNewValueValid(newValue)) {
            String[] rawIpClasses = newValue.split("\\.");

            for (int i = 0; i <= 3; i++) {
                this.setSubClass(i, Integer.parseInt(rawIpClasses[i]));
            }
        }
    }

    public String getValue() {
        return String.format("%s.%s.%s.%s",
                this.getSubClass(0),
                this.getSubClass(1),
                this.getSubClass(2),
                this.getSubClass(3)
        );
    }

    public void setSubClass(int classIndex, int newValue) {
        if (0 <= newValue && newValue <= 255) {
            this.subClasses[classIndex] = newValue;
        }
    }

    public int getSubClass(int classIndex) {
        return this.subClasses[classIndex];
    }

    public boolean isNewValueValid(String newValue) {
        Matcher matcher = this.validationPattern.matcher(newValue);
        return matcher.find();
    }
}