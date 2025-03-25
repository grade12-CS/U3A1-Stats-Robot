import becker.robots.*;

public class RobotAdvanced extends RobotSE{
    /**
     * 
     * @param city the city robot is created to
     * @param x x-coordinate of starting point
     * @param y y-coordinate of starting point
     * @param direction direction robot is initially heading to
     * @param things the number of things robot is initially holding
     */
    public RobotAdvanced(City city, int x, int y, Direction direction, int things) {
        super(city, y, x, direction, things);
    }

    /**
     * 
     * @param city the city robot is created to
     * @param x x-coordinate of starting point
     * @param y y-coordinate of starting point
     */
    public RobotAdvanced(City city, int x, int y) {
        super(city, y, x, Direction.NORTH, 0);
    } 

    /**
     * 
     * @return x-coordinate of current location
     */
    public int getX() {
        return getAvenue();
    }

    /**
     * 
     * @return y-coordinate of current location
     */
    public int getY() {
        return getStreet();
    }

    /**
     * 
     * @return point of current location 
     */
    public Point getCurrentPoint() {
        return new Point(getX(), getY());
    }

    /**
     * turn to a direction regardless of where robot is currently heading to
     * @param target direction to turn
     */
    public void turnTo(Direction target) {
        var current = getDirection(); 
        if (current == target) return;
        if (current.left() == target) {
            turnLeft();
        } else if (current.right() == target) {
            turnRight();
        } else {
            turnAround();
        }
    }

    /**
     * repeat a runnable function for specific amounts of times
     * @param func function to run
     * @param n the number of times to repeat the function
     */
    public void repeat(Runnable func, int n) {
        for (int i = 0; i < n; ++i) {
            func.run();
        }
    }

    /**
     * move robot to the given direction in certain amounts of times
     * @param direction direction to move
     * @param spaces the number of times to move in that direction
     */
    public void move(Direction direction, int spaces) {
        if (spaces <= 0) return;
        turnTo(direction);
        repeat(() -> move(), spaces);
    }

    /**
     * moves robot by certain amounts of units in x and y directions
     * @param x vector x-coordinate to move. moves to east if positive, moves to west if negative
     * @param y vector y-coordinate to move, moves to south if positive, moves to north if negative
     */
    public void move(int x, int y) {
       move(x > 0 ? Direction.EAST : Direction.WEST, Math.abs(x));
       move(y > 0 ? Direction.SOUTH : Direction.NORTH, Math.abs(y));
    }
    
    /**
     * goes to a certain point on the map
     * @param x x-coordinate of the goal point
     * @param y y-coordinate of the goal point
     */
    public void goTo(int x, int y) {
        move(x - getX(), y - getY());
    }

    /**
     * sets transparency of the robot
     * @param value transparency (becomes more opaque closing to 1.0, transparent at 0.0)
     */
    @Override
    public void setTransparency(double value) {
        super.setTransparency(Math.abs(1.0 - value));
    }
}
