package hillelee.pet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JavaEE on 11/18/2017.
 */
@RestController
public class PetController {

    private final GreetingProvider greetingProvider;
    
    public PetController(GreetingProvider greetingProvider) {
        this.greetingProvider = greetingProvider;
    }
    
    @GetMapping(value = "/greeting")
    public String helloWorld() {
        return greetingProvider.getRandomGreeting();
    }

}
