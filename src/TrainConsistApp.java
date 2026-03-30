
import java.util.*;

// Abstract Bogie class
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
    int capacity;
    String category; // Sleeper, AC, First Class

    PassengerBogie(String id, String category, int capacity) {
        super(id, "Passenger");
        this.category = category;
        this.capacity = capacity;
    }

    void display() {
        System.out.println("Bogie ID: " + id +
                " | Type: " + type +
                " | Category: " + category +
                " | Capacity: " + capacity);
    }
}

// Goods Bogie
class GoodsBogie extends Bogie {
    String shape; // Rectangular, Cylindrical
    String cargoType;

    GoodsBogie(String id, String shape, String cargoType) {
        super(id, "Goods");
        this.shape = shape;
        this.cargoType = cargoType;
    }

    void display() {
        System.out.println("Bogie ID: " + id +
                " | Type: " + type +
                " | Shape: " + shape +
                " | Cargo: " + cargoType);
    }
}

// Train class
class Train {
    String trainName;
    List<Bogie> bogies;

    Train(String trainName) {
        this.trainName = trainName;
        this.bogies = new ArrayList<>();
    }

    void addBogie(Bogie b) {
        bogies.add(b);
    }

    void displaySummary() {
        System.out.println("\n=== Train Consist Summary ===");
        System.out.println("Train Name: " + trainName);
        System.out.println("Total Bogies: " + bogies.size());
        System.out.println("-----------------------------");

        for (Bogie b : bogies) {
            b.display();
        }
    }
}

// Main class
public class TrainConsistApp {
    public static void main(String[] args) {

        // Initialize Train
        Train train = new Train("Express 101");

        // Add Passenger Bogies
        train.addBogie(new PassengerBogie("P1", "Sleeper", 72));
        train.addBogie(new PassengerBogie("P2", "AC Chair", 50));
        train.addBogie(new PassengerBogie("P3", "First Class", 30));

        // Add Goods Bogies
        train.addBogie(new GoodsBogie("G1", "Rectangular", "Coal"));
        train.addBogie(new GoodsBogie("G2", "Cylindrical", "Oil"));

        // Display Summary
        train.displaySummary();
    }
}