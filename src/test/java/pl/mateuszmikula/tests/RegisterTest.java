package pl.mateuszmikula.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.mateuszmikula.pages.HomePage;
import pl.mateuszmikula.pages.ProductListPage;

public class RegisterTest extends BaseTest {
    @Test
    public void registerUserTest() {
        ExtentTest test = extentReports.createTest("Register User Test");
        int random = (int) (Math.random() * 1000);
        WebElement dashboardLink = new HomePage(driver)
                .openMyAccountPage()
                .registerUserValidData("test" + random + "@testest.pl", "test" + random + "@testest.pl")
                .getDashboardLink();
        test.log(Status.PASS, "Register user passed");
        Assert.assertEquals(dashboardLink.getText(), "Dashboard");
        test.log(Status.PASS, "Assertions passed");
    }

    @Test
    public void registerUserWithSameEmailTest() {
        ExtentTest test = extentReports.createTest("Register User With Same Email Test");
        WebElement error = new HomePage(driver)
                .openMyAccountPage()
                .registerUserInvalidData("test@testest.pl", "test@testest.pl")
                .getError();
        test.log(Status.PASS, "Register user fail");

        Assert.assertTrue(error.getText().contains("An account is already registered with your email address. Please log in."));
        test.log(Status.PASS, "Assertions passed");
    }
}

