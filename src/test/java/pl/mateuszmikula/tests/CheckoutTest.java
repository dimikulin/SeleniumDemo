package pl.mateuszmikula.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.mateuszmikula.models.Customer;
import pl.mateuszmikula.pages.HomePage;
import pl.mateuszmikula.pages.OrderDetailsPage;

public class CheckoutTest extends BaseTest {
    @Test
    public void checkoutTest() {
        Customer customer = new Customer();
        customer.setFirstName("Mateusz");
        customer.setLastName("Mikuła");
        customer.setCompany("BmB");
        customer.setCountry("Poland");
        customer.setStreetAdress("Cyprysowa");
        customer.setFlatNumber("25");
        customer.setPostCode("11-021");
        customer.setCity("Lublin");
        customer.setPhoneNumber("000111222");
        customer.setEmail("random@random.pl");
        OrderDetailsPage orderDetailsPage = new HomePage(driver)
                .openShopPage().openProduct("Java Selenium WebDriver")
                .addProductToCart()
                .viewCart()
                .openAddressDetails()
                .fillAddressDetails(customer, "Some random comment");

        Assert.assertEquals(orderDetailsPage.getOrderNotice().getText(), "Thank you. Your order has been received.");
        Assert.assertEquals(orderDetailsPage.getProductName().getText(), "Java Selenium WebDriver × 1");
    }

}

