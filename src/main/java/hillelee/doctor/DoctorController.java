package hillelee.doctor;

import hillelee.Config;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/27/2017.
 */
@RestController
@AllArgsConstructor
public class DoctorController
{
  
  private Config config;
  private DoctorService doctorService;
  
  @GetMapping("/doctors/{id}")
  public ResponseEntity<?> getDoctorById(@PathVariable Integer id) {
    Optional<Doctor> found = doctorService.getDoctorById(id);
    return found.isPresent() ?
        ResponseEntity.ok(found.get()) :
        ResponseEntity.notFound().build();
  }
  
  @GetMapping("/doctors")
  public List<Doctor> getDoctors(@RequestParam Optional<String> name,
                           @RequestParam Optional<String> specialization) {
    return doctorService.getDoctors(name, specialization);
  }
  
  @PostMapping("/doctors")
  public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor) {
    Optional<Doctor> created = doctorService.createDoctor(doctor);
    return created.isPresent() ?
        ResponseEntity.created(URI.create("/doctors/" + created.get().getId())).build() :
        ResponseEntity.status(HttpStatus.CONFLICT).body("doctor with ID " + doctor.getId() + " already exists");
  }

  @PutMapping("/doctors/{id}")
  public ResponseEntity<?> updateDoctor(@PathVariable Integer id, @RequestBody Doctor doctor) {
    Optional<Doctor> updated = doctorService.updateDoctor(id, doctor);
    return updated.isPresent() ?
        ResponseEntity.ok(updated.get()) :
        ResponseEntity.notFound().build();
  }

  @DeleteMapping("/doctors/{id}")
  public ResponseEntity<?> deleteDoctor(@PathVariable Integer id)
  {
    return doctorService.deleteDoctor(id).isPresent() ?
        ResponseEntity.noContent().build() :
        ResponseEntity.notFound().build();
  }
  
  @GetMapping("/specialties")
  public List<String> getSpecialties() {
    return config.getSpecialties();
  }
  
}
