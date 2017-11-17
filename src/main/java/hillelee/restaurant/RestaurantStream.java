package hillelee.restaurant;

import jdk.internal.org.objectweb.asm.Handle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/17/2017.
 *
 1. Вывести названия блюд:
 - только низкокаллорийных
 - топ-3 самых питательных
 - всех, но отсортированных сначала по БИО, потом по алфавиту
 2. Посчитать среднюю калорийность по группам: говядина, курица, овощи (Map<DishType, Double>)
 3. Сгрупировать в Map<DishType, List<String>> БИО блюда
 4. Класс hillelee.reflection.ProblemSolver переделать на использование Stram API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class RestaurantStream
{
  private List<Dish> menu;
  
  public List<Dish> selectDishesLowCalorie() {
    return menu.stream()
            .filter(dish -> dish.getCalories() < 2300)
            .collect(Collectors.toList());
  }
  
  public List<Dish> selectDishesTop3HighCalorie() {
    return menu.stream()
            .sorted((o1, o2) -> o2.getCalories() - o1.getCalories())
            .limit(3)
            .collect(Collectors.toList());
  }

  public List<Dish> sortByBioThenByAlphabet() {
    return menu.stream()
            .filter(Dish::getIsBio)
            .sorted((Comparator.comparing(Dish::getName)))
            .collect(Collectors.toList());
  }
  
  public Map<Dish.DishType, Double> countAverageCalorieByType() {
    return menu.stream()
            .collect(Collectors.groupingBy(
                    Dish::getType, Collectors.averagingDouble(Dish::getCalories)));
  }
  
  public Map<Dish.DishType, List<String>> groupBioDishesByType() {
    return menu.stream()
            .filter(Dish::getIsBio)
            .collect(Collectors.groupingBy(Dish::getType,
                    Collectors.mapping(Dish::getName, Collectors.toList())));
  }
  
}
