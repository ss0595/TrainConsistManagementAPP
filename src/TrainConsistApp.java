import java.util.*;

// Abstract Bogie
abstract class Bogie {
    String id;
    String type;

    Bogie(String id, String type) {
        this.id = id;
        this.type = type;
    }

    abstract void display();
}

// Passenger Bogie
class PassengerBogie extends Bogie {
    String category;
    int capacity;

    PassengerBogie(String id, String category, int capacity) {
        super(id, "Passenger");
        this.category = category;
        this.capacity = capacity;
    }

    void display() {
        System.out.println("ID: " + id +
                " | Category: " + category +
                " | Capacity: " + capacity);
    }
}

// Train class with ArrayList operations
class Train {
    String trainName;
    ArrayList<PassengerBogie> bogies;

    Train(String trainName) {
        this.trainName = trainName;
        bogies = new ArrayList<>();
    }

    // Add bogie
    void addBogie(PassengerBogie b) {
        bogies.add(b);
        System.out.println("Bogie added successfully!");
    }

    // Remove bogie by ID
    void removeBogie(String id) {
        Iterator<PassengerBogie> it = bogies.iterator();
        boolean found = false;

        while (it.hasNext()) {
            PassengerBogie b = it.next();
            if (b.id.equals(id)) {
                it.remove();
                found = true;
                System.out.println("Bogie removed successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Bogie not found!");
        }
    }

    // Check if bogie exists
    void searchBogie(String id) {
        for (PassengerBogie b : bogies) {
            if (b.id.equals(id)) {
                System.out.println("Bogie found:");
                b.display();
                return;
            }
        }
        System.out.println("Bogie not found!");
    }

    // Display all bogies
    void displayAll() {
        System.out.println("\nTrain: " + trainName);
        if (bogies.isEmpty()) {
            System.out.println("No bogies added.");
            return;
        }

        for (PassengerBogie b : bogies) {
            b.display();
        }
    }
}

// Main class
public class TrainConsistApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Train train = new Train("Express 101");

        int choice;

        do {
            System.out.println("\n--- Train Menu ---");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Remove Bogie");
            System.out.println("3. Search Bogie");
            System.out.println("4. Display All Bogies");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Bogie ID: ");
                    String id = sc.next();

                    System.out.print("Enter Category (Sleeper/AC/First): ");
                    String cat = sc.next();

                    System.out.print("Enter Capacity: ");
                    int cap = sc.nextInt();

                    train.addBogie(new PassengerBogie(id, cat, cap));
                    break;

                case 2:
                    System.out.print("Enter Bogie ID to remove: ");
                    train.removeBogie(sc.next());
                    break;

                case 3:
                    System.out.print("Enter Bogie ID to search: ");
                    train.searchBogie(sc.next());
                    break;

                case 4:
                    train.displayAll();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
import java.util.*;

// Passenger Bogie
class PassengerBogie {
    String id;
    String category;
    int capacity;

    PassengerBogie(String id, String category, int capacity) {
        this.id = id;
        this.category = category;
        this.capacity = capacity;
    }

    void display() {
        System.out.println("ID: " + id +
                " | Category: " + category +
                " | Capacity: " + capacity);
    }
}

// Train class with HashSet for unique IDs
class Train {
    String trainName;
    ArrayList<PassengerBogie> bogies;
    HashSet<String> bogieIds; // ensures uniqueness

    Train(String trainName) {
        this.trainName = trainName;
        bogies = new ArrayList<>();
        bogieIds = new HashSet<>();
    }

    // Add bogie with uniqueness check
    void addBogie(PassengerBogie b) {
        if (bogieIds.contains(b.id)) {
            System.out.println("❌ Duplicate Bogie ID! Cannot add.");
            return;
        }

        bogies.add(b);
        bogieIds.add(b.id);
        System.out.println("✅ Bogie added successfully!");
    }

    // Remove bogie
    void removeBogie(String id) {
        Iterator<PassengerBogie> it = bogies.iterator();
        boolean found = false;

        while (it.hasNext()) {
            PassengerBogie b = it.next();
            if (b.id.equals(id)) {
                it.remove();
                bogieIds.remove(id); // remove from set also
                found = true;
                System.out.println("✅ Bogie removed!");
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Bogie not found!");
        }
    }

    // Display
    void displayAll() {
        System.out.println("\nTrain: " + trainName);

        if (bogies.isEmpty()) {
            System.out.println("No bogies available.");
            return;
        }

        for (PassengerBogie b : bogies) {
            b.display();
        }
    }
}

// Main class
public class TrainConsistUC3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Train train = new Train("Express 101");

        int choice;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Bogie");
            System.out.println("2. Remove Bogie");
            System.out.println("3. Display Bogies");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = sc.next();

                    System.out.print("Enter Category: ");
                    String cat = sc.next();

                    System.out.print("Enter Capacity: ");
                    int cap = sc.nextInt();

                    train.addBogie(new PassengerBogie(id, cat, cap));
                    break;

                case 2:
                    System.out.print("Enter ID to remove: ");
                    train.removeBogie(sc.next());
                    break;

                case 3:
                    train.displayAll();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}