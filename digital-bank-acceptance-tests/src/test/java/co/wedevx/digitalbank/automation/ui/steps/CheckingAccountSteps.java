package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingInfo;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CheckingAccountSteps {
    WebDriver driver= Driver.getDriver();
    private LoginPage loginPage=new LoginPage(driver);
    private CreateCheckingPage createCheckingPage=new CreateCheckingPage(driver);
    private ViewCheckingAccountPage viewCheckingAccountPage=new ViewCheckingAccountPage(driver);
    @BeforeAll
    public static void setup(){
        WebDriverManager.firefoxdriver().setup();
    }
    @Before
    public void the_user_is_dbank_homepage(){
        driver.get("https://dbank-qa.wedevx.co/bank/login?logout");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @Given("user logged in as {string} {string}")
    public void user_logged_in_as(String username, String password) {
loginPage.login(username,password);

    }
    @When("the user creates new checking account with following data")
    public void the_user_creates_new_checking_account_with_following_data(List<NewCheckingInfo> checkingInfoList) {
       createCheckingPage.createCheking(checkingInfoList);
    }
    @  Then("the user should see the green {string} message")
    public void the_user_should_see_the_green_message(String expectedConfMessage) {
 WebElement newAccountConfAlertDiv=driver.findElement(By.id("new-account-conf-alert"));
 expectedConfMessage="Confirmation "+expectedConfMessage+"\n×";
 Assertions.assertEquals(expectedConfMessage,viewCheckingAccountPage.getActualCreateAccountConfirmationMessage());
    }
    @Then("user should see newly added account card")
    public void user_should_see_newly_added_account_card(List<AccountCard> accountCardList) {
     List<WebElement> allFirstRowDivs=driver.findElements(By.xpath("//div[@id='firstRow']/div"));
     Map<String,String> actualResultMap=viewCheckingAccountPage.getNewlyAddedCheckingAccountInfoMap();
     AccountCard expectedResult=accountCardList.get(0);
        Assertions.assertEquals(expectedResult.getAccountName(),actualResultMap.get("actualAccountName"));
        Assertions.assertEquals("Account: "+expectedResult.getAccountType(),actualResultMap.get("actualAccountType"));
        Assertions.assertEquals("Ownership: " +expectedResult.getOwnership(),actualResultMap.get("actualOwnership"));
        Assertions.assertEquals("Interest Rate: "+expectedResult.getInterestRate(),actualResultMap.get("actualInterestRate"));
        String expectedBalance=String.format("%.2f",expectedResult.getBalance());
        Assertions.assertEquals("Balance: $"+expectedBalance,actualResultMap.get("actualBalance"));
    }
}
