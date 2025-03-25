import java.util.HashSet;
import java.util.Optional;

public class Graph {
    public class Node {
        public final int x, y;
        public HashSet<Node> connections = new HashSet<>();
        public int value = 0;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            nodes.add(Node.this);
        } 

        /**
         *  conncts nodes
         * @param other node to connect with the current node, and so in other way
         */
        public void connect(Node other) {
            connections.add(other);
            other.connections.add(this);
        }
    } 

    private final HashSet<Node> nodes = new HashSet<>();

    /**
     * adds a point to the graph as node 
     * @param x x-coordinate of a point
     * @param y y-coordinate of a point
     * @return new node
     */
    public Node addNode(int x, int y) {
       return new Node(x, y);
    }

    /**
     * adds a point to the graph as node
     * @param point point to add as node to the graph
     * @return new node
     */
    public Node addNode(Point point) {
        return addNode(point.x, point.y);
    }

    /**
     * finds node with coordinates of its point 
     * @param x x-coordinate of node to find
     * @param y y-coordinate of node to find
     * @return node found or null if not found
     */
    public Optional<Node> find(int x, int y) {
        for (Node node : nodes) {
            if (node.x == x && node.y == y) {
                return Optional.of(node); 
            }
        }
        return Optional.empty();
    }

    /**
     * find a node with its point
     * @param point the point of node
     * @return node found or null if not found
     */
    public Optional<Node> find(Point point) {
        return find(point.x, point.y);
    }
}
