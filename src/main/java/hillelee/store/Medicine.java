package hillelee.store;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by JavaEE on 12/23/2017.
 */
@Data
@NoArgsConstructor
@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Version
    private Integer version;
    String name;
    Integer quantity;

    public Medicine(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
