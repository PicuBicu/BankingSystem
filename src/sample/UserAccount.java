package sample;

import java.io.Serializable;

public class UserAccount implements Serializable {

    static final long serialVersionUID = -7588980448693010199L;
    private int id;
    private String firstName;
    private String lastName;
    private String PESEL;
    private Address address;
    private double money;

    public UserAccount() {
        this.id = 0;
        this.firstName = "X";
        this.lastName = "X";
        this.PESEL = "X";
        this.address = new Address();
        this.money = 0;
    }

    public UserAccount(int id, String firstName, String lastName, String PESEL, Address address, int money) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.PESEL = PESEL;
        this.address = address;
        this.money = (double) Math. round(money * 100) / 100;
    }

    public void setId(String id) throws BadDataFormatException {
        try {
            this.id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new BadDataFormatException("Error while - setting id value");
        }
    }

    public void setFirstName(String firstName) throws BadDataFormatException {
        if (firstName.length() > 0) {
            this.firstName = firstName;
        } else {
            throw new BadDataFormatException("Error while - setting first name");
        }
    }

    public void setLastName(String lastName) throws BadDataFormatException {
        if (lastName.length() > 0) {
            this.lastName = lastName;
        } else {
            throw new BadDataFormatException("Error while - setting last name");
        }
    }

    public void setPESEL(String PESEL) throws BadDataFormatException {
        if (PESEL.length() == 12 && validatePESEL(PESEL)) {
            this.PESEL = PESEL;
        } else {
            throw new BadDataFormatException("Error while - setting PESEL");
        }
    }

    public void setMoney(String money) throws BadDataFormatException {
        try {
            this.money = (double)Math.round(Double.parseDouble(money) * 100) / 100;
        } catch (NumberFormatException e) {
            throw new BadDataFormatException("Error while - setting money");
        }
    }

    public void setMoney(double money) {
        this.money = (double)Math.round(money * 100) / 100;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean validatePESEL(String PESEL) {
        int length = PESEL.length();
        for (int i = 0; i < length; i++) {
            if (PESEL.charAt(i) < '0' && PESEL.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return (
                this.id + ":" +
                this.firstName + ":" +
                this.lastName + ":" +
                this.PESEL + ":" +
                this.address + ":" +
                this.money
        );
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPESEL() {
        return this.PESEL;
    }

    public String getAddress() {
        return this.address.toString();
    }

    public double getMoney() {
        return money;
    }

}
