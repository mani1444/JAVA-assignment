import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LibraryCatalogApp {
    static String[][] books = {
            {"101", "HTML and CSS", "Jon Duckett", "Available", "Null"},
            {"102", "JavaScript: The Good Parts", "Douglas C", "Available", "Null"},
            {"103", "Learning Web Design", "Jennifer N", "CP2014", "23-May-2023"},
            {"104", "Responsive Web Design", "Ben Frain", "EC3142", "17-May-2023"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n-----------------------------------------------------------------------------------------------------");
            System.out.println("Welcome to the Unique Library\n-----------------------------------------------------------------------------------------------------");
            System.out.println("1. View the complete list of Books");
            System.out.println("2. Issue a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");

            System.out.print("\nPlease choose an option from the above menu: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAllBooks();
                    break;
                case 2:
                    issueBook(scanner);
                    break;
                case 3:
                    returnBook(scanner);
                    break;
                case 4:
                    System.out.println("\nThank you for visiting SmartPoint!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayAllBooks() {
        System.out.println("\n-----------------------------------------------------------------------------------------------------");
        System.out.println("List of all Books\n-----------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s%-30s%-20s%-15s%-15s\n", "Book ID", "Book Title", "Author", "Availability", "Issue Date");

        for (String[] book : books) {
            System.out.printf("%-8s%-30s%-20s%-15s%-15s\n", book[0], book[1], book[2], book[3], book[4]);
        }

        System.out.println("\n-----------------------------------------------------------------------------------------------------");
    }

    private static void issueBook(Scanner scanner) {
        System.out.println("\n-----------------------------------------------------------------------------------------------------");
        System.out.println("Issue the Book\n-----------------------------------------------------------------------------------------------------");

        System.out.print("Enter the Book ID: ");
        String bookID = scanner.next();

        int index = findBookIndex(bookID);

        if (index == -1) {
            System.out.println("Book not found.");
            return;
        }

        String[] book = books[index];

        System.out.println(book[0] + " - " + book[1] + " by " + book[2] + ": " + book[3]);

        if (!book[3].equals("Available")) {
            System.out.println("Book is already issued.");
            return;
        }

        System.out.print("Enter StudentID: ");
        String studentID = scanner.next();

        System.out.print("Enter \"C\" to confirm: ");
        String confirm = scanner.next();

        if (confirm.equalsIgnoreCase("C")) {
            book[3] = studentID;
            book[4] = getCurrentDate();
            System.out.println("BookID: " + bookID + " is Issued to " + studentID);
        } else {
            System.out.println("Issue canceled.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.println("\n-----------------------------------------------------------------------------------------------------");
        System.out.println("Return the Book\n-----------------------------------------------------------------------------------------------------");

        System.out.print("Enter the Book ID: ");
        String bookID = scanner.next();

        int index = findBookIndex(bookID);

        if (index == -1) {
            System.out.println("Book not found.");
            return;
        }

        String[] book = books[index];

        System.out.println(book[0] + " - " + book[1] + " by " + book[2]);
        System.out.println("StudentID - " + book[3]);
        System.out.println("Issue Date - " + book[4]);

        if (book[3].equals("Available")) {
            System.out.println("Book is not issued.");
            return;
        }

        int daysDelayed = calculateDaysDelayed(book[4]);
        double delayedCharges = calculateDelayedCharges(daysDelayed);

        System.out.println("Delayed by - " + daysDelayed + " days");
        System.out.println("Delayed Charges - Rs. " + delayedCharges);

        System.out.print("Enter \"R\" to confirm the return: ");
        String confirm = scanner.next();

        if (confirm.equalsIgnoreCase("R")) {
            book[3] = "Available";
            book[4] = "Null";
            System.out.println("BookID: " + bookID + " is returned back");
        } else {
            System.out.println("Return canceled.");
        }
    }

    private static int findBookIndex(String bookID) {
        for (int i = 0; i < books.length; i++) {
            if (books[i][0].equals(bookID)) {
                return i;
            }
        }
        return -1;
    }

    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormat.format(new Date());
    }

    private static int calculateDaysDelayed(String issueDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date currentDate = new Date();
            Date dateIssued = dateFormat.parse(issueDate);

            long difference = currentDate.getTime() - dateIssued.getTime();
            return (int) (difference / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static double calculateDelayedCharges(int daysDelayed) {
        if (daysDelayed > 7) {
            return (daysDelayed - 7) * 10.0;
        }
        return 0;
    }
}
