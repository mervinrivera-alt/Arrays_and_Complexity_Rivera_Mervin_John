import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Main.java – Interactive driver for ScoreArrayManager.
 * Uses the exact method signatures from ScoreArrayManager (void returns,
 * exception-based validation). All numeric input is validated; invalid
 * entries (words, etc.) are discarded and the user is reprompted.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Create ScoreArrayManager with fixed capacity of 5
        ScoreArrayManager manager = new ScoreArrayManager(5);

        // 2. Initialize active values [72, 90, 84] using insertAt()
        manager.insertAt(0, 72);
        manager.insertAt(1, 90);
        manager.insertAt(2, 84);

        // 3. Display initial array, size, and capacity immediately
        displayState(manager);

        // 4. Scanner-based interactive menu
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Display");
            System.out.println("2. Insert");
            System.out.println("3. Delete");
            System.out.println("4. Update");
            System.out.println("5. Exit");
            System.out.print("Choose option (1-5): ");

            int choice = readChoice(scanner);
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid option – please choose 1‑5.");
                continue;
            }

            switch (choice) {
                case 1:
                    displayState(manager);
                    break;

                case 2: // Insert
                    if (manager.getSize() >= manager.getCapacity()) {
                        System.out.println("Array is full – cannot insert.");
                        break;
                    }
                    int insIdx = readIndex(scanner, "Enter index to insert at (0-" + manager.getSize() + ")", 0, manager.getSize());
                    int insVal = readValue(scanner, "Enter value to insert: ");
                    try {
                        manager.insertAt(insIdx, insVal);
                        System.out.println("Inserted " + insVal + " at index " + insIdx);
                        displayState(manager);
                    } catch (Exception e) {
                        System.out.println("Error during insert: " + e.getMessage());
                    }
                    break;

                case 3: // Delete
                    if (manager.getSize() == 0) {
                        System.out.println("Array is empty – nothing to delete.");
                        break;
                    }
                    int delIdx = readIndex(scanner, "Enter index to delete (0-" + (manager.getSize() - 1) + ")", 0, manager.getSize() - 1);
                    try {
                        manager.deleteAt(delIdx);
                        System.out.println("Deleted element at index " + delIdx);
                        displayState(manager);
                    } catch (Exception e) {
                        System.out.println("Error during delete: " + e.getMessage());
                    }
                    break;

                case 4: // Update
                    if (manager.getSize() == 0) {
                        System.out.println("Array is empty – nothing to update.");
                        break;
                    }
                    int updIdx = readIndex(scanner, "Enter index to update (0-" + (manager.getSize() - 1) + ")", 0, manager.getSize() - 1);
                    int updVal = readValue(scanner, "Enter new value: ");
                    try {
                        manager.updateScore(updIdx, updVal);
                        System.out.println("Updated index " + updIdx + " to " + updVal);
                        displayState(manager);
                    } catch (Exception e) {
                        System.out.println("Error during update: " + e.getMessage());
                    }
                    break;

                case 5: // Exit
                    running = false;
                    System.out.println("Good‑bye!");
                    break;
            }
        }

        scanner.close();
    }

    /** Read menu choice 1‑5 from a line of input, reprompting on bad input. */
    private static int readChoice(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                int c = Integer.parseInt(line);
                if (c >= 1 && c <= 5) return c;
                System.out.print("Please enter 1‑5: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid choice – please enter 1‑5: ");
            }
        }
    }

    /**
     * Read an index in [min, max] from the user, reprompting until a valid
     * integer within the range is entered. Uses nextLine() so stray tokens
     * don't accumulate.
     */
    private static int readIndex(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + ": ");
            String line = scanner.nextLine().trim();
            try {
                int idx = Integer.parseInt(line);
                if (idx >= min && idx <= max) return idx;
                System.out.println("Index must be between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid index – please enter an integer between " + min + " and " + max + ".");
            }
        }
    }

    /** Read an integer value from the user, reprompting on bad input. */
    private static int readValue(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value – please enter an integer.");
            }
        }
    }

    /** Print the current active elements, size, and capacity. */
    private static void displayState(ScoreArrayManager manager) {
        System.out.println("\n--- Current State ---");
        System.out.println("Active elements: " + manager.getSize());
        System.out.println("Capacity: " + manager.getCapacity());
        manager.display();
        if (manager.getSize() > 0) {
            int[] scores = manager.getScores();
            int size = manager.getSize();
            System.out.println("First valid index (0): " + scores[0]);
            System.out.println("Last valid index (" + (size - 1) + "): " + scores[size - 1]);
        }
    }
}