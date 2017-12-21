package hillelee.doctor;

import hillelee.HilleleeConfig;
import hillelee.doctor.exceptions.DoctorAlreadyExistsException;
import hillelee.doctor.exceptions.DoctorNotFoundException;
import hillelee.doctor.exceptions.IdModificationIsNotAllowedException;
import hillelee.doctor.exceptions.InvalidDoctorSpecialtyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DoctorService {
  
  private final HilleleeConfig config;
  private final JpaDoctorRepository doctorRepository;
  
  public List<Doctor> getDoctors(String name, List<String> specialties) {
    if (name != null) name = name.toLowerCase();
    //return doctorRepository.findByNameAndSpecialty(name, specialties);
    return doctorRepository.findAll();
  }
  
  public Doctor getDoctorById(Integer id) {
    validateNotExists(id);
    return doctorRepository.findOne(id);
  }
  
  public List<String> getDoctorSpecialties(Integer id) {
    //return doctorRepository.getSpecialties(id);
    return getDoctorById(id).getSpecialties();
  }
  
  public Doctor createDoctor(Doctor doctor) {
    validateAlreadyExists(doctor.getId());
    validateSpecialty(doctor);
    return doctorRepository.save(doctor);
  }
  
  public Doctor updateDoctor(Integer id, Doctor doctor) {
    validateNotExists(id);
    validateIdNotModified(id, doctor);
    validateSpecialty(doctor);
    return doctorRepository.save(doctor);
  }
  
  public void deleteDoctor(Integer id) {
    validateNotExists(id);
    doctorRepository.delete(id);
  }
  
  private void validateSpecialty(Doctor doctor) {
    if (!config.getSpecialties().containsAll(doctor.getSpecialties())) {
      throw new InvalidDoctorSpecialtyException();
    }
  }
  
  private void validateIdNotModified(Integer id, Doctor doctor) {
    if (!Objects.equals(id, doctor.getId())) {
      throw new IdModificationIsNotAllowedException();
    }
  }
  
  private void validateNotExists(Integer id) {
    if (!doctorRepository.exists(id)) {
      throw new DoctorNotFoundException();
    }
  }
  
  private void validateAlreadyExists(Integer id) {
    if (doctorRepository.exists(id)) {
      throw new DoctorAlreadyExistsException();
    }
  }
  
  
}
