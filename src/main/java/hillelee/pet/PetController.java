package hillelee.pet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JavaEE on 11/18/2017.
 */
@RestController
public class PetController {

    @GetMapping(value = "/greeting")
    public String helloWorld() {
        return "Hello World!";
    }

}
