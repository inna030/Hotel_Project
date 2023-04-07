import model.Customer;

public class HotelApp {
    public static void main(String[] args){
        Customer customer = new Customer("first", "second", "test@domain.com");
        System.out.println(customer);
    }
}
