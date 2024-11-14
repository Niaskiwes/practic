public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
