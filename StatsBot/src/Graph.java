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

        public void connect(Node other) {
            connections.add(other);
            other.connections.add(this);
        }
    } 

    private final HashSet<Node> nodes = new HashSet<>();

    public Node addNode(int x, int y) {
       return new Node(x, y);
    }

    public Node addNode(Point point) {
        return addNode(point.x, point.y);
    }

    public Optional<Node> find(int x, int y) {
        for (Node node : nodes) {
            if (node.x == x && node.y == y) {
                return Optional.of(node); 
            }
        }
        return Optional.empty();
    }

    public Optional<Node> find(Point point) {
        return find(point.x, point.y);
    }
}
