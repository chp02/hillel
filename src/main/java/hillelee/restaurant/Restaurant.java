package hillelee.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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
    List<Dish> list = sortMenuByCalorie(menu);
    return list.subList(0, 3);
  }

  private List<Dish> sortMenuByCalorie(List<Dish> menu) {
    Collections.sort(menu, new Comparator<Dish>() {
      @Override
      public int compare(Dish o1, Dish o2) {
        return o2.getCalories() - o1.getCalories();
      }
    });
    return menu;
  }

  private List<Dish> sortMenuByAlphabet(List<Dish> menu) {
    Collections.sort(menu, new Comparator<Dish>() {
      @Override
      public int compare(Dish o1, Dish o2) {
        return o1.getName().compareTo(o2.getName());
      }
    });
    return menu;
  }

  private List<Dish> selectBioDishes() {
    List<Dish> list = new ArrayList<>();
    for (Dish dish : menu) if (dish.getIsBio()) list.add(dish);
    return list;
  }

  private List<Dish> selectNotBioDishes() {
    List<Dish> list = new ArrayList<>();
    for (Dish dish : menu) if (!dish.getIsBio()) list.add(dish);
    return list;
  }

  public Map<Dish.DishType, List<Dish>> groupDishesByType() {
    Map<Dish.DishType, List<Dish>> map = new HashMap<>();
    for (Dish.DishType type : Dish.DishType.values()) {
      map.put(type, new ArrayList<>());
    }
    for (Dish dish : menu) {
      map.get(dish.getType()).add(dish);
    }
    return map;
  }

  public List<Dish> sortByBioThenByAlphabet() {
    List<Dish> list = new ArrayList<>();
    List<Dish> listBio = selectBioDishes();
    List<Dish> listNotBio = selectNotBioDishes();
    list.addAll(sortMenuByAlphabet(listBio));
    list.addAll(sortMenuByAlphabet(listNotBio));
    return list;
  }
  
  public Map<Dish.DishType, Double> countAverageCalorieByType() {
    Map<Dish.DishType, Double> map = new HashMap<>();
    Map<Dish.DishType, List<Dish>> groupedByType = groupDishesByType();
    for (Map.Entry e : groupedByType.entrySet()) {
      Double sum = 0.0;
      Dish.DishType type = (Dish.DishType) e.getKey();
      List<Dish> list = (List<Dish>) e.getValue();
      for (Dish dish : list) {
        sum += dish.getCalories();
      }
      map.put(type, sum / list.size());
    }
    return map;
  }
  
  public Map<Dish.DishType, List<String>> groupBioDishesByType() {
    return new HashMap<>();
  }
  
}
