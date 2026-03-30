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
public class TrainConsistApp {
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