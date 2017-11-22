package hillelee.pet;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JavaEE on 11/18/2017.
 */
@RestController
@AllArgsConstructor
public class PetController {

    private final GreetingProvider greetingProvider;
    
    @GetMapping(value = "/greeting")
    public String helloWorld() {
        return greetingProvider.getRandomGreeting();
    }

}
