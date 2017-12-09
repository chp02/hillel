package hillelee;

import hillelee.pet.JpaPetRepository;
import hillelee.pet.Pet;
import hillelee.pet.PetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JavaEE on 12/2/2017.
 */
@Configuration
public class HilleleeConfig {

    @Bean
    PetService petService(JpaPetRepository petRepository) {
        return new PetService(petRepository);
    }

    @Bean
    CommandLineRunner initDb(JpaPetRepository repository) {
        return args -> {
            if (!repository.findAll().isEmpty()) return;
            repository.save(new Pet("Tom", "Cat", 3));
            repository.save(new Pet("Jerry", "Mouse", 1));
        };
    }

}
