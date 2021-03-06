package hillelee.pet;

import hillelee.ErrorBody;
import hillelee.pet.dto.PrescriptionInputDto;
import hillelee.store.NoSuchMedicineException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by JavaEE on 11/18/2017.
 */
@RestController
@AllArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping("/pets")
    public List<Pet> getPets(@RequestParam Optional<String> specie,
                             @RequestParam Optional<Integer> age,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> birthDate) {
        return petService.getPetsUsingSingleMethod(specie, age, birthDate);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Integer id) {
        Optional<Pet> mayBePet = petService.getById(id);
        return mayBePet.map(Object.class::cast)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body(new ErrorBody("there is no pet with ID " + id)));
    }

    @PostMapping("/pets")
    public ResponseEntity<Void> createPet(@RequestBody Pet pet) {
        Pet saved = petService.save(pet);
        return ResponseEntity.created(URI.create("/pets/" + saved.getId())).build();
    }

    @PutMapping("/pets/{id}")
    public void updatePet(@PathVariable Integer id, @RequestBody Pet pet) {
        pet.setId(id);
        petService.save(pet);
    }

    @DeleteMapping("/pets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable Integer id) {
        petService.delete(id).orElseThrow(NoSuchPetException::new);
    }

    @PostMapping("/pets/{id}/prescriptions")
    public void prescribe(@PathVariable Integer id, @RequestBody @Valid PrescriptionInputDto dto) {
        petService.prescribe(id, dto.getDescription(), dto.getMedicineName(), dto.getQuantity(), dto.getTimesPerDay());
    }

    @ExceptionHandler(NoSuchMedicineException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void noSuchMedicine(){}
}

