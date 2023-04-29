package PackageWalletSystem;

public class User {
    private String username;
    private String accountType;
    private int accountId;
    private Wallet wallet;
    private double balance;

    public User(String username, String accountType, String accountId, double initialBalance) {
        this.username = username;
        this.accountType = accountType;
        this.accountId = Integer.parseInt(accountId);
        this.wallet = new Wallet(initialBalance);
    }

    public String getUsername() {
        return username;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return wallet.getBalance();
    }

    public void transferFunds(User recipient, double amount) {
        PaymentProcessor paymentProcessor = new PaymentProcessorImpl();
        Transaction transaction = new Transaction(amount, wallet, recipient.wallet);
        transaction.execute();
    }

    public String toString() {
        return "Username: " + username + "\nAccount Type: " + accountType + "\nAccount ID: " + accountId + "\nBalance: " + wallet.getBalance();
    }
    public void addFunds(double amount) {
        this.balance += amount;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User other = (User) obj;
            return this.username.equals(other.username);
        }
        return false;
    }


    public void deductFunds(double amount) {
        if (this.balance < amount) {
            System.out.println("Insufficient balance. Deduction failed.");
        } else {
            this.balance -= amount;
        }
    }

    public void transferFunds(Wallet recipientWallet, double amount) {
        if (this.balance < amount) {
            System.out.println("Insufficient balance. Transfer failed.");
        } else {
            this.balance -= amount;
            recipientWallet.addFunds(amount);
            System.out.println("$" + amount + " transferred successfully.");
        }
    }
    public Wallet getWallet() {
        return wallet;
    }


}
