package hillelee.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
  
  private DoctorRepository doctorRepository;
  
  public List<Doctor> getDoctors(Optional<String> name, Optional<String> specialization) {
    Predicate<Doctor> nameFilter = name.map(this::filterByName).orElse(doc -> true);
    Predicate<Doctor> specFilter = specialization.map(this::filterBySpec).orElse(doc -> true);
    Predicate<Doctor> compositeFilter = nameFilter.and(specFilter);
    return doctorRepository.getDoctors().values().stream()
        .filter((compositeFilter))
        .collect(Collectors.toList());
  }
  
  private Predicate<Doctor> filterByName(String name) {
    return doc -> doc.getName().startsWith(name);
  }
  
  private Predicate<Doctor> filterBySpec(String specialization) {
    return doc -> doc.getSpecialization().equals(specialization);
  }

}
