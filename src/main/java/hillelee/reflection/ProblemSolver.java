package hillelee.reflection;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by JavaEE on 28.10.2017.
 */
public class ProblemSolver {
    //@SneakyThrows
    public String solve(Object problem) {
        return Stream.of(problem)
                .map(Object::getClass)
                .flatMap(clazz -> Arrays.stream(clazz.getMethods()))
                .filter(method -> method.isAnnotationPresent(CorrectAnswer.class))
                .map(invokeOn(problem))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no CorrectAnswer annotation"));

//        Class<?> aClass = problem.getClass();
//        Method[] methods = aClass.getMethods();
//        for (Method method : methods) {
//            if(method.isAnnotationPresent(CorrectAnswer.class)) {
//                try {
//                    return (String) method.invoke(problem);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//        throw new RuntimeException("There is no CorrectAnswer annotation");
    }

    private Function<Method, String> invokeOn(Object object) {
        return method -> {
            try {
                return (String) method.invoke(object);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}
