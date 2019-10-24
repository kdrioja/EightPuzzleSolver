import java.util.ArrayList;
import java.util.Arrays;

public class State {
    /**
     * A 1-D array represents the 2-D puzzle board to avoid dealing
     * with both a row and a column index
     */
    private final int[] GOAL = {1, 2, 3,
                                8, 0, 4,
                                7, 6, 5};
    private final int PUZZLE_LENGTH = 9;
    private int[] board;
    private int notInPlace;

    /**
     * Constructor
     * @param board
     */
    public State(int[] board) {
        this.board = board;
        this.notInPlace = countOutOfPlace();
    }

    /**
     * Getter for the board member variable
     * @return this State's board
     */
    public int[] getBoard() {
        return this.board;
    }

    /**
     * Getter for the notInPlace member variable
     * @return this State's notInPlace count
     */
    public int getNotInPlace() {
        return this.notInPlace;
    }
    /**
     * Counts the number of tiles that are not in their GOAL position
     * @return count of out of place tiles
     */
    private int countOutOfPlace() {
        int count = 0;

        for (int i = 0; i < PUZZLE_LENGTH; i++) {
            if (GOAL[i] != this.board[i]) {
                count++;
            }
        }

        return count;
    }

    /**
     * Finds the index of the 0 in our board
     * @return location of the hole/0 or -1 if no 0 is found in the board
     */
    private int getIndexOfZero() {
        for (int i = 0; i < PUZZLE_LENGTH; i++) {
            if (this.board[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Helper function that makes a copy of the current board
     * in order to create its children
     * @return copy of the current board
     */
    private int[] makeCopyOfBoard() {
        int[] copy = new int[PUZZLE_LENGTH];

        for (int i = 0;  i < PUZZLE_LENGTH; i++) {
            copy[i] = this.board[i];
        }

        return copy;
    }

    /**
     * Determines whether two states are the same
     * @param other
     * @return boolean
     */
    public boolean equals(State other) {
        /**
        if (Arrays.equals(this.board, other.board)) {
            return true;
        }
        else {
            return false;
        }**/
        return Arrays.equals(this.board, other.board);
    }

    /**
     * Prints the State object
     */
    public void printState() {
        System.out.println(this.board[0] + " | " + this.board[1] + " | " + this.board[2]);
        System.out.println("-- --- --");
        System.out.println(this.board[3] + " | " + this.board[4] + " | " + this.board[5]);
        System.out.println("-- --- --");
        System.out.println(this.board[6] + " | " + this.board[7] + " | " + this.board[8]);
    }

    /**
     *
     * @return
     */
    public int getCost() {
        return 1;
    }

    /**
     * Checks if current board is the same as our GOAL board
     * @return
     */
    public boolean isGoal() {
        return Arrays.equals(this.board, GOAL);
    }

    /**
     * Genrates all of the possible children from the current state's 0 position
     * @return ArrayList containing all of the current State's children
     */
    public ArrayList<State> generateChildren() {
        ArrayList<State> children = new ArrayList<>();
        int zeroIndex = getIndexOfZero();

        // Move Left
        // Indices that cannot move left: 0, 3, 6
        if (zeroIndex != 0 && zeroIndex != 3 && zeroIndex != 6) {
            children.add(makeChild(zeroIndex, zeroIndex - 1));
        }

        // Move Right
        // Indices that cannot move right: 2, 5, 8
        if (zeroIndex != 2 && zeroIndex != 5 && zeroIndex != 8) {
            children.add(makeChild(zeroIndex, zeroIndex + 1));
        }

        // Move Up
        // Indices that cannot move up: 0, 1, 2
        if (zeroIndex != 0 && zeroIndex != 1 && zeroIndex != 2) {
            children.add(makeChild(zeroIndex, zeroIndex - 3));
        }

        // Move Down
        // Indices that cannot move down: 6, 7, 8
        if (zeroIndex != 6 && zeroIndex != 7 && zeroIndex != 8) {
            children.add(makeChild(zeroIndex, zeroIndex + 3));
        }

        return children;
    }

    /**
     * Helper function for generateChildren that creates the new State from the shift
     * @param zeroIndex indicates the index of 0 in the current board
     * @param targetIndex indicates the tile which will be swapped with the 0 tile
     * @return a new State resulting from the swapping of tiles
     */
    private State makeChild(int zeroIndex, int targetIndex) {
        // Make copy of board
        int[] copyOfBoard = makeCopyOfBoard();

        // Swap the two elements at the indices given
        copyOfBoard[zeroIndex] = copyOfBoard[targetIndex];
        copyOfBoard[targetIndex] = 0;

        return new State(copyOfBoard);
    }
}
