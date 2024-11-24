package data;

public enum LanguageLevel {
    beginner("beginner"),
    intermediate("intermediate"),
    advanced("advanced"),
    NATIVE("native");

    private final String value;

    LanguageLevel(String value) {
        this.value = value.toLowerCase();
    }

    public String getValue() {
        return value;
    }
}
