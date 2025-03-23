import becker.robots.*;

public class RobotTask {

	public void run() {
		City maze = new City("maze.txt");
		maze.showThingCounts(true); 
		StatsBot sb = new StatsBot(maze, 4, 4);
		sb.moveWest(2);
		sb.displayProbabilities();
	}
}