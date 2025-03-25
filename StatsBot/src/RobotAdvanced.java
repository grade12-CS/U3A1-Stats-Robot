import becker.robots.*;

public class RobotAdvanced extends RobotSE{
    public RobotAdvanced(City city, int x, int y, Direction direction, int things) {
        super(city, y, x, direction, things);
    }

    public RobotAdvanced(City city, int x, int y) {
        super(city, y, x, Direction.NORTH, 999);
    } 

    public int getX() {
        return getAvenue();
    }

    public int getY() {
        return getStreet();
    }

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

    public void repeat(Runnable func, int n) {
        for (int i = 0; i < n; ++i) {
            func.run();
        }
    }

    public void move(int x, int y) {
       turnTo(x > 0 ? Direction.EAST : Direction.WEST); 
       move(Math.abs(x));
       turnTo(y > 0 ? Direction.NORTH : Direction.SOUTH);
       move(Math.abs(y));
    }
    
    public void goTo(int x, int y) {
        move(x- getX(), y - getY());
    }

    @Override
    public void setTransparency(double value) {
        super.setTransparency(Math.abs(1.0 - value));
    }
}
