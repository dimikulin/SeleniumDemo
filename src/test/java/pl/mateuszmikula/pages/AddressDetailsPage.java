package pl.mateuszmikula.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.mateuszmikula.models.Customer;

public class AddressDetailsPage {

    @FindBy(id="billing_first_name")
    WebElement firstNameInput;

    @FindBy(id="billing_last_name")
    WebElement lastNameInput;

    @FindBy(id="billing_company")
    WebElement companyInput;

    @FindBy(id="billing_country")
    WebElement billingCountrySelect;

    @FindBy(id="billing_address_1")
    WebElement streetAdressInput;

    @FindBy(id="billing_address_2")
    WebElement apartmentInput;

    @FindBy(id="billing_postcode")
    WebElement postCodeInput;

    @FindBy(id="billing_city")
    WebElement cityInput;

    @FindBy(id="billing_phone")
    WebElement phoneNumberInput;

    @FindBy(id="billing_email")
    WebElement emailInput;

    @FindBy(id="order_comments")
    WebElement orderNotesInput;

    @FindBy(xpath = "//button[text()='Place order']")
    WebElement placeOrderButton;

    private WebDriver driver;

    public AddressDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public OrderDetailsPage fillAddressDetails(Customer customer, String comments){

        firstNameInput.sendKeys(customer.getFirstName());
        lastNameInput.sendKeys(customer.getLastName());
        companyInput.sendKeys(customer.getCompany());
        Select countrySelect = new Select(billingCountrySelect);
        countrySelect.selectByVisibleText(customer.getCountry());
        streetAdressInput.sendKeys(String.format("%s %s", customer.getStreetAdress(),customer.getFlatNumber()));
        postCodeInput.sendKeys(customer.getPostCode());
        cityInput.sendKeys(customer.getCity());
        phoneNumberInput.sendKeys(customer.getPhoneNumber());
        emailInput.sendKeys(customer.getEmail());
        orderNotesInput.sendKeys(comments);

        boolean staleElement = true;

        while(staleElement) {

            try {
                placeOrderButton.click();
                staleElement = false;

            } catch (StaleElementReferenceException e) {

                staleElement = true;

            }
        }

        return new OrderDetailsPage(driver);

    }
}
