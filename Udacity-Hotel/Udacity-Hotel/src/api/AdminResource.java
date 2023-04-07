package api;
import service.CustomerService;
import service.reservationService;
import model.Customer;
import model.IRoom;
import model.Room;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AdminResource {
    private static AdminResource instance;
    private AdminResource() {
    }

    public static AdminResource getInstance() {
        if(instance==null){
            instance=new AdminResource();
        }
        return instance;
    }
    CustomerService customerService=CustomerService.getInstance();
    reservationService reservationService= service.reservationService.getInstance();
    public Customer getCusomter(String email){
        return customerService.getCustomer(email);
    }
    public void addRoom(IRoom room){
        reservationService.addRoom(room);
    }
    public List<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }
    public ArrayList<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    public void getAllReservations(){
        reservationService.getAllReservations();
    }
    public void recommendRoom(Date dateC, Date dateO) throws ParseException {
        reservationService.recommendRoom(dateC,dateO);
    }
}
