package hillelee.doctor;

import hillelee.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
  
  private final Config config;
  private final JpaDoctorRepository doctorRepository;
  //private final DoctorRepository doctorRepository;
  
//  public List<Doctor> getDoctors(Optional<String> name, Optional<String> specialization) {
//    Predicate<Doctor> nameFilter = name.map(this::filterByName).orElse(doc -> true);
//    Predicate<Doctor> specFilter = specialization.map(this::filterBySpec).orElse(doc -> true);
//    Predicate<Doctor> compositeFilter = nameFilter.and(specFilter);
//    return doctorRepository.getDoctors().values().stream()
//        .filter((compositeFilter))
//        .collect(Collectors.toList());
//  }
  
  public List<Doctor> getDoctors(Optional<String> name, Optional<String> specialty) {
    return doctorRepository.findByNameAndSpecialty(name.orElse(null), specialty.orElse(null));
  }
  
//  private Predicate<Doctor> filterByName(String name) {
//    return doc -> doc.getName().startsWith(name);
//  }
//
//  private Predicate<Doctor> filterBySpec(String specialization) {
//    return doc -> doc.getSpecialty().equals(specialization);
//  }
  
  public Optional<Doctor> getDoctorById(Integer id) {
    return doctorRepository.findById(id);
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
  
  public Optional<Doctor> deleteDoctor(Integer id)
  {
    Optional<Doctor> mayBeDoctor = doctorRepository.findById(id);
    mayBeDoctor.ifPresent(doctor -> doctorRepository.delete(doctor.getId()));
    return mayBeDoctor;
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
