package hillelee.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * Created by JavaEE on 12/2/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class Pet {
    private Integer id;
    private String name;
    private String specie;
    private Integer age;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
