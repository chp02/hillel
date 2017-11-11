package hillelee.knight;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by JavaEE on 11/11/2017.
 */
public class FairyTail {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("hillelee");
        System.out.println(ctx.getBean("knight"));
    }
}

@Component
@Data
class Knight {
    private final Quest quest;
}

@Component
@Data
class Quest {
    private String task = "Kill the Dragon";
}
