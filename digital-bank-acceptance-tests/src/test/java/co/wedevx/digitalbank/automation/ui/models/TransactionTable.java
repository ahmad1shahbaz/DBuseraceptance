package co.wedevx.digitalbank.automation.ui.models;

public class TransactionTable {
    private String operationType;
    private String category;
    private String description;

    public TransactionTable(String operationType, String category, String description) {
        this.operationType = operationType;
        this.category = category;
        this.description = description;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
