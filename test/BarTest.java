import com.company.Bar;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BarTest {
    Bar bar = new Bar("someName", 1, 1, 10, 20);

    @Test
    public void testDegreesToRadians() {
        double excepted = 10 * Math.PI / 180;
        Assert.assertEquals("there is some mistake", excepted, bar.degreesToRadians(10), 0.0001);
    }

    @Test
    public void distanceInMetersToBarTest(){
     //TODO
    }

    @Test
    public void testGetFormattedWorkingTime(){
        String excepted = "10:00-20:00";
        Assert.assertEquals("there is some mistake", excepted, bar.getFormattedWorkingTime());
    }


}