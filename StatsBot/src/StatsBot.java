import becker.robots.*;

public class StatsBot extends RobotAdvanced {
    public StatsBot(City city, int x, int y) {
        super(city, y, x);
    }

    private int totalMoves = 0;
    private int east = 0;
    private int west = 0;
    private int north = 0;
    private int south = 0;

    public void displayProbabilities() {
        System.out.println("Probability to move in East: " + String.format("%.2f", (double) east / totalMoves * 100) + '%');
        System.out.println("Probability to move in West: " + String.format("%.2f", (double) west / totalMoves * 100) + '%');
        System.out.println("Probability to move in North: " + String.format("%.2f", (double) north / totalMoves * 100) + '%');
        System.out.println("Probability to move in South: " + String.format("%.2f", (double) south / totalMoves * 100) + '%');
    }

    @Override
    public void move(int x, int y) {
        if (x > 0) {
            moveEast(Math.abs(x));
        } else {
            moveWest(Math.abs(x));
        }
        if (y > 0) {
            moveSouth(Math.abs(y));
        } else {
            moveNorth(Math.abs(y));
        }
    }

    @Override
    public void goTo(int x, int y) {
        this.move(x - getX(), y - getY());
    }

    public void moveEast(int n) {
        if (n <= 0) return;
        move(Direction.EAST, n);
        totalMoves += n;
        east += n;
    } 

    public void moveWest(int n) {
        if (n <= 0) return;
        move(Direction.WEST, n);
        totalMoves += n;
        west += n;
    } 

    public void moveSouth(int n) {
        if (n <= 0) return;
        move(Direction.SOUTH, n);
        totalMoves += n;
        south += n;
    } 
    
    public void moveNorth(int n) {
        if (n <= 0) return;
        move(Direction.NORTH, n);
        totalMoves += n;
        north += n;
    } 

    public void reset() {
        totalMoves = 0;
        east = 0;
        west = 0;
        north = 0;
        south = 0;
    }
}
