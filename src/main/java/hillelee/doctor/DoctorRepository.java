package hillelee.doctor;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Repository
public class DoctorRepository {
  
  private Map<Integer, Doctor> doctors = new HashMap<>();
  
  {
    createDoctor(new Doctor(generateId(), "Komarovsky", "surgeon"));
    createDoctor(new Doctor(generateId(), "Shutko", "surgeon"));
    createDoctor(new Doctor(generateId(), "Malahov", "dentist"));
    createDoctor(new Doctor(generateId(), "Stupka", "neurologist"));
  }
  
  public Map<Integer, Doctor> getDoctors() {
    return doctors;
  }
  
  public Optional<Doctor> getDoctorById(Integer id) {
    return Optional.ofNullable(doctors.get(id));
  }
  
  public Optional<Doctor> createDoctor(Doctor doctor) {
    if (doctor.getId() == null) doctor.setId(generateId());
    if (doctors.containsKey(doctor.getId())) return Optional.empty();
    doctors.put(doctor.getId(), doctor);
    return Optional.of(doctor);
  }
  
  public Optional<Doctor> updateDoctor(Integer id, Doctor doctor) {
    if (!doctors.containsKey(id)) return Optional.empty();
    doctors.put(id, doctor);
    return Optional.of(doctor);
  }
  
  public Optional<Doctor> deleteDoctor(Integer id)
  {
    return Optional.ofNullable(doctors.remove(id));
  }
  
  private Integer generateId() {
    return new Random().nextInt(100000);
  }
  
}
