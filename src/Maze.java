package project;
import java.util.ArrayList;

public class Maze {
	private static final int gridWidth = 7;
	//number of grid squares in the grid (along and down)
	private static boolean[][][] walls = new boolean[2][gridWidth + 1][gridWidth + 1];
	//array of booleans where true means there is a wall there, false means there isn't
	//walls[0=horizontal, 1=vertical][x][y]
	//so the grid square (a,b) will have:
	//left wall = [1][a][b]
	//right wall = [1][a+1][b]
	//top wall = [0][b][a]
	//bottom wall = [0][b+1][a]

	public Maze() {
		new Maze(30);
		//no argument constructor automatically sets the density to the default value of 30
	}

	public Maze(int density) {
		walls = generateMaze(density);
		//create a randomly filled grid of walls, where the higher the density, the more walls there will be
		Square[][] areas = this.mergeAll();
		//find all the areas which are separate from one another
		while (areas.length > 1) {
			removeWall(areas[0]);
			//remove a wall between two adjacent separate areas
			areas = this.mergeAll();
			//find the seperate areas again
		}
	}

	public boolean isWallLeft(int x, int y) {
		//inputs: x: x coordinate of the grid square being checked, y: y coordinate of the grid square being checked
		//output: true if there is a wall to the left of the square, false otherwise
		return walls[1][x][y];
	}

	public boolean isWallRight(int x, int y) {
		//inputs: x: x coordinate of the grid square being checked, y: y coordinate of the grid square being checked
		//output: true if there is a wall to the right of the square, false otherwise
		return walls[1][x + 1][y];
	}

	public boolean isWallAbove(int x, int y) {
		//inputs: x: x coordinate of the grid square being checked, y: y coordinate of the grid square being checked
		//output: true if there is a wall above the square, false otherwise
		return walls[0][y][x];
	}

	public boolean isWallBelow(int x, int y) {
		//inputs: x: x coordinate of the grid square being checked, y: y coordinate of the grid square being checked
		//output: true if there is a wall below the square, false otherwise
		return walls[0][y + 1][x];
	}

	private Square[] neighbours(Square square) {
		// returns all squares adjacent to and in the same area as the square inputed, including itself
		// inputs: square: the square to be checked
		// output: an array of all squares that fit the description above
		Square[] neighbours = new Square[5];
		int numberOfNeighbours = 0;
		//find all neighbours{
		if (!isWallLeft(square.getxCoord(), square.getyCoord())) {
			neighbours[numberOfNeighbours] = new Square(square.getxCoord() - 1,
					square.getyCoord());
			numberOfNeighbours++;
		}
		if (!isWallRight(square.getxCoord(), square.getyCoord())) {
			neighbours[numberOfNeighbours] = new Square(square.getxCoord() + 1,
					square.getyCoord());
			numberOfNeighbours++;
		}
		if (!isWallAbove(square.getxCoord(), square.getyCoord())) {
			neighbours[numberOfNeighbours] = new Square(square.getxCoord(),
					square.getyCoord() - 1);
			numberOfNeighbours++;
		}
		if (!isWallBelow(square.getxCoord(), square.getyCoord())) {
			neighbours[numberOfNeighbours] = new Square(square.getxCoord(),
					square.getyCoord() + 1);
			numberOfNeighbours++;
		}
		neighbours[numberOfNeighbours] = square;
		//find all neighbours}

		// remove nulls{
		int realLength = 0;
		boolean found = false;
		for (int i = 0; i < neighbours.length; i++) {
			if (neighbours[i] == null) {
				realLength = i;
				found = true;
				break;
			}
		}
		if (found) {
			Square[] cutReturnValue = new Square[realLength];
			for (int i = 0; i < realLength; i++) {
				cutReturnValue[i] = neighbours[i];
			}
			return cutReturnValue;
		} else {
			return neighbours;
		}
		//remove nulls}
	}
}