package hillelee.doctor;

import hillelee.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/27/2017.
 */
@RestController
@RequiredArgsConstructor
public class DoctorController
{
  private final Config config;
  private final DoctorService doctorService;
  
  @GetMapping("/doctors/{id}")
  public ResponseEntity<?> getDoctorById(@PathVariable Integer id) {
    Doctor doctor = doctorService.getDoctorById(id);
    if (doctor == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(doctor);
  }
  
  @GetMapping("/doctors")
  public List<Doctor> getDoctors(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String specialty) {
    return doctorService.getDoctors(name, specialty);
  }
  
  @PostMapping("/doctors")
  @ResponseStatus(HttpStatus.CREATED)
  public Doctor createDoctor(@RequestBody Doctor doctor) {
    return doctorService.createDoctor(doctor);
  }
  
  @PutMapping("/doctors/{id}")
  public Doctor updateDoctor(@PathVariable Integer id, @RequestBody Doctor doctor) {
    return doctorService.updateDoctor(id, doctor);
  }

  @DeleteMapping("/doctors/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteDoctor(@PathVariable Integer id)
  {
    doctorService.deleteDoctor(id);
  }
  
  @GetMapping("/specialties")
  public List<String> getSpecialties() {
    return config.getSpecialties();
  }
  
}
