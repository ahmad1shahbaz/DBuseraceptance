package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.*;

public class Hooks {
    @After("not @Deposit")
    public void afterEachScenario(Scenario scenario){
        Driver.takeScreenshot(scenario);
        Driver.closeDriver();
    }
    @BeforeAll()
    public static void establishConnection(){
        DBUtils.establishConnection();
    }
    @AfterAll()
    public static void closConnection(){
        DBUtils.closeConnection();
    }

}
