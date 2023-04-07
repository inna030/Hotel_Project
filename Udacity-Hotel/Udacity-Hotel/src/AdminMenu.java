import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AdminMenu {
    public static AdminResource adminResource = AdminResource.getInstance();
    public static Scanner scanner;
    public static void startAdmin() {
        scanner=new Scanner(System.in);
        try{
            boolean exit=true;
            while(exit){
                String option=AdminMenu();
                switch(option){
                    case "1" -> System.out.println(adminResource.getAllCustomers());
                    case "2" -> adminResource.getAllReservations();
                    case "3" -> System.out.println(adminResource.getAllRooms());
                    case "4" -> addRoom();
                    case "5" -> exit = false;
                    default -> AdminMenu();
                }
            }
            String[] arguments = new String[] {""};
            MainMenu.main(arguments);
        } catch(IllegalArgumentException e){
            System.out.println(e);
        } finally{
            scanner.close();
        }
    }
    public static void addRoom(){

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter room number");
        String roomNo = scanner.nextLine().trim();
        System.out.println("Enter price per night");
        double price = scanner.nextDouble();
        System.out.println("Enter room type: 1 for single,  2 for double bed");
        int type = scanner.nextInt();
        RoomType roomtype;
        if (type == 1) {
            roomtype = RoomType.SINGLE;
        } else {
            roomtype = RoomType.DOUBLE;
        }

        Room room=new Room(roomNo,price,roomtype);
        try {
            adminResource.addRoom(room);
        }catch (Exception e){
            System.out.println("Please submit another number");
        }finally{
            startAdmin();
        }




    }
    public static String AdminMenu() {
        System.out.println("__________________________________________________");
        System.out.println("Admin Menu of Hotel Reservation App");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Reservations");
        System.out.println("3. See all Rooms");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("__________________________________________________");
        System.out.println("Choice the menu you want to go:");
        return scanner.nextLine();

    }
    public static void setAdminResource(AdminResource adminResource) {

        AdminMenu.adminResource = adminResource;
    }
}

