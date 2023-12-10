import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String name;
    private String specification;
    private double cost;
    private int quantity;

    public Product(String name, String specification, double cost, int quantity) {
        this.name = name;
        this.specification = specification;
        this.cost = cost;
        this.quantity = quantity;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

public class InventoryManagementSystem {
    private static ArrayList<Product> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Product List");
            System.out.println("2. Product Count");
            System.out.println("3. View Product Details");
            System.out.println("4. Edit Product");
            System.out.println("5. Update Inventory");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    displayProductList();
                    break;
                case 2:
                    displayProductCount();
                    break;
                case 3:
                    viewProductDetails();
                    break;
                case 4:
                    editProduct();
                    break;
                case 5:
                    updateInventory();
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayProductList() {
        System.out.println("\nProduct List:");
        for (Product product : inventory) {
            System.out.println(product.getName());
        }
    }

    private static void displayProductCount() {
        System.out.print("Enter the product name: ");
        String productName = scanner.nextLine();

        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                System.out.println("Quantity available: " + product.getQuantity());
                return;
            }
        }

        System.out.println("Product not found in inventory.");
    }

    private static void viewProductDetails() {
        System.out.print("Enter the product name: ");
        String productName = scanner.nextLine();

        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                System.out.println("Product Specification: " + product.getSpecification());
                System.out.println("Product Cost: $" + product.getCost());
                System.out.println("Quantity available: " + product.getQuantity());
                return;
            }
        }

        System.out.println("Product not found in inventory.");
    }

    private static void editProduct() {
        System.out.print("Enter the product name: ");
        String productName = scanner.nextLine();

        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                System.out.print("Enter new product specification: ");
                product.setSpecification(scanner.nextLine());
                System.out.print("Enter new product cost: $");
                product.setCost(scanner.nextDouble());
                System.out.println("Product details updated successfully.");
                return;
            }
        }

        System.out.println("Product not found in inventory.");
    }

    private static void updateInventory() {
        System.out.print("Enter the product name: ");
        String productName = scanner.nextLine();

        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                System.out.print("Do you want to (1) Add or (2) Delete quantities? Enter your choice: ");
                int option = scanner.nextInt();
                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();

                if (option == 1) {
                    product.setQuantity(product.getQuantity() + quantity);
                    System.out.println(quantity + " quantities added to inventory.");
                } else if (option == 2) {
                    if (product.getQuantity() >= quantity) {
                        product.setQuantity(product.getQuantity() - quantity);
                        System.out.println(quantity + " quantities deleted from inventory.");
                    } else {
                        System.out.println("Insufficient quantity in inventory.");
                    }
                } else {
                    System.out.println("Invalid option. Please enter 1 or 2.");
                }

                return;
            }
        }

        System.out.println("Product not found in inventory.");
    }
}
