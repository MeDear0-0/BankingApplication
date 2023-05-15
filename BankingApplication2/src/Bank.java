import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {

    private final String bankName;

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public void listAccount() {
        try {
            Connection connection = BankingConnection.connect();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM accounts";
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();

            System.out.println("\nList of Accounts --------");
            for (int i = 1; i <= rsMetaData.getColumnCount(); i++)
                System.out.printf("%-12s\t", rsMetaData.getColumnName(i));
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                    System.out.printf("%-12s\t", resultSet.getObject(i));
                }
                System.out.println();
            }
            System.out.println();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean openAccount(int accountNumber, String accountName, double balance) {
        try {
            Connection connection = BankingConnection.connect();
            String sql = "INSERT INTO accounts(number, name, balance) "
                    + "VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.setString(2, accountName);
            preparedStatement.setDouble(3, balance);

            preparedStatement.executeUpdate();
            connection.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean closeAccount(int accountNumber) {
        try {
            Connection connection = BankingConnection.connect();
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM accounts WHERE number = " + accountNumber;
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void depositMoney(int number, double amount) {
        Account account = getAccount(number);
        account.desposit(amount);

        try {
            Connection connection = BankingConnection.connect();
            String sql = "UPDATE accounts SET balance = ? WHERE number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void withdrawMoney(int number, double amount) {
        Account account = getAccount(number);
        account.withdraw(amount);

        try {
            Connection connection = BankingConnection.connect();
            String sql = "UPDATE accounts SET balance = ? WHERE number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getAccount(int number) {

        Account account = null;

        try {
            Connection connection = BankingConnection.connect();
            String sql = "SELECT * FROM accounts WHERE number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            account = new Account(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));


        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }


}