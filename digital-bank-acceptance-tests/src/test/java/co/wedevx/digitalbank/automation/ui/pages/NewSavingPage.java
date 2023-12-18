package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewSavingPage extends  BasePageClass{

    public NewSavingPage(WebDriver driver){
        super(driver);
    }
    @FindBy(id="Savings")
    private WebElement savingRadioBtn;
    @FindBy(id="Money Market")
    private WebElement moneyMarketRadioBtn;
    @FindBy(id="Individual")
    private WebElement individualRadioBtn;
    @FindBy(id="Joint")
    private  WebElement jointRadioBtn;
    @FindBy(id="name")
    private WebElement accountNameTxtBox;
    @FindBy(id="openingBalance")
    private WebElement openingBalanceTxtBox;
    @FindBy(id="newSavingsSubmit")
    private WebElement newSavingsSubmit;
    @FindBy(id="newSavingsReset")
    private  WebElement newSavingsReset;
    public void newSavingAccount(){

    }

}
