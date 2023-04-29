package PackageWalletSystem;

public class Wallet {
    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void addFunds(double amount) {
        balance += amount;
    }

    public void deductFunds(double amount) {
        if (balance < amount) {
            throw new RuntimeException("Insufficient funds!");
        }
        balance -= amount;
    }
    public static Wallet getWallet() {
        double initialBalance = 0.0; // or some other default value
        Wallet wallet = new Wallet(initialBalance);
        return wallet;
    }


}
