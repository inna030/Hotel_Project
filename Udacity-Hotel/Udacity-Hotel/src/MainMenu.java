import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final AdminResource adminResource = AdminResource.getInstance();

    public static void main(String[] args) {
        try {
            boolean exit = false;
            while (!exit) {
                String selection = Menu();
                switch (selection) {
                    case "1":
                        findAndReserveRoom();
                        break;
                    case "2":
                        createAccount();
                        break;
                    case "3":
                        seeMyReservations();
                        break;
                    case "4":
                        AdminMenu.setAdminResource(adminResource);
                        AdminMenu.startAdmin();
                        break;
                    case "5":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid selection. Please try again.");
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void seeMyReservations() {
        System.out.println("Enter Customer Email format: xxx@xxx.xxx");
        String email = scanner.next();
        Customer customer = hotelResource.getCustomer(email);
        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            List<model.Reservation> reservations = hotelResource.getCustomerReservations(customer.getEmail());
            if (reservations.isEmpty()) {
                System.out.println("No reservations found for this customer.");
            } else {
                for (model.Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        }
    }



    private static Customer getExistingAccount() {
        System.out.println("Enter Email format: xxx@xxx.xxx");
        String email = scanner.next();
        return hotelResource.getCustomer(email);
    }

    private static Customer createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer First Name:");
        String firstName = scanner.next();
        System.out.println("Enter Customer Last Name:");
        String lastName = scanner.next();
        System.out.println("Enter Customer Email format:name@domain.com:");
        String email = scanner.next();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return new Customer(firstName, lastName, email);
    }


    private static void findAndReserveRoom() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter CheckIn Date dd/mm/yyyy example (01/02/2021)");
        Date checkInDate = dateFormat.parse(scanner.nextLine());
        System.out.println("Enter CheckOut Date dd/mm/yyyy example (01/02/2021)");
        Date checkOutDate = dateFormat.parse(scanner.nextLine());
        List<IRoom> availableRooms =  hotelResource.findARoom(checkInDate, checkOutDate);

        if(!availableRooms.isEmpty()){
            Customer customer;

            System.out.println("Would you like to book a room? y/n");
            char optionBookARoom = scanner.next().trim().charAt(0);

            if(optionBookARoom == 'y'){
                System.out.println("Do you have an account with us? y/n");
                char optionHasAccount = scanner.next().trim().charAt(0);

                if(optionHasAccount == 'y'){
                    customer = getExistingAccount();

                    if(customer == null){
                        System.out.println("Customer was not found.");
                        return;
                    }

                } else {
                    customer = createAccount();
                }


                boolean isRoomAvailable = false;
                while (!isRoomAvailable) {
                    System.out.println("Available rooms:");
                    System.out.println(availableRooms);
                    System.out.println("Please enter room number from the available rooms:");
                    String roomNumber = scanner.next();
                    IRoom selectedRoom = hotelResource.getRoom(roomNumber);

                    if(!availableRooms.contains(selectedRoom)){
                        System.out.println("Sorry, room number '" + roomNumber + "' is not available. We have recommend room for you:");
                        adminResource.recommendRoom(checkInDate,checkOutDate);
                    } else {
                        hotelResource.bookARoom(customer, selectedRoom, checkInDate, checkOutDate);
                        System.out.println("Reservation was successfully created!");
                        scanner.nextLine();
                        isRoomAvailable = true;
                    }
                }
            }
        } else {
            System.out.println("Sorry there are no rooms available.");
        }

    }
    private static String Menu() {
        System.out.println("__________________________________________________");
        System.out.println("   MAIN MENU");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. Create an Account");
        System.out.println("3. Check reservations");
        System.out.println("4. AdminMenu start");
        System.out.println("5. Exit");
        System.out.println("__________________________________________________");
        System.out.println("Enter menu selection:");
        return scanner.nextLine();
    }
    }

