package hillelee.pet;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/22/2017.
 */
public class GreetingProviderTest
{
  @Test
  public void greetingRandomization() {
    GreetingProvider greetingProvider = new GreetingProvider();
    Map<String, Integer> gr = new HashMap<>();
    int poolSize = 100;
    String greeting;
    for (int i = 0; i < poolSize; i++) {
      greeting = greetingProvider.getRandomGreeting();
      gr.put(greeting, gr.containsKey(greeting) ? gr.get(greeting) + 1 : 1);
    }
    gr.entrySet().stream()
        .peek(e -> {
          System.out.println(calculateDeltaPercentage(e.getValue(), poolSize));
          Assert.assertTrue(calculateDeltaPercentage(e.getValue(), poolSize) <= 10);
        }).toArray();
  }
  
  private double calculateDeltaPercentage(Integer actual, Integer poolSize) {
    double expected = poolSize / 3;
    return (Math.abs(actual - expected) * 100.0) / expected;
  }
  
}