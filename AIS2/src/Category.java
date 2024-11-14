public enum Category {
    FIRST("Первая"),
    SECOND("Вторая"),
    HIGHEST("Высшая");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
