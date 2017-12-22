package hillelee.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by JavaEE on 12/9/2017.
 */
public interface JpaDoctorRepository extends JpaRepository<Doctor, Integer> {
    
    @Query("SELECT DISTINCT doctor FROM Doctor doctor JOIN doctor.specialties s WHERE " +
        "(s IN :specialties OR :specialties IS NULL) AND " +
        "(LOWER(doctor.name) LIKE :name% OR :name IS NULL)")
    List<Doctor> findByNameAndSpecialty(@Param("name") String name, @Param("specialties") List<String> specialties);
    
    
}
