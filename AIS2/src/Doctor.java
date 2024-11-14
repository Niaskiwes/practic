public class Doctor {
    private String name;
    private Specialization specialization;
    private Category category;
    private String phone;

    public Doctor(String name, Specialization specialization, Category category, String phone) {
        this.name = name;
        this.specialization = specialization;
        this.category = category;
        this.phone = phone;
    }

    // Getters
    public String getName() { return name; }
    public Specialization getSpecialization() { return specialization; }
    public Category getCategory() { return category; }
    public String getPhone() { return phone; }
}
