package hillelee.pet;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/22/2017.
 */
@Component
class GreetingProvider
{
  private String[] greetings = {
      "hello world",
      "hola world",
      "bonjour world"
  };
  
  String getRandomGreeting() {
    return greetings[new Random().nextInt(3)];
  }
  
}
