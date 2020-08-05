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
	private static boolean isNeighbour(Square a, Square b){
		//takes two squares and says whether or not they are direct neighbours
		int x = a.getxCoord();
		int y = a.getyCoord();
		int x2 = b.getxCoord();
		int y2 = b.getyCoord();
		return (x2-1==x && y2==y)||(x2+1==x && y2==y)||(x2==x && y2-1==y)||(x2==x && y2+1==y);
	}
	private void removeWall(Square[] area){
		// when given an array of squares that make up an area, remove a random wall (or two) between that area and another
		// ie connect that area to a random other one (or two)
		Square[] neighbours = areaNeighbours(area);
		//find all neighbours of the area
		int numberToRemove=2;
		//may or may not want to remove multiple walls to connect to multiple areas?
		for (int j = 0; j<numberToRemove;j++){


			int randomNum = (int) (Math.random()*neighbours.length);
			Square chosen = neighbours[randomNum];
			//choose a random square from the list of neighbours
			Square areaSquareChosen = new Square(0,0);
			for (int i = 0; i<area.length; i++){
				if (isNeighbour(area[i],chosen)){
					areaSquareChosen=area[i];
					break;
				}
			}
			//find a square in the area adjacent to that one
			int x = chosen.getxCoord();
			int y = chosen.getyCoord();
			int x2 = areaSquareChosen.getxCoord();
			int y2 = areaSquareChosen.getyCoord();
			if (x==x2){
				walls[0][Math.max(y,y2)][x]=false;
			}else{
				walls[1][Math.max(x2, x)][y]=false;
			}
		}
		//remove the wall between those two squares
	}
	private Square[] areaNeighbours(Square[] area){
		//when given an array of squares that make up an area, returns all neighbours of that area
		//doesn't return any squares in the area itself
		Square[] neighbours = new Square[49];
		//return all walled neighbours{
		for (int i = 0; i<area.length;i++){
			neighbours =  merge(neighbours,walledNeighbours(area[i]));
		}
		//return all walled neighbours}
		//remove any squares found that are actually in the area itself{
		Square[] cutNeighbours = new Square[neighbours.length];
		int cutNeighboursLength=0;
		for (int i = 0;i<neighbours.length;i++){
			boolean found = false;
			for (int j=0;j<area.length;j++){
				if (neighbours[i].equals(area[j])){
					found = true;
				}
			}
			if (!found){
				cutNeighbours[cutNeighboursLength]=neighbours[i];
				cutNeighboursLength++;
			}
		}
		//remove any squares found that are actually in the area itself}
		//remove nulls{
		Square[] returnValue = new Square[cutNeighboursLength];
		for (int i = 0; i<cutNeighboursLength;i++){
			returnValue[i]=cutNeighbours[i];
		}
		//remove nulls}
		return returnValue;
	}
	private Square[] walledNeighbours(Square square){
		//returns all neighbour squares of the input square which are separated by a wall
		Square[] neighbours = new Square[4];
		int numberOfNeighbours = 0;
		//find all walled neighbours{
		if (isWallLeft(square.getxCoord(), square.getyCoord())&&square.getxCoord()-1>=0) {
			neighbours[numberOfNeighbours] = new Square(square.getxCoord() - 1,
					square.getyCoord());
			numberOfNeighbours++;
		}
		if (isWallRight(square.getxCoord(), square.getyCoord())&&square.getxCoord()+1<7) {
			neighbours[numberOfNeighbours] = new Square(square.getxCoord() + 1,
					square.getyCoord());
			numberOfNeighbours++;
		}
		if (isWallAbove(square.getxCoord(), square.getyCoord())&&square.getyCoord()-1>=0) {
			neighbours[numberOfNeighbours] = new Square(square.getxCoord(),
					square.getyCoord() - 1);
			numberOfNeighbours++;
		}
		if (isWallBelow(square.getxCoord(), square.getyCoord())&&square.getyCoord()+1<7) {
			neighbours[numberOfNeighbours] = new Square(square.getxCoord(),
					square.getyCoord() + 1);
			numberOfNeighbours++;
		}
		//find all walled neighbours}
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