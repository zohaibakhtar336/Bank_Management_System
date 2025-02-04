public class Transaction {
    private String accountNumber;
    private String transactionType;
    private double amount;

    public Transaction(String accountNumber, String transactionType, double amount) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account: " + accountNumber + ", Type: " + transactionType + ", Amount: " + amount;
    }
}
