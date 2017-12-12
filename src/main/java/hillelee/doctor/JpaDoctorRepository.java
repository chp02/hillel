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
            "(doctor.name = :name OR :name IS NULL) AND " +
            "(doctor.specialty = :specialty OR :specialty IS NULL)")
    List<Doctor> findByNameAndSpecialty(@Param("name") String name, @Param("specialty") String specialty);

}
