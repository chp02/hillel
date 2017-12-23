package hillelee.pet;

import hillelee.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by JavaEE on 12/2/2017.
 */
@Service
@RequiredArgsConstructor
public class PetService {

    private final JpaPetRepository petRepository;
    private final StoreService storeService;

    public List<Pet> getPetsUsingSeparateJpaMethod(Optional<String> specie, Optional<Integer> age) {
        if (specie.isPresent() && age.isPresent()) {
            petRepository.findBySpecieAndAge(specie.get(), age.get());
        }
        if (specie.isPresent()) {
            return petRepository.findBySpecie(specie.get());
        }
        if (age.isPresent()) {
            return petRepository.findByAge(age.get());
        }
        return petRepository.findAll();
    }

    // just an example, can use
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private List<Prescription> prescriptions;
    @Transactional
    public List<Pet> getPetsUsingSingleMethod(Optional<String> specie, Optional<Integer> age) {
        List<Pet> nulableBySpecieAndAge =
                petRepository.findNullableBySpecieAndAge(specie.orElse(null), age.orElse(null));
        nulableBySpecieAndAge.forEach(pet -> System.out.println(pet.getPrescriptions()));
        return nulableBySpecieAndAge;
    }

    public List<Pet> getPetsUsingStreamFilters(Optional<String> specie, Optional<Integer> age) {
        Predicate<Pet> specieFilter = specie.map(this::filterBySpecie).orElse(pet -> true);
        Predicate<Pet> ageFilter = age.map(this::filterByAge).orElse(pet -> true);
        Predicate<Pet> complexFilter = ageFilter.and(specieFilter);
        return petRepository.findAll().stream()
                .filter((complexFilter))
                .collect(Collectors.toList());
    }

    private Predicate<Pet> filterBySpecie(String specie) {
        return pet -> pet.getSpecie().equals(specie);
    }

    private Predicate<Pet> filterByAge(Integer age) {
        return pet -> pet.getAge().equals(age);
    }

    public Optional<Pet> getById(Integer id) {
        return petRepository.findById(id);
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> delete(Integer id) {
        Optional<Pet> mayBePet = petRepository.findById(id);
        mayBePet.ifPresent(pet -> petRepository.delete(pet.getId()));
        return mayBePet;
    }

    @Transactional
    @Retryable(ObjectOptimisticLockingFailureException.class)
    public void prescribe(Integer petId,
                          String description,
                          String medicineName,
                          Integer quantity,
                          Integer timesPerDay) {
        Pet pet = petRepository.findById(petId).orElseThrow(RuntimeException::new);
        pet.getPrescriptions().add(new Prescription(description, LocalDate.now(), timesPerDay));
        petRepository.save(pet);
        storeService.decrement(medicineName, quantity);
    }

}
