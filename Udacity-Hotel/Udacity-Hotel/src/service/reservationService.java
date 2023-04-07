package service;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class reservationService {
    private static reservationService instance;


    private reservationService() {

    }
    public static reservationService getInstance() {
        if (instance == null) {
            instance = new reservationService();
        }
        return instance;
    }

    ArrayList<IRoom> rooms = new ArrayList<>();
    private static final Set<Reservation> reservations=new HashSet<>(); //hashset only contain different key reservation info, the value will be same
    public void addRoom(IRoom room){
        if(rooms.contains(room)){
            throw new IllegalArgumentException("Room number " + room.getRoomNumber() +
                    " already exists");
        } else {
            rooms.add(room);
        }
    }
    public IRoom getARoom(String roomNo){
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomNo)) {
                return room;
            }
        }
        return null;

    }
    public List<IRoom> getAllRooms() {
        return rooms;
    }
    public Reservation reserveARoom(Customer customer,IRoom room, Date checkInDate, Date checkOutDate) {
        try{
            Reservation newReseveration=new Reservation(customer, room, checkInDate, checkOutDate);
            if(reservations.contains(newReseveration)){
                throw new IllegalArgumentException("This room is already booked");
            }else{
                reservations.add(newReseveration);
                return newReseveration;
            }} catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Can't book the room");
            }
        }
    public List<IRoom> recommendRoom(Date checkInDate, Date checkOutDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date newCheckinDate = addDate(checkInDate, 7);
        Date newCheckOutDate = addDate(checkOutDate, 7);
        List<IRoom> available = findRooms(newCheckinDate, newCheckOutDate);
        if (available.isEmpty()) {
            Date currentDate = new Date();
            newCheckinDate = addDate(currentDate, 7);
            newCheckOutDate = addDate(newCheckinDate, 7);
            available = findRooms(newCheckinDate, newCheckOutDate);
            if (available.isEmpty()) {
                throw new RuntimeException("No rooms available");
            }
        }
        return available;
    }
    public static Date addDate(Date date,long day) throws ParseException{
        Long time=date.getTime();
        day=day*24*60*60*1000;
        time+=day;
        return new Date(time);
    }

    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms=new ArrayList<>();

        try{
            List<IRoom> reservedRooms=getReservedRooms(checkInDate,checkOutDate);
            for (IRoom room : rooms) {
                if(!reservedRooms.contains(room)){
                    availableRooms.add(room);
                }
            }

        }catch(Exception e){
            if(availableRooms.isEmpty()) return null;
        }
        return availableRooms;
    }
    public List<IRoom> getReservedRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> reservedRooms=new ArrayList<>();
        try{
            for(Reservation reservation:reservations){
                if(reservation.getCheckInDate().getTime()>=checkInDate.getTime() &&     reservation.getCheckOutDate().getTime() <= checkOutDate.getTime()) {
                    reservedRooms.add(reservation.getRoom());
                }
            }
        }catch (Exception e){
            if(reservedRooms.isEmpty()) return null;
        }
        return reservedRooms;
    }
    public List<Reservation> getCustomerReservations(String customerEmail) {
        List<Reservation> getCustomer=new ArrayList<>();
        for(Reservation reservation:reservations){
            if(reservation.getCustomer().getEmail().equals(customerEmail)){
                getCustomer.add(reservation);
            }
        }
        return getCustomer;
    }
    public boolean isRoomAvailable(IRoom room,List<IRoom> availableRooms){
        for(IRoom availableRoom:availableRooms){
            if(availableRoom.equals(room)){
                return true;
            }
        }
        return false;
    }
    public void getAllReservations(){
        for(Reservation reservation:reservations){
            System.out.println(reservation);
        }
    }





}



