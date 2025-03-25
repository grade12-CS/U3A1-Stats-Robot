import becker.robots.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MazeBot extends RobotAdvanced{
    private final City field;

    public MazeBot(Cijy city, int x, int y, Direction direction, int things) {
        super(city, y, x, direction, things);
        this.field = city;
    }

    public MazeBot(City city, int x, int y) {
        super(city, y, x, Direction.NORTH, 0);
        this.field = city;
    } 

    private List<Point> thingPoints = new ArrayList<>(); 

    public Graph scan(final int startX, final int startY, final int avenues, final int streets) {
        Direction[] northAndWest = { Direction.NORTH, Direction.WEST };
        Graph graph = new Graph();
        for (int x = startX; x < avenues; ++x) {
            for (int y = startY; y < streets; ++y) {
                //creates and add a node to graph for each point on the map
                Point currentPoint = new Point(x, y);
                Graph.Node currentNode = graph.addNode(x, y);
                for (Direction direction : northAndWest) {
                    //creats a dummy robot to create paths
                    Robot scanner = new Robot(field, y, x, direction);        
                    scanner.setTransparency(0.9);
                    if (scanner.frontIsClear()) {
                        //if the dummy bot's front is clear, connet current node to the node in the front
                        Point frontPoint = currentPoint.get(direction); 
                        var frontNode = graph.find(frontPoint.x, frontPoint.y);
                        if (frontNode.isPresent()) {
                            currentNode.connect(frontNode.get());
                        }
                    }
                    //checks for thing location
                    if (scanner.canPickThing()) {
                        thingPoints.add(new Point(x, y));
                    }
                }
            }
        }
        return graph;
    } 

    public void solve(Graph.Node start, Graph.Node end, Graph graph) {
        LinkedList<Graph.Node> toVisit = new LinkedList<>();
        HashSet<Graph.Node> visited = new HashSet<>();
        toVisit.add(end);
        int dist = 0;
        while (!toVisit.isEmpty()) {
            Graph.Node visiting = toVisit.remove();
            visiting.value = ++dist;
            for (Graph.Node node : visiting.connections) {
                if (!visited.contains(node)) {
                    toVisit.add(node);
                }
            }
            visited.add(visiting);
        }

        Graph.Node move = start; 
        while (move != end) {
            int shortest = dist;
            for (Graph.Node node : move.connections) {
                if (node.value < shortest) {
                    shortest = node.value;
                    move = node;
                }
            }
            goTo(move.x, move.y);
        }
    }

    public void solve(Point start, Point end, Graph graph) {
        var startNode = graph.find(start);
        var endNode = graph.find(end);
        if (startNode.isPresent() && endNode.isPresent()) {
            solve(startNode.get(), endNode.get(), graph);
        } else if (startNode.isEmpty()) {
            System.out.println("start node does not exist in the given graph!");
        } else if (endNode.isEmpty()) {
            System.out.println("end node does not exist in the given graph!");
        }
    }

    public void solve(Point end, Graph graph) {
        Point current = getCurrentPoint();
        solve(current, end, graph);
    }

    public void solve(int avenues, int streets) {
        final Point home = getCurrentPoint();
        Graph graph = scan(0, 0, avenues, streets);
        
        while (!thingPoints.isEmpty()) {
            Point thingPoint = thingPoints.removeLast();
            solve(thingPoint, graph);
            pickAllThings(); 
        }
        solve(home, graph);
    }
}
