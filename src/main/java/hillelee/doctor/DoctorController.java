package hillelee.doctor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/27/2017.
 */
@RestController
public class DoctorController
{
  private Map<Integer, Doctor> doctors = new HashMap<>();
  
  {
    addDoctor(new Doctor(generateId(), "Komarovsky", "surgeon"));
    addDoctor(new Doctor(generateId(), "Shutko", "surgeon"));
    addDoctor(new Doctor(generateId(), "Malahov", "dentist"));
    addDoctor(new Doctor(generateId(), "Stupka", "neurologist"));
  }
  
  @GetMapping("/doctors/{id}")
  public ResponseEntity<?> getDoctorById(@PathVariable Integer id) {
    return doctors.containsKey(id) ? ResponseEntity.ok(doctors.get(id)) : ResponseEntity.notFound().build();
  }
  
  @GetMapping("/doctors")
  public List<Doctor> getDoctors(@RequestParam Optional<String> name,
                           @RequestParam Optional<String> specialization) {
    Predicate<Doctor> nameFilter = name.map(this::filterByName).orElse(doc -> true);
    Predicate<Doctor> specFilter = specialization.map(this::filterBySpec).orElse(doc -> true);
    Predicate<Doctor> compositeFilter = nameFilter.and(specFilter);
    return doctors.values().stream()
        .filter((compositeFilter))
        .collect(Collectors.toList());
  }
  
  private Predicate<Doctor> filterByName(String name) {
    return doc -> doc.getName().startsWith(name);
  }
  
  private Predicate<Doctor> filterBySpec(String specialization) {
    return doc -> doc.getSpecialization().equals(specialization);
  }
  
  @PostMapping("/doctors")
  public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor) {
    if (doctor.getId() == null) doctor.setId(generateId());
    if (doctors.containsKey(doctor.getId())) return ResponseEntity.status(HttpStatus.CONFLICT)
            .body("doctor with ID " + doctor.getId() + " already exists");
    addDoctor(doctor);
    return ResponseEntity.created(URI.create("/doctors/" + doctor.getId())).build();
  }

  @PutMapping("/doctors/{id}")
  public ResponseEntity<?> updateDoctor(@PathVariable Integer id, @RequestBody Doctor doctor) {
    if (!doctors.containsKey(id)) {
      return ResponseEntity.notFound().build();
    }
    if (!Objects.equals(doctor.getId(), doctors.get(id).getId())) {
      return ResponseEntity.badRequest().body("change ID is not permitted");
    }
    addDoctor(doctor);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/doctors/{id}")
  public ResponseEntity<?> deleteDoctor(@PathVariable Integer id) {
    if (!doctors.containsKey(id)) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
  }

  private void addDoctor(Doctor doctor) {
    doctors.put(doctor.getId(), doctor);
  }
  
  private Integer generateId() {
    return new Random().nextInt(100000);
  }
  
}
