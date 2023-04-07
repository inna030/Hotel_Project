package model;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(isValidateEmail(email)){
        this.email = email;}
        else{
            throw new IllegalArgumentException("Email is invalid,please correct your email.");
        }
        }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private static boolean isValidateEmail(String email) {
        Matcher matcher = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$",
                Pattern.CASE_INSENSITIVE).matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email is invalid, the format is wrong. please re-enter a vaild email.");
        }
        return true;


    }
    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        Customer otherCustomer = (Customer) o;
        boolean firstNameEquals = (this.firstName == null && otherCustomer.firstName == null) ||
                (this.firstName != null && this.firstName.equals(otherCustomer.firstName));
        boolean lastNameEquals = (this.lastName == null && otherCustomer.lastName == null)
                || (this.lastName != null && this.lastName.equals(otherCustomer.lastName));
        boolean emailEquals = (this.email == null && otherCustomer.email == null)
                || (this.email != null && this.email.equals(otherCustomer.email));
        return firstNameEquals && lastNameEquals && emailEquals; //if three condition fullfilled together then return true
    }

        @Override
    public int hashCode() {
        int result=12;
        if(firstName!=null){
            result=31*result+firstName.hashCode();
        }
        if(lastName!=null){
            result=31*result+lastName.hashCode();
        }
        if(email!=null){
            result=31*result+email.hashCode();
        }
        return result;
    }
}
