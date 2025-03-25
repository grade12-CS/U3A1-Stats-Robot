import becker.robots.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MazeBot extends StatsBot{
    /**
     * the city in which the robot is created
     */
    private final City field;

    public MazeBot(City city, int x, int y) {
        super(city, y, x);
        this.field = city;
    } 

    private final List<Point> thingPoints = new ArrayList<>(); 

    /**
     * scan current map and generate a graph
     * @param startX x-coordinate of robot's starting point
     * @param startY y-coordinate of robot's starting point
     * @param avenues the number of columns of the map
     * @param streets the number of rows of the map
     * @return graph generated from the map
     */
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

    /**
     * solve the given graph, using depth-first search algorithm
     * @param start the node to start moving in the graph
     * @param end the node to end moving in the graph
     * @param graph graph to solve
     */
    public void solve(Graph.Node start, Graph.Node end, Graph graph) {
        LinkedList<Graph.Node> toVisit = new LinkedList<>();
        HashSet<Graph.Node> visited = new HashSet<>();
        toVisit.add(end);
        int dist = 0; //spaces to walk
        //checks for avaialbe paths
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

        //take the shortest path to the end point 
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

    /**
     * solve the given graph
     * @param start starting point, given that the point is in the graph 
     * @param end ending point, given that the point is in the graph
     * @param graph graph to solve
     */
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

    /**
     * solve the given graph
     * @param end ending point, given that the point is in the graph
     * @param graph graph to solve
     */
    public void solve(Point end, Graph graph) {
        solve(getCurrentPoint(), end, graph);
    }

    /**
     * solve the given graph
     * @param avenues the number of columns of the map
     * @param streets the number of rows of the map
     */
    public void solve(int avenues, int streets) {
        final Point home = getCurrentPoint();
        Graph graph = scan(0, 0, avenues, streets);
        while (!thingPoints.isEmpty()) {
            Point thingPoint = thingPoints.removeFirst();
            solve(thingPoint, graph);
            pickAllThings(); 
        }
        solve(home, graph);
    }
}
