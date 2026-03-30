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

// Train class using TreeSet (SortedSet)
class Train {
    String trainName;
    ArrayList<PassengerBogie> bogies;
    TreeSet<String> sortedIds; // maintains sorted unique IDs

    Train(String trainName) {
        this.trainName = trainName;
        bogies = new ArrayList<>();
        sortedIds = new TreeSet<>();
    }

    // Add bogie
    void addBogie(PassengerBogie b) {
        if (!sortedIds.add(b.id)) {
            System.out.println("❌ Duplicate ID! Not allowed.");
            return;
        }

        bogies.add(b);
        System.out.println("✅ Bogie added!");
    }

    // Remove bogie
    void removeBogie(String id) {
        Iterator<PassengerBogie> it = bogies.iterator();
        boolean found = false;

        while (it.hasNext()) {
            PassengerBogie b = it.next();
            if (b.id.equals(id)) {
                it.remove();
                sortedIds.remove(id);
                found = true;
                System.out.println("✅ Bogie removed!");
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Bogie not found!");
        }
    }

    // Display sorted IDs
    void displaySortedIds() {
        System.out.println("\nSorted Bogie IDs:");
        for (String id : sortedIds) {
            System.out.println(id);
        }
    }

    // Display full details
    void displayAll() {
        System.out.println("\nTrain: " + trainName);

        if (bogies.isEmpty()) {
            System.out.println("No bogies.");
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
            System.out.println("3. Display All");
            System.out.println("4. Display Sorted IDs");
            System.out.println("5. Exit");
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
                    train.displaySortedIds();
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