package hillelee.apple;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by JavaEE on 28.10.2017.
 */
public class AppleSelectorTest {

    List<String> worms = ImmutableList.of("worm1", "worm2", "worm3");
    List<Apple> apples = ImmutableList.of(
        new Apple("RED", 100, worms),
        new Apple("GREEN", 30,worms),
        new Apple("RED", 56, worms),
        new Apple("GREEN", 87,worms),
        new Apple("RED", 23,worms));
    
    Integer weightToFilter = 50;
    String colorToFilter = "GREEN";

    @Test
    public void selectHeaviest() throws Exception {
        Optional<Apple> mayBeHeaviest = AppleSelector.getHeaviest(apples);
        if (mayBeHeaviest.isPresent()) {
            Apple heaviest = mayBeHeaviest.get();
            assertThat(heaviest.getWeight(), is(100));
        } else {
            fail();
        }
    }

    @Test
    public void selectHeaviestFromEmptyList() throws Exception {
        List<Apple> apples = ImmutableList.of();
        Optional<Apple> mayBeHeaviest = AppleSelector.getHeaviest(apples);
        if (mayBeHeaviest.isPresent()) {
            fail();
        }
    }

    @Test
    public void sort() throws Exception {
        apples = new ArrayList<>(apples);
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
    }

    @Test
    public void mapDefault() throws Exception {
        Map<String, Integer> nameToSalary = ImmutableMap.of("Ivan", 200);
        Integer salary = nameToSalary.getOrDefault("Stepan", 0);
    }

//    @Test
//    public void filterByPredicateColor() throws Exception {
//        List<Apple> filtered = AppleSelector.filter(apples, new ColorPredicate());
//        assertThat(filtered, hasSize(2));
//    }
//
//    @Test
//    public void filterByPredicateWeight() throws Exception {
//        List<Apple> filtered = AppleSelector.filter(apples, new WeightPredicate());
//        assertThat(filtered, hasSize(3));
//    }
//
//    @Test
//    public void filterByAnonymousPredicate() throws Exception {
//        List<Apple> filtered = AppleSelector.filter(apples, new ApplePredicate() {
//            @Override
//            public Boolean test(Apple apple) {
//                return apple.getWeight() > 56;
//            }
//        });
//    }
    
    @Test
    public void filterByLambdaPredicateColor() throws Exception {
        List<Apple> filtered = AppleSelector.filter(apples, apple -> apple.getColor().equals(colorToFilter));
        assertThat(filtered, hasSize(2));
    }
    
    @Test
    public void filterByLambdaPredicateWeight() throws Exception {
        List<Apple> filtered = AppleSelector.filter(apples, apple -> apple.getWeight() > weightToFilter);
        assertThat(filtered, hasSize(3));
    }

    @Test
    public void filterByStreamPredicateWeight() throws Exception {
        List<Apple> filtered = apples.stream()
                .filter(apple -> apple.getWeight() > weightToFilter)
                .collect(Collectors.toList());
        assertThat(filtered, hasSize(3));
    }

    @Test
    public void mapToColor() throws Exception {
        List<String> colors = apples.stream()
                                    .map(Apple::getColor)
                                    .collect(Collectors.toList());
        assertThat(colors, hasSize(5));
        assertThat(colors.get(0), is("RED"));
    }

    @Test
    public void printApples() throws Exception {
        apples.forEach(System.out::println);
    }

    @Test
    public void composeFunctions() throws Exception {
        Predicate<Apple> heavierThan50 = apple -> apple.getWeight() > 50;
        Predicate<Apple> isRed = apple -> apple.getColor().equals("RED");
        Predicate<Apple> heavyAndRed = isRed.and(heavierThan50);
        List<Apple> filtered = apples.stream()
                .filter(heavyAndRed)
                .collect(Collectors.toList());
        assertThat(filtered, hasSize(2));
    }

    @Test
    public void executionFlow() throws Exception {
        apples.stream()
                .filter(apple -> apple.getWeight() > 50)
                .map(Apple::getColor)
                .peek(System.out::println)
                .limit(2)
                .collect(Collectors.toList());
    }

    @Test
    public void findFirst() throws Exception {
        apples.stream()
                .filter(apple -> apple.getWeight() > 50)
                .findFirst()
                .map(Apple::getColor)
                .ifPresent(System.out::println);
    }

    @Test
    public void intStream() throws Exception {
        LongSummaryStatistics lss = apples.stream()
                .mapToLong(Apple::getWeight)
                .summaryStatistics();
        System.out.println(lss);
    }

    @Test
    public void intStream2() throws Exception {
        IntSummaryStatistics lss = apples.stream()
                .map(Apple::getWeight)
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println(lss);
    }

    @Test
    public void weightToApple() throws Exception {
        Map<Integer, Apple> weightToApple = apples.stream()
                .collect(Collectors.toMap(Apple::getWeight, Function.identity()));
        System.out.println(weightToApple);
        assertThat(weightToApple.get(100), is(apples.get(0)));
    }

    @Test
    public void groupByColor() throws Exception {
        Map<String, List<Apple>> grouped = apples.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.toList()));
        System.out.println(grouped);
    }

    @Test
    public void getAllWorms() throws Exception {
        apples.stream()
                .map(Apple::getWorms)
                .flatMap(List::stream)
                .forEach(System.out::println);
    }

    @Test
    public void test() throws Exception {

    }

}