package hillelee.apple;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by JavaEE on 28.10.2017.
 */
public class AppleSelectorTest {
    
    List<Apple> apples = ImmutableList.of(
        new Apple("RED", 100),
        new Apple("GREEN", 30),
        new Apple("RED", 56),
        new Apple("GREEN", 87),
        new Apple("RED", 23));

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

    @Test
    public void filterByPredicateColor() throws Exception {
        List<Apple> filtered = AppleSelector.filter(apples, new ColorPredicate());
        assertThat(filtered, hasSize(2));
    }
    
    @Test
    public void filterByPredicateWeight() throws Exception {
        List<Apple> filtered = AppleSelector.filter(apples, new WeightPredicate());
        assertThat(filtered, hasSize(3));
    }

    @Test
    public void filterByAnonymousPredicate() throws Exception {
        List<Apple> filtered = AppleSelector.filter(apples, new ApplePredicate() {
            @Override
            public Boolean test(Apple apple) {
                return apple.getWeight() > 56;
            }
        });
    }
    
    @Test
    public void filterByLambdaPredicateColor() throws Exception {
        String color = "GREEN";
        List<Apple> filtered = AppleSelector.filter(apples, apple -> apple.getColor().equals(color));
        assertThat(filtered, hasSize(2));
    }
    
    @Test
    public void filterByLambdaPredicateWeight() throws Exception {
        Integer weight = 50;
        List<Apple> filtered = AppleSelector.filter(apples, apple -> apple.getWeight() > weight);
        assertThat(filtered, hasSize(3));
    }

}