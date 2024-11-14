import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VisitService {
    private List<Visit> visits = new ArrayList<>();
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public VisitService(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public void createVisit(String patientName, String doctorName,
                            LocalDateTime dateTime, String diagnosis,
                            List<String> prescriptions) {
        Patient patient = patientRepository.getAllPatients().stream()
                .filter(p -> p.getName().equals(patientName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        Doctor doctor = doctorRepository.getDoctorByName(doctorName);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor not found");
        }

        Visit visit = new Visit(patient, doctor, dateTime, diagnosis, prescriptions);
        visits.add(visit);
    }

    public Visit getVisit(String patientName, String doctorName, String dateTimeStr) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        return visits.stream()
                .filter(v -> v.getPatient().getName().equals(patientName) &&
                        v.getDoctor().getName().equals(doctorName) &&
                        v.getDateTime().equals(dateTime))
                .findFirst()
                .orElse(null);
    }

    public void deleteVisit(String patientName, String doctorName, String dateTimeStr) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        visits.removeIf(v -> v.getPatient().getName().equals(patientName) &&
                v.getDoctor().getName().equals(doctorName) &&
                v.getDateTime().equals(dateTime));
    }

    public List<Visit> getAllVisits() {
        return new ArrayList<>(visits);
    }
}
