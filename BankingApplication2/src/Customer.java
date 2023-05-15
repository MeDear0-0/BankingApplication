public class Customer {

    private final int idCard;
    private final String firstname, lastname;

    public Customer(int idCard, String firstname, String lastname) {
        this.idCard = idCard;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getIdCard() {
        return idCard;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }
}
