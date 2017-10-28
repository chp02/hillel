package hillelee.apple;

import hillelee.defaultmethods.Fruit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Created by JavaEE on 28.10.2017.
 */
@Data
@AllArgsConstructor
public class Apple implements Fruit {
    private String color;
    private Integer weight;
}
