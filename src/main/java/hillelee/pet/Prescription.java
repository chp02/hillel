package hillelee.pet;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by JavaEE on 12/16/2017.
 */
@Data
@NoArgsConstructor
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private LocalDate start;
    private Integer timesPerDay;
    @Enumerated(EnumType.STRING)
    private MedicineType medicineType;

    public Prescription(String description, LocalDate start, Integer timesPerDay, MedicineType medicineType) {
        this.description = description;
        this.start = start;
        this.timesPerDay = timesPerDay;
        this.medicineType = medicineType;
    }
}
