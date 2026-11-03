
# Array Operations and Complexity Basics

## Table of Contents

1. [Project Overview](#1-project-overview)
2. [Array Design](#2-array-design)
3. [Manual Trace](#3-manual-trace)
4. [Test Evidence](#4-test-evidence)
5. [AI-Use Declaration](#5-ai-use-declaration)
   - [AI Tool and Model](#ai-tool-and-model)
   - [Prompts Used](#prompts-used)
   - [AI Suggestions Adopted](#ai-suggestions-adopted)
   - [AI Suggestions Corrected or Rejected](#ai-suggestions-corrected-or-rejected)
   - [Verification](#verification)
6. [Individual Viva Question](#6-individual-viva-question)

---

## 1. Project Overview

This project demonstrates basic array operations using Java. It uses a fixed-size array with a capacity of five elements and supports the following operations:

- **Insert:** Adds a new value at a selected index while shifting existing elements to the right.
- **Update:** Replaces the value at a selected index.
- **Delete:** Removes a value at a selected index while shifting the following active elements to the left.
- **Display:** Shows the current active elements, size, and capacity.

A `Scanner` menu is used to accept user input and validate numerical entries and array indexes.

## 2. Array Design

The program follows the contiguous active-elements approach.

**Initial array:**

```text
Index:      0    1    2    3    4
Array:     [72,  90,  84,   0,   0]
```

| Property | Value |
|---|---|
| Capacity | 5 |
| Initial size | 3 |
| First valid index | 0 |
| Last valid index | size - 1 = 2 |
| Active elements | 72, 90, 84 |
| Unused indexes | 3 and 4 |

**Important rules:**

- Active elements must always occupy indexes `0` through `size - 1`.
- Insertion is allowed at indexes `0` through `size`.
- Deletion and updating are allowed only at indexes `0` through `size - 1`.
- Insertion shifts elements to the right.
- Deletion shifts elements to the left.
- The array cannot exceed its capacity of five elements.

## 3. Manual Trace

**Required task:** Show the array before and after inserting `88` at index `1` into `[72, 90, 84, 0, 0]`. Mark every shifted value.

### Initial Array

```text
Index:      0    1    2    3    4
Array:     [72,  90,  84,   0,   0]

Size = 3
Capacity = 5
```

**Insertion operation:**

```java
for (int i = size - 1; i >= index; i--) {
    scores[i + 1] = scores[i];
}
```

The operation starts at the last active element and shifts values from right to left.

### Step-by-Step Trace

| Step | Operation | Array after operation |
|---|---|---|
| Initial | Original array | `[72, 90, 84, 0, 0]` |
| 1 | `scores[3] = scores[2]` (84 moves from index 2 to 3) | `[72, 90, 84, 84, 0]` |
| 2 | `scores[2] = scores[1]` (90 moves from index 1 to 2) | `[72, 90, 90, 84, 0]` |
| 3 | Loop stops because `0 >= 1` is false | `[72, 90, 90, 84, 0]` |
| 4 | `scores[1] = 88` | `[72, 88, 90, 84, 0]` |
| 5 | `size++` | Size changes from 3 to 4 |

### Final Array

```text
Index:      0    1    2    3    4
Array:     [72,  88,  90,  84,   0]

Size = 4
Capacity = 5
```

**Explanation:**

The value `84` moves from index 2 to index 3, while `90` moves from index 1 to index 2. After space is created, the new value `88` is placed at index 1.

The size increases from 3 to 4 because one new active element has been inserted.

### Accessing the First and Last Valid Indexes

Before insertion:

```text
First valid index = 0
scores[0] = 72

Last valid index = size - 1 = 2
scores[2] = 84
```

After insertion:

```text
First valid index = 0
scores[0] = 72

Last valid index = size - 1 = 3
scores[3] = 84
```

This demonstrates that the last valid index changes whenever the number of active elements changes.

## 4. Test Evidence

The following test cases cover the required operations and conditions specified in the lab instructions.

| Test case | Input / action | Expected | Actual result |
|---|---|---|---|
| Access first and last valid indexes | Access index `0` and index `size - 1` | Returns `72` and `84`, respectively, from the initial array | Pending actual test |
| Insert at index 0 | Insert `10` at index `0` | `[10, 72, 90, 84]`, size = 4 | Pending actual test |
| Insert in the middle | Insert `88` at index `1` | `[72, 88, 90, 84]`, size = 4 | Pending actual test |
| Delete the final active element | Delete index `2` from the initial array | `[72, 90]`, size = 2 | Pending actual test |
| Reject an invalid index | Attempt an operation using an invalid index | Reject the operation and display an error message | Pending actual test |
| Reject insertion when full | Fill all five slots and attempt another insertion | Reject insertion and display an array-full message | Pending actual test |

**Testing note:** Each pending actual result should be replaced with the result observed when running the program. For the full-array test, two additional values must first be inserted into the initial three-element array before another insertion is attempted.

## 5. AI-Use Declaration

### AI Tool and Model

| Item | Details |
|---|---|
| AI tool | OpenCode |
| Model recorded in Agent.log | Nemotron 3.5 Lightning Free |
| Purpose | Algorithm design, Java implementation assistance, debugging, and input validation |
| Instructor approval | To be confirmed with the instructor |

### Prompts Used

The following are the main prompts used during development. The complete interaction history is retained in `Agent.log`.

**Prompt 1: Algorithm design**

> Act as a Java tutor. Help me design pseudocode for Arrays and Complexity Basics. Do not write full code until I explain the algorithm back to you.

**Prompt 2: Implementing insertion and deletion**

> Now that the `insertAt()` and `deleteAt()` algorithms are finalized, implement both methods in the existing Java code using the contiguous active-array design, where `size` tracks the valid elements from index 0 to size - 1 and `capacity` is the total array length.

**Prompt 3: Interactive Scanner menu**

> Modify the existing Java program by removing all hard-coded testing code and replacing `main()` with a Scanner interactive menu for Display, Insert, Delete, Update, and Exit, with numeric input validation and index validation that preserves the contiguous active-array design.

**Prompt 4: Creating Main.java**

> Inspect my existing `ScoreArrayManager.java` and create a new file named `Main.java` in the same directory, without modifying or replacing any code in `ScoreArrayManager.java`; use its actual method signatures, initialize the starting active values to `[72, 90, 84]`, and create a Scanner-based menu with Display, Insert, Delete, Update, and Exit options.

**Prompt 5: Handling empty arrays**

> Modify the existing Java program to fix the empty-array case without changing the current `insertAt()`, `deleteAt()`, or `updateScore()` algorithms, and display a clear message when Delete or Update is selected while the array is empty.

### AI Suggestions Adopted

The following AI suggestions were incorporated into the final implementation:

1. A `size` variable tracks the number of active elements separately from the array's capacity.
2. Elements shift right-to-left during insertion to avoid overwriting existing values.
3. Elements shift left-to-right during deletion to fill the gap left by a removed element.
4. Insertion at index `size` is allowed to support appending a value.
5. Deletion and updating are restricted to active indexes only.
6. `Scanner` input validation prevents non-numeric input from crashing the program.
7. `try-catch` handles exceptions from the existing `ScoreArrayManager` methods.
8. The updated array is displayed after each successful modification.

### AI Suggestions Corrected or Rejected

Some initial AI suggestions required correction to match the existing Java code and the required array design.

1. **Incorrect deletion explanation:** An early explanation suggested moving the unused element at index 3 into index 2 when deleting the final active element. This was corrected because deleting the final active element does not require shifting when there are no active elements after it.
2. **Incorrect method return assumptions:** An earlier `Main.java` draft treated `insertAt()`, `deleteAt()`, and `updateScore()` as methods returning `boolean`. The existing `ScoreArrayManager.java` methods actually return `void`, so the final implementation calls them directly and uses `try-catch` for exception handling.
3. **Scanner input handling:** Initial use of `nextInt()` encountered input-reading problems in the OpenCode execution environment. The final version uses `nextLine()` with `Integer.parseInt()` for input validation.

### Verification

The following verification steps were recorded:

- The existing `ScoreArrayManager.java` was inspected to confirm its actual method signatures.
- `Main.java` was created separately, keeping the original `ScoreArrayManager.java` unchanged.
- The command `javac ScoreArrayManager.java Main.java` completed without compilation errors in the recorded OpenCode session.
- OpenCode could not complete an interactive console test because its execution environment did not provide interactive standard input.
- The program was subsequently reported as functional in VS Code.
- Individual test cases require verification and supporting console screenshots before final submission.

## 6. Individual Viva Question

**Question: Why is `arr[index]` O(1), while inserting at index 0 is O(n)?**

Accessing `arr[index]` is O(1), or constant time, because the computer can directly locate an element using its index without checking other elements. Inserting at index 0 is O(n), or linear time, because all existing active elements must shift one position to the right to create space for the new value. Therefore, accessing an element takes constant time, while inserting at the beginning takes time proportional to the number of active elements.

---
