package hillelee;

import hillelee.doctor.Doctor;
import hillelee.doctor.JpaDoctorRepository;
import hillelee.pet.JpaPetRepository;
import hillelee.pet.Pet;
import hillelee.pet.PetService;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by JavaEE on 12/2/2017.
 */
@Configuration
@ConfigurationProperties("clinic-info")
@Data
public class HilleleeConfig {
    
    private List<String> specialties;
    
    @Bean
    PetService petService(JpaPetRepository petRepository) {
        return new PetService(petRepository);
    }

    @Bean
    CommandLineRunner initPets(JpaPetRepository repository) {
        return args -> {
            if (!repository.findAll().isEmpty()) return;
            repository.save(new Pet("Tom", "Cat", 3));
            repository.save(new Pet("Jerry", "Mouse", 1));
        };
    }
    
    @Bean
    CommandLineRunner initDoctors(JpaDoctorRepository repository) {
        return args -> {
            if (!repository.findAll().isEmpty()) return;
            repository.save(new Doctor( "Komarovsky", "urology"));
            repository.save(new Doctor("Shutko", "surgery"));
            repository.save(new Doctor("Malahov", "cardiology"));
            repository.save(new Doctor("Stupka", "surgery"));
        };
    }

}
