import java.util.ArrayList;
import java.util.Scanner;

class Visitor {
    private String name;
    private String contactNumber;
    private String email;
    private String appointmentTime;

    public Visitor(String name, String contactNumber, String email) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.appointmentTime = "Not scheduled";
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}

public class AppointmentManagementSystem {
    private static ArrayList<Visitor> visitorsList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Visitors List");
            System.out.println("2. Add new Visitor");
            System.out.println("3. Edit Visitor Details");
            System.out.println("4. View Appointment Schedule for a Day");
            System.out.println("5. Book an Appointment");
            System.out.println("6. Edit/Cancel Appointment");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    displayVisitorsList();
                    break;
                case 2:
                    addNewVisitor();
                    break;
                case 3:
                    editVisitorDetails();
                    break;
                case 4:
                    viewAppointmentSchedule();
                    break;
                case 5:
                    bookAppointment();
                    break;
                case 6:
                    editCancelAppointment();
                    break;
                case 7:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayVisitorsList() {
        System.out.println("\nVisitors List:");
        for (Visitor visitor : visitorsList) {
            System.out.println("Name: " + visitor.getName() + ", Contact: " + visitor.getContactNumber() +
                    ", Email: " + visitor.getEmail() + ", Appointment Time: " + visitor.getAppointmentTime());
        }
    }

    private static void addNewVisitor() {
        System.out.print("Enter visitor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Visitor newVisitor = new Visitor(name, contactNumber, email);
        visitorsList.add(newVisitor);

        System.out.println("Visitor added successfully.");
    }

    private static void editVisitorDetails() {
        System.out.print("Enter the name of the visitor to edit details: ");
        String visitorName = scanner.nextLine();

        for (Visitor visitor : visitorsList) {
            if (visitor.getName().equalsIgnoreCase(visitorName)) {
                System.out.print("Enter new contact number: ");
                visitor.setContactNumber(scanner.nextLine());
                System.out.print("Enter new email: ");
                visitor.setEmail(scanner.nextLine());
                System.out.println("Visitor details updated successfully.");
                return;
            }
        }

        System.out.println("Visitor not found.");
    }

    private static void viewAppointmentSchedule() {
        System.out.print("Enter the appointment time to view schedule (e.g., 9 AM): ");
        String appointmentTime = scanner.nextLine();

        System.out.println("\nAppointment Schedule for " + appointmentTime + ":");
        for (Visitor visitor : visitorsList) {
            if (visitor.getAppointmentTime().equalsIgnoreCase(appointmentTime)) {
                System.out.println("Name: " + visitor.getName() + ", Contact: " + visitor.getContactNumber() +
                        ", Email: " + visitor.getEmail());
            }
        }
    }

    private static void bookAppointment() {
        System.out.print("Enter the name of the visitor to book an appointment: ");
        String visitorName = scanner.nextLine();

        for (Visitor visitor : visitorsList) {
            if (visitor.getName().equalsIgnoreCase(visitorName)) {
                if (visitor.getAppointmentTime().equals("Not scheduled")) {
                    System.out.print("Enter the appointment time (e.g., 9 AM): ");
                    String appointmentTime = scanner.nextLine();
                    visitor.setAppointmentTime(appointmentTime);
                    System.out.println("Appointment booked successfully.");
                } else {
                    System.out.println("This visitor already has an appointment scheduled.");
                }
                return;
            }
        }

        System.out.println("Visitor not found.");
    }

    private static void editCancelAppointment() {
        System.out.print("Enter the name of the visitor to edit/cancel appointment: ");
        String visitorName = scanner.nextLine();

        for (Visitor visitor : visitorsList) {
            if (visitor.getName().equalsIgnoreCase(visitorName)) {
                if (!visitor.getAppointmentTime().equals("Not scheduled")) {
                    System.out.print("Do you want to (1) Edit or (2) Cancel the appointment? Enter your choice: ");
                    int option = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (option == 1) {
                        System.out.print("Enter the new appointment time (e.g., 10 AM): ");
                        String newAppointmentTime = scanner.nextLine();
                        visitor.setAppointmentTime(newAppointmentTime);
                        System.out.println("Appointment updated successfully.");
                    } else if (option == 2) {
                        visitor.setAppointmentTime("Not scheduled");
                        System.out.println("Appointment canceled successfully.");
                    } else {
                        System.out.println("Invalid option. Please enter 1 or 2.");
                    }
                } else {
                    System.out.println("This visitor does not have an appointment scheduled.");
                }
                return;
            }
        }

        System.out.println("Visitor not found.");
    }
}
