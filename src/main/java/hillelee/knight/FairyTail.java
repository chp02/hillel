package hillelee.knight;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by JavaEE on 11/11/2017.
 */
public class FairyTail {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("hillelee");
        Knight knight1 = ctx.getBean(Knight.class);
        Knight knight2 = ctx.getBean(Knight.class);
        System.out.println(knight1);
        System.out.println(knight1);
        System.out.println(knight1 == knight2);
    }
}

@Configuration
class Config {
    @Bean
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Knight knight(/*@Qualifier("quest")*/ Quest quest) {
        return new Knight(quest);
    }

    @Bean
    @Primary
    Quest killDragon() {
        return new Quest("Kill the Dragon");
    }

    @Bean
    Quest rescuePrincess() {
        return new Quest("Rescue the Princess");
    }

    @Bean
    String task() {
        return "do nothing";
    }
}

//@Component
@Data
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class Knight {
    private final Quest quest;
}

@Component
@Data
class Quest {
    //private String task = "Kill the Dragon";
    private final String task;
}
