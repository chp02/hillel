package hillelee;

import hillelee.doctor.Doctor;
import hillelee.doctor.JpaDoctorRepository;
import hillelee.pet.*;
import hillelee.store.Medicine;
import hillelee.store.MedicineRepository;
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
            List<String> spec1 = new ArrayList<>();
            spec1.add("urology");
            spec1.add("surgery");
            
            List<String> spec2 = new ArrayList<>();
            spec2.add("cardiology");
            spec2.add("pulmonology");
            
            List<String> spec3 = new ArrayList<>();
            spec3.add("surgery");
            spec3.add("neurology");
            
            if (!repository.findAll().isEmpty()) return;
            repository.save(new Doctor( "Komarovsky", spec1));
            repository.save(new Doctor("Shutko", spec2));
            repository.save(new Doctor("Malahov", spec3));
            repository.save(new Doctor("Stupka", spec1));
        };
    }

    @Bean
    CommandLineRunner initMedicineStore(MedicineRepository repository) {
        return args -> {
            repository.save(new Medicine("Brilliant green", 1));
        };
    }

}
