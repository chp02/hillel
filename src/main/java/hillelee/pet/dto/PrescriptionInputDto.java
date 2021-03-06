package hillelee.pet.dto;

import hillelee.validator.LatinName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by JavaEE on 12/23/2017.
 */
@Data
@AllArgsConstructor
public class PrescriptionInputDto {

    private String description;
    private LocalDate start;
    @Range(min = 1, max = 12)
    @NotNull
    private Integer timesPerDay;
    @NotEmpty
    @LatinName
    private String medicineName;
    @NotNull
    @Min(1)
    private Integer quantity;

}
