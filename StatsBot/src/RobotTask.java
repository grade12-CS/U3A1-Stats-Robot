import becker.robots.*;

public class RobotTask {

	public void run() {
		City maze = new City("maze.txt");
		maze.showThingCounts(true); 
		MazeBot bot = new MazeBot(maze, 3, 4);
		var graph = bot.scan(0, 0, 10, 10);
		bot.solve(bot.getCurrentPoint(), new Point(0, 0), graph);
	}
}