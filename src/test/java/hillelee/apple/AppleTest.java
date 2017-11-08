package hillelee.apple;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by JavaEE on 28.10.2017.
 */
public class AppleTest {

    @Test
    public void getterTest() {
        String color = new Apple("RED", 33).getColor();
        Assert.assertEquals(color, "RED");
    }

}