import java.util.HashMap;
import becker.robots.*;

public class Point {
    public final int x, y; 

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * unit points to add onto the current point depending on the direction
     */
    private static final HashMap<Direction, Point> directions = new HashMap<>() {{
        put(Direction.NORTH, new Point(0, -1));
        put(Direction.SOUTH, new Point(0, 1));
        put(Direction.EAST, new Point(1, 0));
        put(Direction.WEST, new Point(-1, 0));
    }};

    /**
     * create a new point added onto the original point
     * @param x x-coordinate to plus to x-coordinate of the original point
     * @param y y-coordinate to plus to y-coordinate of the original point
     * @return new point
     */
    public Point add(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    /**
     * create a new point added onto the original point
     * @param point point to plus to the original point
     * @return new point
     */
    public Point add(Point point) {
        return add(point.x, point.y);
    }

    /**
     * adds a unit point based on directions to the original point
     * @param d direction of a unit point
     * @return new point
     */
    public Point add(Direction d) {
        return add(directions.get(d));
    }

    /**
     * gets a point that a unit point is added to the original point, based  on the given direction
     * @param direction direction of a unit point
     * @return new point
     */
    public Point get(Direction direction) {
        return add(direction); 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }
}
