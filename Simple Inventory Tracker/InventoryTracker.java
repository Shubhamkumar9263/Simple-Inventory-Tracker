
import java.util.*;

class Product {
    int id, quantity;
    String name;
    double price;

    Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    void display() {
        System.out.println(id + " | " + name + " | Qty: "
                + quantity + " | Price: Rs." + price);
    }
}

public class InventoryTracker {
    static ArrayList<Product> inventory = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static Product search(int id) {
        for (Product p : inventory)
            if (p.id == id) return p;
        return null;
    }

    static void addProduct() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (search(id) != null) {
            System.out.println("ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        if (qty < 0 || price < 0) {
            System.out.println("Invalid quantity or price!");
            return;
        }

        inventory.add(new Product(id, name, qty, price));
        System.out.println("Product added successfully!");
    }

    static void displayProducts() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty!");
            return;
        }
        for (Product p : inventory) p.display();
    }

    static void searchProduct() {
        System.out.print("Enter ID: ");
        Product p = search(sc.nextInt());

        if (p != null) p.display();
        else System.out.println("Product not found!");
    }

    static void updateProduct() {
        System.out.print("Enter ID: ");
        Product p = search(sc.nextInt());

        if (p != null) {
            System.out.print("Enter new quantity: ");
            int qty = sc.nextInt();

            if (qty < 0) {
                System.out.println("Invalid quantity!");
                return;
            }

            p.quantity = qty;
            System.out.println("Quantity updated!");
        } else {
            System.out.println("Product not found!");
        }
    }

    static void deleteProduct() {
        System.out.print("Enter ID: ");
        Product p = search(sc.nextInt());

        if (p != null) {
            inventory.remove(p);
            System.out.println("Product deleted!");
        } else {
            System.out.println("Product not found!");
        }
    }

    static void lowStock() {
        for (Product p : inventory)
            if (p.quantity <= 5) p.display();
    }

    static void sortProducts() {
        inventory.sort(Comparator.comparingInt(p -> p.quantity));
        displayProducts();
    }

    static void totalValue() {
        double total = 0;
        for (Product p : inventory)
            total += p.quantity * p.price;

        System.out.println("Total Inventory Value: Rs." + total);
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== INVENTORY TRACKER =====");
            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Quantity");
            System.out.println("5. Delete Product");
            System.out.println("6. Low Stock Alert");
            System.out.println("7. Sort Products");
            System.out.println("8. Total Inventory Value");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: addProduct(); break;
                case 2: displayProducts(); break;
                case 3: searchProduct(); break;
                case 4: updateProduct(); break;
                case 5: deleteProduct(); break;
                case 6: lowStock(); break;
                case 7: sortProducts(); break;
                case 8: totalValue(); break;
                case 9: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 9);

        sc.close();
    }
}
