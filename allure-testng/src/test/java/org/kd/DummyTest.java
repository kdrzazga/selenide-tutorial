package org.kd;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DummyTest {

    @Severity(SeverityLevel.NORMAL)
    @Story("JIRA-1")
    @Description("Empty first test")
    @Test()
    public void testToBeReported() {
        System.out.println("first");
        Assert.assertTrue(true);
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("JIRA-2")
    @Description("Second test")
    @Test(priority = 1)
    public void testToBeReported2() {
        System.out.println("2nd");
        Assert.assertTrue(true);
    }

}
