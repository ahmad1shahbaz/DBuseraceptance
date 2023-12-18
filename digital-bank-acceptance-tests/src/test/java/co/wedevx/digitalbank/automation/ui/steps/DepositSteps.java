package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingInfo;
import co.wedevx.digitalbank.automation.ui.models.TransactionTable;
import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.driver;
import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class DepositSteps {
    RegistrationPage registrationPage=new RegistrationPage(getDriver());
    @Given("user is on signs up with following data on digital bank")
    public void user_is_on_signs_up_with_following_data_on_digital_bank(List<Map<String,String>> testData) {
  getDriver().get(ConfigReader.getPropertiesValues("registration.url"));
  String registeredPassword=registrationPage.password(testData);
 registrationPage.registerFirstPage(testData);
    }

    @When("user logs in with registered email and password {string}")
    public void user_logs_in_with_registered_email_and_password(String pwd) {

            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(pwd);
            WebElement signIn = driver.findElement(By.id("submit"));
            signIn.click();
    }
    @When("the user creates new checking account for registered user with following data")
    public void the_user_creates_new_checking_account_for_registered_user_with_following_data(List<NewCheckingInfo> checkingInfoList) {
        NewCheckingInfo testDataForCheckingAccount=checkingInfoList.get(0);
        WebElement checkingMenu=driver.findElement(By.id("checking-menu"));
        checkingMenu.click();
        WebElement newCheckingBtn=driver.findElement(By.id("new-checking-menu-item"));
        newCheckingBtn.click();
        WebElement accountTypeRadioBtn=driver.findElement(By.id(testDataForCheckingAccount.getCheckingAccountType()));
        accountTypeRadioBtn.click();
        WebElement accountOwnership=driver.findElement(By.id(testDataForCheckingAccount.getAccountOwnerShip()));
        accountOwnership.click();
        WebElement accountName=driver.findElement(By.id("name"));
        accountName.sendKeys(testDataForCheckingAccount.getAccountName());
        WebElement initialDeposit=driver.findElement(By.id("openingBalance"));
        initialDeposit.sendKeys(String.valueOf(testDataForCheckingAccount.getInitialDepositAmount()));



        WebElement submit=driver.findElement(By.id("newCheckingSubmit"));
        submit.click();

    }
    @When("user should go deposit page and choose account and deposit income")
    public void user_should_go_deposit_page_and_choose_account_and_deposit_income(List<Map<String,String>> list) {
        Map<String,String> data= list.get(0);
        WebElement deposit=driver.findElement(By.id("deposit-menu-item"));
        deposit.click();
        WebElement selectedAccount=driver.findElement(By.id("selectedAccount"));
        Select select=new Select(selectedAccount);
        select.selectByVisibleText(data.get("accountNameAndType"));
        WebElement amount=driver.findElement(By.id("amount"));
        amount.sendKeys(data.get("amount"));
WebElement submit=driver.findElement(By.xpath("//div[@class='card-footer']//button[@type='submit']"));
submit.click();
    }
    @Then("user should validate view checking with following data")
    public void user_should_validate_view_checking_with_following_data(List<TransactionTable> transactionTables) {
        TransactionTable expectedTransactionTable=transactionTables.get(0);
        String expectedCategory=expectedTransactionTable.getCategory();
        String expectedOperationType=expectedTransactionTable.getOperationType();
        String expectedDescription=expectedTransactionTable.getDescription();
      WebElement checking=driver.findElement(By.id("checking-menu"));
      checking.click();
      WebElement viewChecking=driver.findElement(By.id("view-checking-menu-item"));
      viewChecking.click();
      String actualCategory=driver.findElement(By.xpath("//div[@class='col-sm-12']//td[2]")).getText();
      String actualDescription=driver.findElement(By.xpath("//div[@class='col-sm-12']//td[3]")).getText();
      String actualDescription1=actualDescription.substring(actualDescription.indexOf(" ")+1);
      String actualOperationType=actualDescription.substring(actualDescription.indexOf("-")+2);
   Assertions.assertEquals(expectedCategory,actualCategory);
   Assertions.assertEquals(expectedDescription,actualDescription1);
   Assertions.assertEquals(expectedOperationType,actualOperationType);
    }



    @When("user should go deposit page and choose  and deposit {int}")
    public void user_should_go_deposit_page_and_choose_and_deposit(Integer int1, List<Map<String,String>> list) {
        Map<String,String> data= list.get(0);
        WebElement deposit=driver.findElement(By.id("deposit-menu-item"));
        deposit.click();
        WebElement selectedAccount=driver.findElement(By.id("selectedAccount"));
        Select select=new Select(selectedAccount);
        if(data.get("accountNameAndType")!=null) {
            select.selectByVisibleText(data.get("accountNameAndType"));
        }
        WebElement amount=driver.findElement(By.id("amount"));
        amount.sendKeys(data.get("amount"));
        WebElement submit=driver.findElement(By.xpath("//div[@class='card-footer']//button[@type='submit']"));
        submit.click();
        String actualErrorMsgAccountSelect=selectedAccount.getAttribute("validationMessage");
        Assertions.assertEquals("Please select an item in the list.",actualErrorMsgAccountSelect);
    }
    @When("user should go deposit page and choose Vader \\(Standard Checking) and deposit ")
    public void user_should_go_deposit_page_and_choose_vader_standard_checking_and_deposit(List<Map<String,String>> list) {
        Map<String,String> data= list.get(0);
        WebElement deposit=driver.findElement(By.id("deposit-menu-item"));
        deposit.click();
        WebElement selectedAccount=driver.findElement(By.id("selectedAccount"));
        Select select=new Select(selectedAccount);
        if(data.get("accountNameAndType")!=null) {
            select.selectByVisibleText(data.get("accountNameAndType"));
        }
        WebElement amount=driver.findElement(By.id("amount"));
        if(data.get("amount")!=null) {
            amount.sendKeys(data.get("amount"));
        }
        WebElement submit=driver.findElement(By.xpath("//div[@class='card-footer']//button[@type='submit']"));
        submit.click();
        String actualErrorMsgAccountAmount=amount.getAttribute("validationMessage");
        Assertions.assertEquals("Please fill out this field.",actualErrorMsgAccountAmount);
    }
    @When("user should go deposit page and choose Vader \\(Standard Checking) and deposit {int}")
    public void user_should_go_deposit_page_and_choose_vader_standard_checking_and_deposit(Integer int1, List<Map<String,String>> list) {
        Map<String,String> data= list.get(0);
        WebElement deposit=driver.findElement(By.id("deposit-menu-item"));
        deposit.click();
        WebElement selectedAccount=driver.findElement(By.id("selectedAccount"));
        Select select=new Select(selectedAccount);
        if(data.get("accountNameAndType")!=null) {
            select.selectByVisibleText(data.get("accountNameAndType"));
        }
        WebElement amount=driver.findElement(By.id("amount"));
        if(data.get("amount")!=null) {
            amount.sendKeys(data.get("amount"));
        }
        WebElement submit=driver.findElement(By.xpath("//div[@class='card-footer']//button[@type='submit']"));
        submit.click();
        String actualErrorMsgAccountAmount=amount.getAttribute("validationMessage");
        Assertions.assertEquals("Please match the requested format.",actualErrorMsgAccountAmount);
    }
    @When("user should go deposit page and choose Vader \\(Standard Checking) and deposit ab")
    public void user_should_go_deposit_page_and_choose_vader_standard_checking_and_deposit_ab( List<Map<String,String>> list) {
        Map<String,String> data= list.get(0);
        WebElement deposit=driver.findElement(By.id("deposit-menu-item"));
        deposit.click();
        WebElement selectedAccount=driver.findElement(By.id("selectedAccount"));
        Select select=new Select(selectedAccount);
        if(data.get("accountNameAndType")!=null) {
            select.selectByVisibleText(data.get("accountNameAndType"));
        }
        WebElement amount=driver.findElement(By.id("amount"));
        if(data.get("amount")!=null) {
            amount.sendKeys(data.get("amount"));
        }
        WebElement submit=driver.findElement(By.xpath("//div[@class='card-footer']//button[@type='submit']"));
        submit.click();
        String actualErrorMsgAccountAmount=amount.getAttribute("validationMessage");
        Assertions.assertEquals("Please match the requested format.",actualErrorMsgAccountAmount);
    }
    @When("user should go deposit page and choose Vader \\(Standard Checking) and deposit {int}!{int}")
    public void user_should_go_deposit_page_and_choose_vader_standard_checking_and_deposit( Integer i,Integer i1,List<Map<String,String>> list) {
        Map<String,String> data= list.get(0);
        WebElement deposit=driver.findElement(By.id("deposit-menu-item"));
        deposit.click();
        WebElement selectedAccount=driver.findElement(By.id("selectedAccount"));
        Select select=new Select(selectedAccount);
        if(data.get("accountNameAndType")!=null) {
            select.selectByVisibleText(data.get("accountNameAndType"));
        }
        WebElement amount=driver.findElement(By.id("amount"));
        if(data.get("amount")!=null) {
            amount.sendKeys(data.get("amount"));
        }
        WebElement submit=driver.findElement(By.xpath("//div[@class='card-footer']//button[@type='submit']"));
        submit.click();
        String actualErrorMsgAccountAmount=amount.getAttribute("validationMessage");
        Assertions.assertEquals("Please match the requested format.",actualErrorMsgAccountAmount);
    }
}
