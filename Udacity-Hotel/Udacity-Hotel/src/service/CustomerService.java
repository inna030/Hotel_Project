package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static CustomerService instance;
    private CustomerService(){
    }
    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }

        return instance;
    }
    private static final HashMap<String, Customer> customers=new HashMap<>();
    public void addCustomer(String firstName, String lastName, String email){
        Customer customer=new Customer(firstName,lastName,email);
        if(customers.containsKey(email)){
            throw new IllegalArgumentException("Customer with this email is already registered.");
        }
        else{
            customers.put(email,customer);}

    }
    public Customer getCustomer(String customerEmail){
        if(customers.containsKey(customerEmail)){
            return customers.get(customerEmail);
        }
        return null;
    }

    public static ArrayList<Customer> getAllCustomers(){
        return new ArrayList<>(customers.values());
    }



}
