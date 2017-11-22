package hillelee.pet;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/22/2017.
 */
public class GreetingProviderTest
{
  @Test
  public void greetingRandomization() {
    GreetingProvider greetingProvider = new GreetingProvider();
    int maxDelta = 10;
    int poolSize = 1000;
    Stream.generate(greetingProvider::getRandomGreeting)
            .limit(poolSize)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .values().stream()
            .peek(System.out::println)
            .forEach(v -> Assert.assertTrue(calculateDelta(v, poolSize) <= maxDelta));
  }
  
  private double calculateDelta(Long actual, Integer poolSize) {
    double expected = poolSize / 3;
    return (Math.abs(actual - expected) * 100.0) / expected;
  }
  
}