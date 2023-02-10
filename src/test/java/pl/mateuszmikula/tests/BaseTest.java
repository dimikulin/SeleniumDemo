package pl.mateuszmikula.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.mateuszmikula.utils.DriverFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    protected static ExtentHtmlReporter htmlReporter;

    protected static ExtentReports extentReports;

    @BeforeSuite
    public void beforeSuit(){
        htmlReporter = new ExtentHtmlReporter("raport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @AfterSuite
    public void afterSuit(){
        htmlReporter.flush();
        extentReports.flush();
    }

    @BeforeMethod
    public void setup() {
        this.driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.seleniumdemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
