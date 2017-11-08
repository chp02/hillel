package hillelee.apple;

import hillelee.defaultmethods.Fruit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by JavaEE on 28.10.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple implements Fruit {
    private String color;
    private Integer weight;
}
