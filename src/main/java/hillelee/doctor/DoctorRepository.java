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
    addDoctor(new Doctor(generateId(), "Komarovsky", "surgeon"));
    addDoctor(new Doctor(generateId(), "Shutko", "surgeon"));
    addDoctor(new Doctor(generateId(), "Malahov", "dentist"));
    addDoctor(new Doctor(generateId(), "Stupka", "neurologist"));
  }
  
  public Map<Integer, Doctor> getDoctors() {
    return doctors;
  }
  
  public Optional<Doctor> getDoctorById(Integer id) {
    return Optional.ofNullable(doctors.get(id));
  }
  
  public Optional<Doctor> addDoctor(Doctor doctor) {
    if (doctor.getId() == null) doctor.setId(generateId());
    if (doctors.containsKey(doctor.getId())) return Optional.empty();
    doctors.put(doctor.getId(), doctor);
    return Optional.of(doctor);
  }
  
  public Optional<Doctor> deleteDoctor(Integer id)
  {
    return Optional.ofNullable(doctors.remove(id));
  }
  
  public Integer generateId() {
    return new Random().nextInt(100000);
  }
  
}
