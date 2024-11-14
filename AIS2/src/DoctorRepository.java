import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    private List<Doctor> doctors = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }


    public Doctor getDoctorByName(String name) {
        return doctors.stream()
                .filter(d -> d.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void deleteDoctorByName(String name) {
        doctors.removeIf(d -> d.getName().equals(name));
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }
}
