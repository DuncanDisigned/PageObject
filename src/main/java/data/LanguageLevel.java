package data;

public enum LanguageLevel {
    NATIVE("native"),
    ADVANCED("advanced"),
    BEGINNER("beginner"),
    INTERMEDIATE("intermediate");

    private final String value;

    LanguageLevel(String value) {
        this.value = value; // Значения в нижнем регистре
    }

    public String getValue() {
        return value;
    }
}