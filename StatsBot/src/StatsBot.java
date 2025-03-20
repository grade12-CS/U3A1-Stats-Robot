import becker.robots.*;

public class StatsBot extends RobotSE {
    public StatsBot(City city, int x, int y, Direction direction, int things) {
        super(city, y, x, direction, things);
    }

    public StatsBot(City city, int x, int y) {
        super(city, y, x, Direction.NORTH, 999);
    }

    protected int moveCount = 0;

    public void moveEast(int n) {
        var dir = getDirection();
         if (dir == Direction.WEST) {
            turnRight();
            turnRight();
        } else if (dir == Direction.NORTH) {
            turnRight();
        } else if (dir == Direction.SOUTH) {
            turnLeft();
        }
        move(n);
        moveCount += n;
    } 

    public void moveWest(int n) {
        var dir = getDirection();
         if (dir == Direction.EAST) {
            turnLeft();
            turnLeft();
        } else if (dir == Direction.NORTH) {
            turnLeft();
        } else if (dir == Direction.SOUTH) {
            turnRight();
        }
        move(n);
        moveCount += n;
    } 

    public void moveSouth(int n) {
        var dir = getDirection();
         if (dir == Direction.NORTH) {
            turnLeft();
            turnLeft();
        } else if (dir == Direction.WEST) {
            turnLeft();
        } else if (dir == Direction.EAST) {
            turnRight();
        }
        move(n);
        moveCount += n;
    } 
    
    public void moveNorth(int n) {
        var dir = getDirection();
         if (dir == Direction.SOUTH) {
            turnLeft();
            turnLeft();
        } else if (dir == Direction.WEST) {
            turnRight();
        } else if (dir == Direction.EAST) {
            turnLeft();
        }
        move(n);
        moveCount += n;
    } 
}
