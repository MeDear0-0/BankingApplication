import java.util.Random;
import java.util.Scanner;

public class BankingApplication2 {

    public static void main(String[] args) {

        Bank bank = new Bank("My Bank");
        int option;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Main Menu --------");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Open New Account");
            System.out.println("3. Close Existing Account");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            option = sc.nextInt();
            sc.nextLine();

            try {
                switch (option) {
                    case 1 -> bank.listAccount();
                    case 2 -> {
                        int number = genAccountNumber();
                        String name;
                        double balance;

                        System.out.print("Enter Account Name: ");
                        name = sc.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        balance = sc.nextDouble();
                        if (bank.openAccount(number, name, balance)) {
                            System.out.println("\nAccount Creation Successful.");
                            System.out.println("\tAccount Number: " + number);
                            System.out.println("\tName: " + name);
                            System.out.println("\tInitial Balance: " + balance + "\n");
                        } else {
                            System.out.println("Account Creation Failed.");
                        }
                    }
                    case 3 -> {
                        int number;
                        System.out.print("Enter Account Number: ");
                        number = sc.nextInt();
                    }
                    case 4 -> {
                        int number;
                        double amount;
                        System.out.print("Enter Account Number: ");
                        number = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter amount for desposit: ");
                        amount = sc.nextDouble();
                        bank.depositMoney(number, amount);
                    }
                    case 5 -> {
                        int number;
                        double amount;
                        System.out.print("Enter Account Number: ");
                        number = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter amount for withdraw: ");
                        amount = sc.nextDouble();
                        bank.withdrawMoney(number, amount);
                    }
                    case 6 -> {}
                    default -> throw new AssertionError();
                }
            } catch (AssertionError ex) {
                System.out.println("Please Enter Only 1-6");
            }


        } while (option != 6);
    }

    public static int genAccountNumber() {
        Random rand = new Random();
        int accNumber = 100000 + rand.nextInt(900000);
        return accNumber;
    }

}