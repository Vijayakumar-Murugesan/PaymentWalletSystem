package PackageWalletSystem;

public class Transaction {
    private double amount;
    private Wallet sender;
    private Wallet recipient;

    public Transaction(double amount, Wallet sender, Wallet recipient) {
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;
    }

    public void execute() {
        sender.deductFunds(amount);
        recipient.addFunds(amount);
    }
}
