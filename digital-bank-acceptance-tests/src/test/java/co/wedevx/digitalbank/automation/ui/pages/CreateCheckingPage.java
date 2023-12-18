package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class CreateCheckingPage extends BaseMenuPage{

    public CreateCheckingPage(WebDriver driver){
     super(driver);
    }

    @FindBy(id="Standard Checking")
    private WebElement standardChecking;
    @FindBy(id="Interest Checking")
    private WebElement interestChecking;
    @FindBy(id="Individual")
    private WebElement individualOwnership;
    @FindBy(id="Joint")
    private WebElement jointOwnership;
    @FindBy(id = "name")
    private WebElement accountName;
    @FindBy(id="openingBalance")
    private WebElement openingBalance;
    @FindBy(id = "newCheckingSubmit")
    private WebElement submitBtn;

    public void createCheking(List<NewCheckingInfo> checkingInfoList){
        NewCheckingInfo testData=checkingInfoList.get(0);
        checkingMenu.click();
        newCheckingButton.click();
        if(testData.getCheckingAccountType().equals("Standard Checking")){
            standardChecking.click();
        }
        else if(testData.getCheckingAccountType().equals("Interest Checking")){
             interestChecking.click();
        }
        else{
            throw new NoSuchElementException("Invalid Checking account type");
        }
        if(testData.getAccountOwnerShip().equals("Individual")){
            individualOwnership.click();
        }
        else if(testData.getAccountOwnerShip().equals("Joint")){
            jointOwnership.click();
        }
        else{
            throw new NoSuchElementException("Invalid Checking account type");
        }
        accountName.sendKeys(testData.getAccountName());
        String amount= String.valueOf(testData.getInitialDepositAmount());
        openingBalance.sendKeys(amount);
        submitBtn.click();
    }

}
