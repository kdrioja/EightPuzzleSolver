public class Node {
    private State state;
    private Node parent;
    private int cost;

    public Node(State state) {
        this.state = state;
        this.parent = null;
        this.cost = 0;
    }

    public Node(State state, Node parent, int cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
    }

    public State getState() {
        return this.state;
    }

    public Node getParent() {
        return this.parent;
    }

    public int getCost() {
        return this.cost;
    }


}
