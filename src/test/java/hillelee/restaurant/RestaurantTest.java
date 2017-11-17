package hillelee.restaurant;

import org.junit.Test;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/17/2017.
 */
public class RestaurantTest
{
  private List<Dish> menu = new ArrayList<Dish>(){{
    add(new Dish("dishD", 3100, true, Dish.DishType.BEEF));
    add(new Dish("dishO", 3200, false, Dish.DishType.BEEF));
    add(new Dish("dishC", 3300, true, Dish.DishType.BEEF));
    add(new Dish("dishH", 3400, false, Dish.DishType.BEEF));
    add(new Dish("dishU", 2100, true, Dish.DishType.CHICKEN));
    add(new Dish("dishN", 2200, false, Dish.DishType.CHICKEN));
    add(new Dish("dishI", 2300, true, Dish.DishType.CHICKEN));
    add(new Dish("dishW", 2400, false, Dish.DishType.CHICKEN));
    add(new Dish("dishK", 1100, true, Dish.DishType.VEGETABLES));
    add(new Dish("dishA", 1200, false, Dish.DishType.VEGETABLES));
    add(new Dish("dishL", 1300, true, Dish.DishType.VEGETABLES));
    add(new Dish("dishP", 1400, false, Dish.DishType.VEGETABLES));
  }};
  //private Restaurant restaurant = new Restaurant(menu);
  private RestaurantStream restaurant = new RestaurantStream(menu);

  @Test
  public void selectDishesLowCalorie() {
    List<Dish> list = restaurant.selectDishesLowCalorie();
    System.out.println(list);
    assertThat(list, hasSize(6));
  }

  @Test
  public void selectDishesTop3HighCalorie() {
    List<Dish> list = restaurant.selectDishesTop3HighCalorie();
    System.out.println(list);
    assertThat(list, hasSize(3));
  }

  @Test
  public void sortByBioThenByAlphabet() {
    List<Dish> list = restaurant.sortByBioThenByAlphabet();
    for (Dish dish : list) System.out.println(dish);
  }

  @Test
  public void countAverageCalorieByType() {
    System.out.println(restaurant.countAverageCalorieByType());
  }

  @Test
  public void groupBioDishesByType() {
    System.out.println(restaurant.groupBioDishesByType());
  }

}