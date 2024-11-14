public enum Specialization {
    THERAPIST("Терапевт"),
    SURGEON("Хирург"),
    PEDIATRICIAN("Педиатр"),
    NEUROLOGIST("Невролог"),
    CARDIOLOGIST("Кардиолог");

    private final String displayName;

    Specialization(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}