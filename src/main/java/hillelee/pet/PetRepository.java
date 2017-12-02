package hillelee.pet;

import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by JavaEE on 12/2/2017.
 */
@Repository
public class PetRepository {

    private Map<Integer, Pet> pets = new HashMap<>();

    {
        Integer id = generateId();
        pets.put(id, new Pet(id,"Tom", "Cat", 3));
        id = generateId();
        pets.put(id, new Pet(id, "Jerry", "Mouse", 1));
    }

    public Collection<Pet> findAll() {
        return pets.values();
    }

    public Optional<Pet> findById(Integer id) {
        return Optional.ofNullable(pets.get(id));
    }

    public Pet save(Pet pet) {
        Integer id = generateId();
        if (pet.getId() == null) {
            pet.setId(id);
        }
        pets.put(id, pet);
        return pet;
    }

    private Integer generateId() {
        return new Random().nextInt(100000);
    }

    public Optional<Pet> delete(Integer id) {
        return Optional.ofNullable(pets.remove(id));
    }

}
