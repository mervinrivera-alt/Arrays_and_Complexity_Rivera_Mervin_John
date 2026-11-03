public class ScoreArrayManager {
    // ---------- fields ----------
    private int[] scores;   // fixed‑size container for the quiz scores
    private int capacity;   // maximum number of scores the array can hold
    private int size;       // how many slots are currently occupied (0 ≤ size ≤ capacity)

    // ---------- constructor ----------
    public ScoreArrayManager(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = maxSize;
        this.scores = new int[maxSize];
        this.size = 0;
    }

    // ---------- optional getters (will be used by the methods) ----------
    public int getCapacity() { return capacity; }
    public int getSize()      { return size; }
    public int[] getScores() { return scores; }

    // ---------- display() ----------
    public void display() {
        System.out.print("Scores: ");
        for (int i = 0; i < size; i++) {
            System.out.print(scores[i] + " ");
        }
        System.out.println();
    }

    // ---------- updateScore() ----------
    public void updateScore(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(
                "Index " + index + " is out of bounds (valid 0‑" + (size - 1) + ")");
        }
        scores[index] = value;   // O(1) direct write
    }

    // ---------- insertAt() ----------
    public void insertAt(int index, int value) {
        // 1️⃣ Validate index: must be within the logical range [0, size]
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(
                "Invalid index: " + index + " (valid range: 0‑" + size + ")");
        }
        // 2️⃣ Validate capacity: there must be an empty slot
        if (size == capacity) {
            throw new IllegalStateException("Array is full – cannot insert");
        }

        // 3️⃣ Shift elements one position to the right, starting from the last real element
        for (int i = size - 1; i >= index; i--) {
            scores[i + 1] = scores[i];   // move element at i → i+1
        }

        // 4️⃣ Place the new value at the freed slot
        scores[index] = value;

        // 5️⃣ Increase the logical size
        size++;
    }
    // ---------- deleteAt() ----------
    public void deleteAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(
                "Invalid index: " + index + " (valid range: 0‑" + (size - 1) + ")");
        }
        for (int i = index; i < size - 1; i++) {
            scores[i] = scores[i + 1];
        }
        size--;
    }
}