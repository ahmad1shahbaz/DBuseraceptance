package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.cucumber.java.bs.I;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    public static WebDriver driver;

    private Driver() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser=ConfigReader.getPropertiesValues("browser");
        switch (browser.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                break;
            case "safari":
                driver=new SafariDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver=new InternetExplorerDriver();
                break;
            case "headless":
                FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                FirefoxOptions options=new FirefoxOptions();
                 options.addArguments("--window-size=1920,1080");//optional
                 options.addArguments("disable-extensions");
                 options.setCapability("useAutomationExtension",false);
                 options.addArguments("--proxy-server='direct://'");
                 options.addArguments("--proxy-bypass-list=*");
                 options.addArguments("--start-maximized");
                 options.addArguments("--headless");
                 driver=new FirefoxDriver(options);
                 break;
            case "firefox":
            default:
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
        }
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
    public static void takeScreenshot(Scenario scenario){
        if(scenario.isFailed()){
            final  byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

    }
    public static void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}