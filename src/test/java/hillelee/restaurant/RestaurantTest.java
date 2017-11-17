package hillelee.restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/17/2017.
 */
public class RestaurantTest
{
  private List<Dish> menu = new ArrayList<Dish>(){{
    add(new Dish("dish0", 3100, true, Dish.DishType.BEEF));
    add(new Dish("dish1", 3200, false, Dish.DishType.BEEF));
    add(new Dish("dish2", 3300, true, Dish.DishType.BEEF));
    add(new Dish("dish3", 3400, false, Dish.DishType.BEEF));
    add(new Dish("dish4", 2100, true, Dish.DishType.CHICKEN));
    add(new Dish("dish5", 2200, false, Dish.DishType.CHICKEN));
    add(new Dish("dish6", 2300, true, Dish.DishType.CHICKEN));
    add(new Dish("dish7", 2400, false, Dish.DishType.CHICKEN));
    add(new Dish("dish8", 1100, true, Dish.DishType.VEGETABLES));
    add(new Dish("dish9", 1200, false, Dish.DishType.VEGETABLES));
    add(new Dish("dishA", 1300, true, Dish.DishType.VEGETABLES));
    add(new Dish("dishB", 1400, false, Dish.DishType.VEGETABLES));
  }};
  private Restaurant restaurant = new Restaurant(menu);
  
  
  
  
  
}