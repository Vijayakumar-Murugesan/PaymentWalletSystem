package PackageWalletSystem;

public interface PaymentProcessor {
    void addFunds(double amount);
    void deductFunds(double amount);
}
