package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.utils.MockData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class RegistrationPage extends  BasePageClass{

    MockData mockData=new MockData();





    public RegistrationPage(WebDriver driver){
        super(driver);
        }
        @FindBy(id="title")
        private WebElement selectTitle;
        @FindBy(id="firstName")
        private  WebElement firstNameTxtBox;
        @FindBy(id="lastName")
        private  WebElement lastNameTxtBox;
        @FindBy(xpath = "//label[@for='male']//input[@id='gender']")
        private WebElement genderCheckBox;
        @FindBy(id="dob")
        private WebElement dobTxtBox;
        @FindBy(id="ssn")
        private  WebElement ssnTxtBox;
        @FindBy(id="emailAddress")
        private WebElement emailAddressTxtBox;
        @FindBy(id="password")
        private WebElement passwordTxtBox;
        @FindBy(id="confirmPassword")
        private WebElement confirmPasswordTxtBox;
        @FindBy(xpath = "//button[@type='submit']")
        private WebElement submit;
     @FindBy(id="address")
    private WebElement addressTxtBox;
    @FindBy(id="locality")
    private  WebElement localityTxtBox;
    @FindBy(id="region")
    private  WebElement regionTxtBox;
    @FindBy(id="postalCode")
    private WebElement postalCodeTxtBox;
    @FindBy(id="country")
    private WebElement countryTxtBox;
    @FindBy(id="homePhone")
    private  WebElement homePhoneTxtBox;
    @FindBy(id="mobilePhone")
    private WebElement mobilePhoneTxtBox;
    @FindBy(id="agree-terms")
    private WebElement agreeTermsCheckBox;
    @FindBy(xpath ="//button[@type='submit']")
    private WebElement submitClick;
        //actions method
        public String password(List<Map<String,String>> pageData){
            return pageData.get(0).get("password");
        }
        public void registerFirstPage(List<Map<String,String>> pageData){
           Map<String,String> firstRow= pageData.get(0);
            selectTitle.click();
            Select select=new Select(selectTitle);
            select.selectByValue("Mr.");
            genderCheckBox.click();
            firstNameTxtBox.sendKeys(firstRow.get("firstName"));
            lastNameTxtBox.sendKeys(firstRow.get("lastName"));
            dobTxtBox.sendKeys(firstRow.get("dob"));
            ssnTxtBox.sendKeys(mockData.generateRandomSSN());
            emailAddressTxtBox.sendKeys(mockData.generateEmailAndName().get("email"));
            passwordTxtBox.sendKeys(firstRow.get("password"));
            confirmPasswordTxtBox.sendKeys(firstRow.get("password"));
            submit.click();
            addressTxtBox.sendKeys(firstRow.get("address"));
            localityTxtBox.sendKeys(firstRow.get("locality"));
            regionTxtBox.sendKeys(firstRow.get("region"));
            postalCodeTxtBox.sendKeys(firstRow.get("postalCode"));
            countryTxtBox.sendKeys(firstRow.get("country"));
            homePhoneTxtBox.sendKeys(firstRow.get("homePhone"));
            mobilePhoneTxtBox.sendKeys(firstRow.get("mobilePhone"));
            agreeTermsCheckBox.click();
            submitClick.click();
        }

    }

