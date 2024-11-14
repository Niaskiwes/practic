import java.time.LocalDateTime;
import java.util.List;

public class Visit {
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;
    private String diagnosis;
    private List<String> prescriptions;

    public Visit(Patient patient, Doctor doctor, LocalDateTime dateTime,
                 String diagnosis, List<String> prescriptions) {
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.diagnosis = diagnosis;
        this.prescriptions = prescriptions;
    }

    // Getters
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getDiagnosis() { return diagnosis; }
    public List<String> getPrescriptions() { return prescriptions; }
}
