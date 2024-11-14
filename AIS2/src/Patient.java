import java.time.LocalDate;

public class Patient {
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String phone;
    private String snils;
    private BloodType bloodType;

    public Patient(String name, LocalDate birthDate, Gender gender, String address,
                   String phone, String snils, BloodType bloodType) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.snils = snils;
        this.bloodType = bloodType;
    }

    // Getters
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public Gender getGender() { return gender; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getSnils() { return snils; }
    public BloodType getBloodType() { return bloodType; }
}