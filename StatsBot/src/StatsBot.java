import becker.robots.*;

public class StatsBot extends RobotSE {
    public StatsBot(City city, int x, int y, Direction direction, int things) {
        super(city, y, x, direction, things);
    }

    public StatsBot(City city, int x, int y) {
        super(city, y, x, Direction.NORTH, 999);
    }

    protected int totalMoves = 0;
    protected int east = 0;
    protected int west = 0;
    protected int north = 0;
    protected int south = 0;

    public void displayProbabilities() {
        System.out.println("Probability to move in East: " + Math.round(east / totalMoves * 100) + '%');
        System.out.println("Probability to move in West: " + Math.round(west / totalMoves * 100) + '%');
        System.out.println("Probability to move in North: " + Math.round(north / totalMoves * 100) + '%');
        System.out.println("Probability to move in South: " + Math.round( south / totalMoves * 100) + '%');
    }

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
        totalMoves += n;
        east += n;
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
        totalMoves += n;
        west += n;
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
        totalMoves += n;
        south += n;
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
        totalMoves += n;
        north += n;
    } 
}
