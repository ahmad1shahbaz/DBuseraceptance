package co.wedevx.digitalbank.automation.ui.steps.data_transformer;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingInfo;

import co.wedevx.digitalbank.automation.ui.models.TransactionTable;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTransformer {
    @DataTableType
    public AccountCard accountCardEntry(Map<String, String> entry) {
        String accountName = entry.get("accountName");
        String accountType=entry.get("accountType");
        String ownership=entry.get("ownership");
        long accountNumber=Long.parseLong(entry.get("accountNumber"));
        String interestRate=entry.get("interestRate");
        double balance=Double.parseDouble(entry.get("balance"));
        return new AccountCard(accountName,accountType,ownership,accountNumber,interestRate,balance);
    }
    @DataTableType
    public NewCheckingInfo newCheckingInfoEntry(Map<String,String> entry){
        String checkingAccountType=entry.get("checkingAccountType");
        String accountOwnership=entry.get("accountOwnership");
        String accountName=entry.get("accountName");
        double initialDepositAmount=Double.parseDouble(entry.get("initialDepositAmount"));
        return  new NewCheckingInfo(checkingAccountType,accountOwnership,accountName,initialDepositAmount);
    }
    @DataTableType
    public TransactionTable transactionTable(Map<String,String> entry){
        String operationType=entry.get("operationType");
        String category=entry.get("category");
        String description=entry.get("description");
        return  new TransactionTable(operationType,category,description);
    }
}

