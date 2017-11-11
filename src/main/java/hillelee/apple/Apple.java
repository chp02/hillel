package hillelee.apple;

import com.google.common.collect.ImmutableList;
import hillelee.defaultmethods.Fruit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by JavaEE on 28.10.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple implements Fruit {
    private String color;
    private Integer weight;
    private List<String> worms;
}
