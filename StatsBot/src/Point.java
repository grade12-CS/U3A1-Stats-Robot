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

    private static final HashMap<Direction, Point> directions = new HashMap<>() {{
        put(Direction.NORTH, new Point(0, -1));
        put(Direction.SOUTH, new Point(0, 1));
        put(Direction.EAST, new Point(1, 0));
        put(Direction.WEST, new Point(-1, 0));
    }};

    public Point add(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    public Point add(Point point) {
        return add(point.x, point.y);
    }

    public Point add(Direction d) {
        return add(directions.get(d));
    }

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
