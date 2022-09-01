import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringEntry extends GenericEntry implements EntryInterface<String> {
    private String value;
    private Pattern validationPattern;

    public void setValue(String newValue) {
        if (isNewValueValid(newValue)) this.value = newValue;
    }

    public String getValue() {
        return this.value;
    }

    public void setValidationPattern(String newValidationPattern) {
        this.validationPattern = Pattern.compile(newValidationPattern);
    }

    public boolean isNewValueValid(String newValue) {
        Matcher matcher = this.validationPattern.matcher(newValue);
        return matcher.find();
    }
}