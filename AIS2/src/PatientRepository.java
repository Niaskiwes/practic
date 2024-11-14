import java.util.ArrayList;
import java.util.List;

public class PatientRepository {
    private List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient getPatientBySnils(String snils) {
        return patients.stream()
                .filter(p -> p.getSnils().equals(snils))
                .findFirst()
                .orElse(null);
    }

    public void deletePatientBySnils(String snils) {
        patients.removeIf(p -> p.getSnils().equals(snils));
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }
}