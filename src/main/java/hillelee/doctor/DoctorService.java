package hillelee.doctor;

import hillelee.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DoctorService {
  
  private final Config config;
  private final JpaDoctorRepository doctorRepository;
  
  public List<Doctor> getDoctors(String name, String specialty) {
    return doctorRepository.findByNameAndSpecialty(name, specialty);
  }
  
  public Doctor getDoctorById(Integer id) {
    return doctorRepository.findOne(id);
  }
  
  public Doctor createDoctor(Doctor doctor) {
    validateSpecialty(doctor);
    return doctorRepository.save(doctor);
  }
  
  public Doctor updateDoctor(Integer id, Doctor doctor) {
    validateIdNotModified(id, doctor);
    validateSpecialty(doctor);
    return doctorRepository.save(doctor);
  }
  
  public void deleteDoctor(Integer id)
  {
    doctorRepository.delete(id);
  }
  
  private void validateSpecialty(Doctor doctor) {
    if (!config.getSpecialties().contains(doctor.getSpecialty())) {
      throw new InvalidDoctorSpecialty("Specialty '" + doctor.getSpecialty() +
                                           "' is not allowed (use '/specialties' endpoint to check allowed list)");
    }
  }
  
  private void validateIdNotModified(Integer id, Doctor doctor) {
    if (!Objects.equals(id, doctor.getId())) {
      throw new IdModificationIsNotAllowed("Existing doctor ID modification is not allowed");
    }
  }
  
  
}
