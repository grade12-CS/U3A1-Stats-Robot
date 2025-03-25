import becker.robots.*;

public class RobotTask {

	public void run() {
		final int avenues = 10, streets = 10;
		City maze = new City("maze.txt");
		maze.showThingCounts(true); 
		MazeBot bot = new MazeBot(maze, 4, 4);
		bot.solve(avenues, streets);
	}
}