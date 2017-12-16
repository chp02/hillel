package hillelee;

import hillelee.doctor.Doctor;
import hillelee.doctor.JpaDoctorRepository;
import hillelee.pet.*;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
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
            List<Prescription> tomsPrescriptions = new ArrayList<>();
            tomsPrescriptions.add(new Prescription("Paracetamol", LocalDate.now(), 3));
            tomsPrescriptions.add(new Prescription("Briputron", LocalDate.now(), 2));

            List<Prescription> jerrysPrescriptions = new ArrayList<>();
            jerrysPrescriptions.add(new Prescription("Fortulit", LocalDate.now(), 4));
            jerrysPrescriptions.add(new Prescription("Pekalin", LocalDate.now(), 3));

            if (!repository.findAll().isEmpty()) return;
            MedicalCard medicalCardTom = new MedicalCard(LocalDate.now(), "bububu");
            repository.save(new Pet("Tom", "Cat", 3, LocalDate.now(), medicalCardTom, tomsPrescriptions));
            MedicalCard medicalCardJerry = new MedicalCard(LocalDate.now(), "bebebe");
            repository.save(new Pet("Jerry", "Mouse", 1, LocalDate.now(), medicalCardJerry, jerrysPrescriptions));
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
