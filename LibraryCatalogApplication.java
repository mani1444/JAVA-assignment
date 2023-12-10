import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private int bookId;
    private String title;
    private String author;
    private String availability;
    private String issueDate;

    public Book(int bookId, String title, String author, String availability, String issueDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.availability = availability;
        this.issueDate = issueDate;
    }

    public String toCsvString() {
        return String.format("%d,%s,%s,%s,%s%n", bookId, title, author, availability, issueDate);
    }

    public static Book fromCsvString(String csvString) {
        String[] parts = csvString.split(",");
        int bookId = Integer.parseInt(parts[0]);
        String title = parts[1];
        String author = parts[2];
        String availability = parts[3];
        String issueDate = parts[4];
        return new Book(bookId, title, author, availability, issueDate);
    }

    // Getters and setters...
}

class LibraryCatalog {
    private List<Book> books = new ArrayList<>();
    private static final String CSV_FILE_PATH = "library_catalog.csv";

    public void addBook(int bookId, String title, String author, String availability, String issueDate) {
        Book book = new Book(bookId, title, author, availability, issueDate);
        books.add(book);
    }

    public void displayCatalog() {
        System.out.println("\n-----------------------------------------------------------------------------------------------------");
        System.out.println("List of all Books\n");
        System.out.printf("%-10s%-40s%-20s%-15s%-15s%n", "Book ID", "Book Title", "Author", "Availability", "Issue Date");
        for (Book book : books) {
            System.out.printf("%-10d%-40s%-20s%-15s%-15s%n", book.getBookId(), book.getTitle(), book.getAuthor(),
                    book.getAvailability(), book.getIssueDate());
        }
        System.out.println("\n-----------------------------------------------------------------------------------------------------");
    }

    public void issueBook(int bookId, String studentId) {
        // Implement issueBook logic here...
    }

    public void returnBook(int bookId) {
        // Implement returnBook logic here...
    }

    public void saveToCsv() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            writer.write("Book ID,Book Title,Author,Availability,Issue Date\n");
            for (Book book : books) {
                writer.write(book.toCsvString());
            }
            System.out.println("Data saved to " + CSV_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromCsv() {
        books.clear(); // Clear existing data before loading
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line; // Read header
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                books.add(Book.fromCsvString(line));
            }
            System.out.println("Data loaded from " + CSV_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class LibraryApplication {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n-----------------------------------------");
            System.out.println("Library Catalog System\n");
            System.out.println("1. Display Catalog");
            System.out.println("2. Add Book");
            System.out.println("3. Save to CSV");
            System.out.println("4. Load from CSV");
            System.out.println("5. Exit\n");

            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    catalog.displayCatalog();
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Availability: ");
                    String availability = scanner.nextLine();
                    System.out.print("Enter Issue Date: ");
                    String issueDate = scanner.nextLine();
                    catalog.addBook(bookId, title, author, availability, issueDate);
                    System.out.println("Book added successfully.");
                    break;

                case 3:
                    catalog.saveToCsv();
                    break;

                case 4:
                    catalog.loadFromCsv();
                    break;

                case 5:
                    System.out.println("Exiting the application.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
}
