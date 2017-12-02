package hillelee;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by JavaEE on 12/2/2017.
 */
@Data
@AllArgsConstructor
public class ErrorBody {
    private String message;
    private final Integer code = 400;
}
