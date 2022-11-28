package pl.mateuszmikula.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.mateuszmikula.pages.HomePage;

public class LoginTest extends BaseTest {
    @Test
    public void loginTest() {
        WebElement dashboardLink = new HomePage(driver)
                .openMyAccountPage()
                .loginUserValidData("test@testest.pl", "test@testest.pl")
                .getDashboardLink();

        Assert.assertEquals(dashboardLink.getText(), "Dashboard");
    }

    @Test
    public void loginWithInvalidPasswordTest() {
        WebElement error = new HomePage(driver)
                .openMyAccountPage()
                .loginUserInvalidData("test@testest.pl", "123")
                .getError();

        Assert.assertTrue(error.getText().contains("Incorrect username or password."), "Expected error text doesn't match");
    }
}

