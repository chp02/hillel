package hillelee.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * Created by JavaEE on 12/23/2017.
 */
@Data
@AllArgsConstructor
public class PrescriptionInputDto {

    private String description;
    private LocalDate start;
    private Integer timesPerDay;
    private String medicineName;
    private Integer quantity;

}
