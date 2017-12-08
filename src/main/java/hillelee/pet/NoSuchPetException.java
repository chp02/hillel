package hillelee.pet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by JavaEE on 12/2/2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
class NoSuchPetException extends RuntimeException {

}
