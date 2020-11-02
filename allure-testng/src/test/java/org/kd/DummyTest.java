package org.kd;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DummyTest {

    @Test()
    public void testToBeReported() {
        System.out.println("first");
        Assert.assertTrue(true);
    }

    @Test(priority = 1)
    public void testToBeReported2() {
        System.out.println("2nd");
        Assert.assertTrue(true);
    }

}
