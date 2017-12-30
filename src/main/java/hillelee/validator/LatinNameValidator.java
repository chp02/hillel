package hillelee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by JavaEE on 12/30/2017.
 */
public class LatinNameValidator implements ConstraintValidator<LatinName, String> {

    @Override
    public void initialize(LatinName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.contains("um") || value.contains("us");
    }

}
