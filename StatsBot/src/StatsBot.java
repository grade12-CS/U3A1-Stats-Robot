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
    private int thingsPicked = 0;

    /**
     * displays probabilities of directions robot may move, after performing some movements
     */
    public void displayProbabilities() {
        System.out.println("Probability to move in East: " + String.format("%.2f", (double) east / totalMoves * 100) + '%');
        System.out.println("Probability to move in West: " + String.format("%.2f", (double) west / totalMoves * 100) + '%');
        System.out.println("Probability to move in North: " + String.format("%.2f", (double) north / totalMoves * 100) + '%');
        System.out.println("Probability to move in South: " + String.format("%.2f", (double) south / totalMoves * 100) + '%');
        System.out.println("Probability to pick Things: " + String.format("%.2f", (double) thingsPicked / totalMoves * 100) + '%');
    }

    @Override
    public void pickThing() {
        super.pickThing();
        thingsPicked ++;
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

    /**
     * moves to east, regardless of the diretion robot is currently heading to.
     * tracks total moves and east movements
     * @param n the number of spaces robot will move to east
     */
    public void moveEast(int n) {
        if (n <= 0) return;
        move(Direction.EAST, n);
        totalMoves += n;
        east += n;
    } 

    /**
     * moves to west, regardless of the diretion robot is currently heading to.
     * tracks total moves and west movements
     * @param n the number of spaces robot will move to west 
     */
    public void moveWest(int n) {
        if (n <= 0) return;
        move(Direction.WEST, n);
        totalMoves += n;
        west += n;
    } 

    /**
     * moves to south, regardless of the diretion robot is currently heading to.
     * tracks total moves and south movements
     * @param n the number of spaces robot will move to south 
     */
    public void moveSouth(int n) {
        if (n <= 0) return;
        move(Direction.SOUTH, n);
        totalMoves += n;
        south += n;
    } 
    
    /**
     * moves to north, regardless of the diretion robot is currently heading to.
     * tracks total moves and north movements
     * @param n the number of spaces robot will move to north 
     */
    public void moveNorth(int n) {
        if (n <= 0) return;
        move(Direction.NORTH, n);
        totalMoves += n;
        north += n;
    } 

    /**
     * resets all stats
     */
    public void reset() {
        totalMoves = 0;
        east = 0;
        west = 0;
        north = 0;
        south = 0;
    }
}
