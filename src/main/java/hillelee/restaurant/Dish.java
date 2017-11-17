package hillelee.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/17/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Dish
{
  private String name;
  private Integer calories;
  private Boolean isBio;
  private DishType type;
  
  enum DishType {
    BEEF,
    CHICKEN,
    VEGETABLES
  }
  
  
}
