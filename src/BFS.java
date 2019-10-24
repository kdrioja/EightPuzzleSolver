import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {
    public static void search(int[] board) {
        State startingState = new State(board);
        Node root = new Node(startingState);

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        findGoal(queue);
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

    public static void findGoal(Queue<Node> queue) {
        int iterations = 1;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            // The currentNode is not equal to the GOAL int[]
            if (!currentNode.getState().isGoal()) {
                ArrayList<State> children = currentNode.getState().generateChildren();

                for (State child : children) {
                    Node newNode = new Node(child, currentNode, currentNode.getCost() + child.getCost());

                    if (!hasBeenSeenBefore(newNode)) {
                        queue.add(newNode);
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
                System.out.println("Total cost of path using BFS: " + currentNode.getCost());

                // Terminates the search due to the solution having been found
                System.exit(0);
            }
        }
    }
}