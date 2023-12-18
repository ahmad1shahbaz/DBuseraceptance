package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.driver;

public class BrowserHelper {
    //wait.until the element is visible
    public static WebElement waitForVisibilityOfElement(WebElement element,int timeToWaitInSec){
        WebDriverWait wait=new WebDriverWait(driver,timeToWaitInSec);
        return  wait.until(ExpectedConditions.visibilityOf(element));
    }
    //wait until element is clickable and click on it
    public static WebElement waitUntilClickableAndClick(WebElement element,int timeToWaitInSec){
        WebDriverWait wait=new WebDriverWait(driver,timeToWaitInSec);
        WebElement clickable=wait.until(ExpectedConditions.elementToBeClickable(element));
        clickable.click();
        return clickable;
    }
    public static WebElement waitUntilElementPresent(String elementId,int timeToWaitInSec){
        Duration waitTime = Duration.ofSeconds(30);
        // Set the polling interval
        Duration pollingInterval = Duration.ofSeconds(2);
        Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingInterval).
                ignoring(Exception.class);
        return  wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
    }
    public static void scrollIntoView(WebElement element){
        JavascriptExecutor  js=(JavascriptExecutor) driver;
        js.executeScript(("arguments[0].scrollIntoView()"),element);
    }
    public static void clickElementWithTxt(WebDriver driver,String text){
        // Build a dynamic XPath to locate the element by its text content
        String xpath = "//button[contains(text(),'" + text + ")']";
        // Find the element using the XPath
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }
    public static void fillTextInput(WebElement element,String text){
        scrollIntoView(element);
        if(element.isDisplayed() && element.isEnabled()){
            element.clear();
            element.sendKeys(text);
        }else{
            System.out.println("Element is not interactable");
        }
    }
}
