package api;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.reservationService;

import java.util.Date;
import java.util.List;
public class HotelResource {
    private static HotelResource instance;

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        if (null == instance) {
            instance = new HotelResource();
        }
        return instance;

    }

    CustomerService customerService = CustomerService.getInstance();
    reservationService reservationService = service.reservationService.getInstance();

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNo) {

        return reservationService.getARoom(roomNo);
    }

    public Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }
    public List<Reservation> getCustomerReservations(String customerEmail) {
        return reservationService.getCustomerReservations(customerEmail);
    }

    public List<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }
}

