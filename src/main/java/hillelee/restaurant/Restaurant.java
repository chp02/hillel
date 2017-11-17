package hillelee.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
class Restaurant
{
  private List<Dish> menu;
  
  public List<Dish> selectDishesLowCalorie() {
    List<Dish> list = new ArrayList<>();
    for (Dish dish : menu) if (dish.getCalories() < 2300) list.add(dish);
    return list;
  }
  
  public List<Dish> selectDishesTop3HighCalorie() {
    List<Dish> list = new ArrayList<>();
    for (int i = 0; i < menu.size(); i++) {
      if (menu.get(i).getCalories() < 2300) {
        list.add(menu.get(i));
        if (list.size() == 3) break;
      }
    }
    return list;
  }
  
  public List<Dish> sortByBioThenByAlphabet() {
    List<Dish> list = new ArrayList<>();
    return list;
  }
  
  public Map<Dish.DishType, Double> countAverageCalorieByType() {
    Map<Dish.DishType, Double> map = new HashMap<>();
    return map;
  }
  
  public Map<Dish.DishType, List<String>> groupBioDishesByType() {
    Map<Dish.DishType, List<String>> map = new HashMap<>();
    return map;
  }
  
}
