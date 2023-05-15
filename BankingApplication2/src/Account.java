public class Account {

    private final int accountNumber;
    private final String accountName;
    private double balance;

    public Account(int accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    public boolean desposit(double amount) {
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public int getNumber() {
        return accountNumber;
    }

    public String getName() {
        return accountName;
    }

    public double getBalance() {
        return balance;
    }


}