import java.util.ArrayList;
import java.util.Stack;

public class DFS {
    public static void search(int[] board) {
        State startingState = new State(board);
        Node root = new Node(startingState);

        Stack<Node> stack = new Stack<>();

        stack.push(root);

        findGoal(stack);
    }

    private static boolean hasBeenSeenBefore(Node node) {
        Node currentNode = node.getParent();

        while (currentNode != null) {
            if (currentNode.getState().equals(node.getState())) {
                return true;
            }

            currentNode = currentNode.getParent();
        }

        return false;
    }

    public static void findGoal(Stack<Node> stack) {
        int iterations = 1;

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            // The currentNode is not equal to the GOAL int[]
            if (!currentNode.getState().isGoal()) {
                ArrayList<State> children = currentNode.getState().generateChildren();

                for (State child : children) {
                    Node newNode = new Node(child, currentNode, currentNode.getCost() + child.getCost());

                    if (!hasBeenSeenBefore(newNode)) {
                        stack.push(newNode);
                    }
                }
            }
            // The currentNode is equal to the GOAL int[]
            else {
                //System.out.println("Found solution!");
                Stack<Node> solutionPath = new Stack<>();

                solutionPath.push(currentNode);
                currentNode = currentNode.getParent();

                while (currentNode != null) {
                    solutionPath.push(currentNode);
                    currentNode = currentNode.getParent();
                }

                // Printing the solution path
                // Root Node will now be at the top of the stack followed by its descendants
                while (!solutionPath.isEmpty()) {
                    currentNode = solutionPath.pop();
                    currentNode.getState().printState();
                    System.out.println("\n");
                }

                // currentNode now holds the solution State which will have the total cost of the path
                System.out.println("Total cost of path using DFS: " + currentNode.getCost());

                // Terminates the search due to the solution having been found
                System.exit(0);
            }
        }
    }
}