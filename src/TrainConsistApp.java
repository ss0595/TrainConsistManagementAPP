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
import java.util.*;

class Bogie {
    private String id;
    private String type;

    public Bogie(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " (" + type + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bogie)) return false;
        Bogie bogie = (Bogie) o;
        return id.equals(bogie.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

class TrainConsistManagementApp {

    // Maintains insertion order + uniqueness
    private LinkedHashSet<Bogie> bogieSet = new LinkedHashSet<>();

    // Stack to enforce LIFO removal
    private Deque<Bogie> stack = new ArrayDeque<>();

    // Attach bogie
    public void attachBogie(Bogie bogie) {
        if (bogieSet.contains(bogie)) {
            System.out.println("Bogie already exists: " + bogie);
            return;
        }

        bogieSet.add(bogie);
        stack.push(bogie); // LIFO tracking

        System.out.println("Attached: " + bogie);
    }

    // Remove last attached bogie (LIFO)
    public void detachLastBogie() {
        if (stack.isEmpty()) {
            System.out.println("No bogies to remove.");
            return;
        }

        Bogie last = stack.pop();
        bogieSet.remove(last);

        System.out.println("Detached (LIFO): " + last);
    }

    // Display train composition
    public void displayConsist() {
        System.out.println("\nCurrent Train Consist (Insertion Order):");
        for (Bogie b : bogieSet) {
            System.out.println(b);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TrainConsistManagementApp manager = new TrainConsistManagementApp();

        Bogie b1 = new Bogie("B1", "Sleeper");
        Bogie b2 = new Bogie("B2", "AC Chair");
        Bogie b3 = new Bogie("B3", "Goods");

        manager.attachBogie(b1);
        manager.attachBogie(b2);
        manager.attachBogie(b3);

        manager.displayConsist();

        // LIFO removal
        manager.detachLastBogie();

        manager.displayConsist();
    }
}
import java.util.*;

// Bogie class
class Bogie {
    private String id;
    private String type;

    public Bogie(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + " (" + type + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bogie)) return false;
        Bogie bogie = (Bogie) o;
        return id.equals(bogie.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

// Manager class
class TrainConsistManager {

    // Map Bogie -> Capacity
    private HashMap<Bogie, Integer> capacityMap = new HashMap<>();

    // Add bogie with capacity
    public void addBogie(Bogie bogie, int capacity) {
        if (capacityMap.containsKey(bogie)) {
            System.out.println("Bogie already exists: " + bogie);
            return;
        }

        capacityMap.put(bogie, capacity);
        System.out.println("Added: " + bogie + " | Capacity: " + capacity);
    }

    // Remove bogie
    public void removeBogie(Bogie bogie) {
        if (!capacityMap.containsKey(bogie)) {
            System.out.println("Bogie not found: " + bogie);
            return;
        }

        capacityMap.remove(bogie);
        System.out.println("Removed: " + bogie);
    }

    // Display all bogies with capacity
    public void displayAll() {
        System.out.println("\nTrain Bogie Capacity Details:");
        for (Map.Entry<Bogie, Integer> entry : capacityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> Capacity: " + entry.getValue());
        }
    }

    // Get total capacity
    public int getTotalCapacity() {
        int total = 0;
        for (int cap : capacityMap.values()) {
            total += cap;
        }
        return total;
    }

    // Validate capacity (example rule)
    public void validateCapacityLimit(int maxLimit) {
        int total = getTotalCapacity();
        if (total > maxLimit) {
            System.out.println("⚠ Capacity exceeded! Total: " + total);
        } else {
            System.out.println("✅ Capacity within limit. Total: " + total);
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        TrainConsistManager manager = new TrainConsistManager();

        Bogie b1 = new Bogie("B1", "Sleeper");
        Bogie b2 = new Bogie("B2", "AC Chair");
        Bogie b3 = new Bogie("B3", "Goods");

        manager.addBogie(b1, 72);   // seats
        manager.addBogie(b2, 60);   // seats
        manager.addBogie(b3, 100);  // load capacity

        manager.displayAll();

        System.out.println("\nTotal Capacity: " + manager.getTotalCapacity());

        manager.validateCapacityLimit(200);
    }
}
import java.util.*;

// Bogie class
class Bogie {
    private String id;
    private String type;

    public Bogie(String id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return id + " (" + type + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bogie)) return false;
        Bogie bogie = (Bogie) o;
        return id.equals(bogie.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

// Manager class
class TrainConsistManager {

    private HashMap<Bogie, Integer> capacityMap = new HashMap<>();

    public void addBogie(Bogie bogie, int capacity) {
        capacityMap.put(bogie, capacity);
    }

    // Sort bogies by capacity (ascending or descending)
    public void sortBogiesByCapacity(boolean descending) {

        List<Map.Entry<Bogie, Integer>> list =
                new ArrayList<>(capacityMap.entrySet());

        // Custom Comparator
        list.sort((e1, e2) -> {
            if (descending) {
                return e2.getValue() - e1.getValue(); // high → low
            } else {
                return e1.getValue() - e2.getValue(); // low → high
            }
        });

        System.out.println("\nBogies sorted by capacity (" +
                (descending ? "High to Low" : "Low to High") + "):");

        for (Map.Entry<Bogie, Integer> entry : list) {
            System.out.println(entry.getKey() +
                    " -> Capacity: " + entry.getValue());
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        TrainConsistManager manager = new TrainConsistManager();

        Bogie b1 = new Bogie("B1", "Sleeper");
        Bogie b2 = new Bogie("B2", "AC Chair");
        Bogie b3 = new Bogie("B3", "Goods");

        manager.addBogie(b1, 72);
        manager.addBogie(b2, 56);
        manager.addBogie(b3, 100);

        // Sort descending (most useful in real-world)
        manager.sortBogiesByCapacity(true);

        // Sort ascending
        manager.sortBogiesByCapacity(false);
    }
}