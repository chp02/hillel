package hillelee.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by JavaEE on 12/9/2017.
 */
public interface JpaDoctorRepository extends JpaRepository<Doctor, Integer> {
    
    @Query("SELECT doctor FROM Doctor AS doctor WHERE " +
        "(LOWER(doctor.name) LIKE :name% OR :name IS NULL) AND " +
        "(doctor.specialties IN :specialties OR :specialties IS NULL)")
    List<Doctor> findByNameAndSpecialty(@Param("name") String name, @Param("specialties") List<String> specialties);
    
//    @Query("SELECT doctor FROM Doctor AS doctor WHERE " +
//        "(LOWER(doctor.name) LIKE :name% OR :name IS NULL) AND :specialties IS NULL)")
//    List<Doctor> findByNameAndSpecialty(@Param("name") String name, @Param("specialties") List<String> specialties);
    
    @Query("SELECT doctor.specialties FROM Doctor AS doctor WHERE doctor.id = :id)")
    List<String> getSpecialties(@Param("id") Integer id);
}
