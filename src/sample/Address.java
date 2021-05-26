package sample;

import java.io.Serializable;

public class Address implements Serializable {

    static final long serialVersionUID = -7588910448693010399L;
    private String town;
    private String street;
    private int houseNumber;
    private int apartmentNumber;

    public Address() {
        this.town = "x";
        this.street = "x";
        this.houseNumber = 0;
        this.apartmentNumber = 0;
    }

    public Address(String a, String b, int i, int i1) {
        this.town = a;
        this.street = b;
        this.houseNumber = i;
        this.apartmentNumber = i1;
    }

    public void setTown(String town) throws BadDataFormatException{
        if (town.length() > 0) {
            this.town = town;
        } else {
            throw new BadDataFormatException("Error while - setting town");
        }
    }

    public void setStreet(String street) throws BadDataFormatException{
        if (street.length() > 0) {
            this.street = street;
        } else {
            throw new BadDataFormatException("Error while - setting street");
        }
    }

    public void setHouseNumbers(String houseNumbers) throws BadDataFormatException{
        String[] nums = houseNumbers.split("/");
        if (nums.length != 2) {
            throw new BadDataFormatException("Error while - setting house numbers");
        } else {
            try {
                this.houseNumber = Integer.parseInt(nums[0]);
                this.apartmentNumber = Integer.parseInt(nums[1]);
            } catch (NumberFormatException e) {
                throw new BadDataFormatException("Error while - setting house numbers");
            }
        }
    }

    @Override
    public String toString() {
        return (
                this.town + " " +
                this.street + " " +
                this.houseNumber + " " +
                this.apartmentNumber
        );
    }



}
