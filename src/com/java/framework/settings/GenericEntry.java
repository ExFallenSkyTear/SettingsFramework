public class GenericEntry<EntryType> {
    private String name = null;

    public void setName(String name) {
        if (this.name == null) this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public EntryType getValue() {
        return null;
    }
}