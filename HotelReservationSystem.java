import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;




public class HotelReservationSystem {
    
    private static final String url="";

    private static final String username="";

    private static final String password="";

    public static void main(String[] args) throws ClassNotFoundException ,SQLException {
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
    }catch (ClassNotFoundException e){
        System.out.println(e.getMessage());
    }
    try {
        Connection connection =DriverManager.getConnection(url,username,password);
        while(true){
            System.out.println();
            System.out.println("Hotel Maganagement System");
            Scanner scanner =new Scanner(System.in);
            System.out.println("1. Reserve a room");
            System.out.println("2. View reservations");
            System.out.println("3. Get Room Number");
            System.out.println("4. Update Reservatuins");
            System.out.println("5. Delete Reservations");
            System.out.println("0. Exit");
            System.out.println("Choose an Option: ");
            int choice =scanner.nextInt();
        switch(choice){
            case 1:
                reserveRoom(connection ,scanner);
                break;
            case 2:
                viewReservations(connection);
                break;
            case 3:
                getRoomNumber(connection,scanner);
                break;
            case 4:
                updateReservations(connection,scanner);
                break;
            case 5:
                deleteReservations(connection,scanner);
                break;
            case 0:
                exit();
                scanner.close();
                return;
            default:
                System.out.println("Invalid Option. Please try again.");
            }
        }
    }catch (SQLException e){
    System.out.println(e.getMessage());
    }catch (InterruptedException e){
        throw new RuntimeException(e); 
    }
}


 private static void reserveRoom(Connection connection, Scanner scanner){
    try {
        System.out.println("Enter Guest Name:");
        String guestName =scanner.next();
        scanner.nextLine();
        System.out.println("Enter Room number:");
        int roomNumber =scanner.nextInt();
        System.out.print("Enter contact number:");
        String contactNumber =scanner.next();
    
        String sql = "INSERT INTO reservations (guest_name, room_number, contact_number)" + "VALUES ('"+guestName+"',"+roomNumber+",'"+contactNumber+"')";
        
        try (Statement statement =connection.createStatement()){
            int rowsAffected =statement.executeUpdate(sql);
            if(rowsAffected > 0){
                System.out.println("Room reserved successfully!");
            }else{
                System.out.println("Failed to reserve the room. Please try again.");
            }
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
 }


 private static void viewReservations(Connection connection) throws SQLException {
        String sql = "SELECT reservation_id, guest_name, room_number, contact_number, reservation_date FROM reservations";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Current Reservations:");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                String guestName = resultSet.getString("guest_name");
                int roomNumber = resultSet.getInt("room_number");
                String contactNumber = resultSet.getString("contact_number");
                String reservationDate = resultSet.getTimestamp("reservation_date").toString();

                // Format and display the reservation data in a table-like format
                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",
                        reservationId, guestName, roomNumber, contactNumber, reservationDate);
            }

            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
        }
    }


    private static void getRoomNumber(Connection connection, Scanner scanner) throws SQLException {
        try{
            System.out.print("Enter reservation ID:");
            int reservationId =scanner.nextInt();
        
            System.out.println("Enter Guest Name:");
            String guestName =scanner.next();
        
        String sql = "SELECT room_number FROM reservations" + " WHERE reservation_id ="+reservationId+" AND guest_name ='"+guestName+"'";
        
        
        try (Statement statement =connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql)){
            
            
                if(resultSet.next()){
                int roomNumber =resultSet.getInt("room_number");
                System.out.println("Room Number forReservation ID "+reservationId +"and Guest "+guestName+"is:"+roomNumber);
            }else{
                System.out.println("No reservation found for the provided ID and Guest Name.");
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
}
    private static void updateReservations(Connection connection, Scanner scanner){
        try{
            System.out.print("Enter reservation ID to Update:");
            int reservationId =scanner.nextInt();
            scanner.nextLine();

           if(!reservationExists(connection, reservationId)){
                System.out.println("No reservation found with the provided ID.");
                return;
            }
        
            System.out.print("Enter Guest Name:");
            String guestName =scanner.nextLine();
        
            System.out.print("Enter new Room number:");
            int newRoomNumber =scanner.nextInt();

            System.out.print("Enter new contact number:");
            String newContactNumber =scanner.next();
        
        String sql = "UPDATE reservations SET guest_name ='"+guestName+"', room_number ="+newRoomNumber+", contact_number ='"+newContactNumber+"' WHERE reservation_id ="+reservationId;
        
        
        try (Statement statement =connection.createStatement()){
            int rowsAffected =statement.executeUpdate(sql);
          
            if(rowsAffected > 0){
                System.out.println("Reservation updated successfully!");
            }else{
                System.out.println("No reservation found for the provided ID and Guest Name.");
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }

}

    private static void deleteReservations(Connection connection, Scanner scanner){
        try{
            System.out.print("Enter reservation ID to delete:");
            int reservationId =scanner.nextInt();
        
            if(!reservationExists(connection, reservationId)){
                System.out.println("No reservation found with the provided ID.");
                return;
            }
        
        String sql = "DELETE FROM reservations WHERE reservation_id ="+reservationId;
        
        
        try (Statement statement =connection.createStatement()){
            int rowsAffected =statement.executeUpdate(sql);
          
            if(rowsAffected > 0){
                System.out.println("Reservation deleted successfully!");
            }else{
                System.out.println("No reservation found for the provided ID and Guest Name.");
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
}

    private static boolean reservationExists(Connection connection, int reservationId){
        try{
            String sql = "SELECT reservation_id FROM reservations WHERE reservation_id ="+reservationId;
        
            try (Statement statement =connection.createStatement();
                ResultSet resultSet =statement.executeQuery(sql)){
                
                    return resultSet.next();
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    private static void exit() throws InterruptedException {
        System.out.println("Exiting the Hotel Management System. Goodbye!");
        int i=5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(450); // Simulate a delay before exiting
            i--;
        }
    }
}

