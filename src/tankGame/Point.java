package tankGame;

public class Point {
	private double xCoordinate;
	private double yCoordinate;//so the point is (xCoordinate,yCoordinate)
	
	//Basic constructor:
	public Point(double a, double b){
		this.setXCoordinate(a);
		this.setYCoordinate(b);
	}

	//Getter and Setter methods:
	public double getXCoordinate() {
		return xCoordinate;
	}

	public void setXCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public double getYCoordinate() {
		return yCoordinate;
	}

	public void setYCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	public void setCoordinates(double xCoordinate, double yCoordinate){
		this.xCoordinate=xCoordinate;
		this.yCoordinate=yCoordinate;
	}
	
	public String toString(){
		return "" + xCoordinate + "," + yCoordinate;
	}
	
	public boolean equals(Point a){
		return this.getXCoordinate()==a.getXCoordinate()&&this.getYCoordinate()==a.getYCoordinate();
	}
	public static double distance(Point p1, Point p2){
		double xDistance = p1.getXCoordinate()-p2.getXCoordinate();
		double yDistance = p1.getYCoordinate()-p2.getYCoordinate();
		return Math.sqrt(xDistance*xDistance+yDistance*yDistance);
	}
}


