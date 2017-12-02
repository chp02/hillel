package hillelee;

import hillelee.pet.PetRepository;
import hillelee.pet.PetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JavaEE on 12/2/2017.
 */
@Configuration
public class HilleleeConfig {

    @Bean
    PetService petService(PetRepository petRepository) {
        return new PetService(petRepository);
    }

}
