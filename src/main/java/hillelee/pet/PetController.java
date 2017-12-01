package hillelee.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by JavaEE on 11/18/2017.
 */
@RestController
public class PetController {

//    private List<Pet> pets = new ArrayList<Pet>() {{
//        add(new Pet("Tom", "Cat", 3));
//        add(new Pet("Jerry", "Mouse", 1));
//    }};

    private Map<Integer, Pet> pets = new HashMap<Integer, Pet>() {{
        put(0, new Pet("Tom", "Cat", 3));
        put(1, new Pet("Jerry", "Mouse", 1));
    }};

    //private final GreetingProvider greetingProvider;
    
    @GetMapping("/greeting")
    public String helloWorld() {
        //return greetingProvider.getRandomGreeting();
        return "Hello!";
    }

    @GetMapping("/pets")
    public List<Pet> getPets(@RequestParam Optional<String> specie,
                             @RequestParam Optional<Integer> age) {
        Predicate<Pet> specieFilter = specie.map(this::filterBySpecie).orElse(pet -> true);
        Predicate<Pet> ageFilter = age.map(this::filterByAge).orElse(pet -> true);
        Predicate<Pet> complexFilter = ageFilter.and(specieFilter);
        return pets.values().stream()
                .filter((complexFilter))
                .collect(Collectors.toList());
    }

    private Predicate<Pet> filterBySpecie(String specie) {
        return pet -> pet.getSpecie().equals(specie);
    }

    private Predicate<Pet> filterByAge(Integer age) {
        return pet -> pet.getAge().equals(age);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Integer id) {
        if (id >= pets.size()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorBody("no pet with ID = " + id));
        }
        return ResponseEntity.ok(pets.get(id));
    }

    @PostMapping("/pets")
    public ResponseEntity<Void> createPet(@RequestBody Pet pet) {
        Integer id = generateId();
        pets.put(id, pet);
        return ResponseEntity.created(URI.create("/pets/" + id)).build();
    }

    @PutMapping("/pets/{id}")
    public void updatePet(@PathVariable Integer id, @RequestBody Pet pet) {
        pets.put(id, pet);
    }

    @DeleteMapping("/pets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable Integer id) {
        if (!pets.containsKey(id)) {
            throw new NoSuchPetException();
        }
        pets.remove(id.intValue());
    }

    private Integer generateId() {
        return new Random().nextInt(100000);
    }

}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class NoSuchPetException extends RuntimeException {}

@Data
@AllArgsConstructor
class ErrorBody {
    private String message;
    private final Integer code = 400;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Pet {
    private String name;
    private String specie;
    private Integer age;
}
