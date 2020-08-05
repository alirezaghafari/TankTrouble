package project;

//import java.util.Map;

public class Player {
	private int playerNumber; // defines whether this is player 1 or 2
	private int numberOfBulletsFired; // stores the number of bullets currently
	// on the field from this player
	private int direction; // the direction the tank is currently facing
	// 0 is North, 180 is South
	private Point coordinates; // the coordinates of the centre of the tank
	private static final int rotationSpeed = 3; // the speed at which each tank can turn
	private static final double forwardSpeed = 1; // the speed at which each tank can move forwards
	private static final double reverseSpeed = -.65; // the speed at which each tank can move backwards

	private GameEngine engine;

	// Movement methods:
	public void turnRight() {
		this.direction += rotationSpeed;
		if (this.direction>359){
			this.direction-=360;
		}
	}

	public void turnLeft() {
		this.direction -= rotationSpeed;
		if (this.direction<0){
			this.direction+=360;
		}
	}
	private void move(double speed) {
		Point nextPoint = new Point(this.coordinates.getxCoord()
				+ (speed * Math.cos(Math.toRadians(90-this.direction))),
				this.coordinates.getyCoord()
						- (speed * Math.sin(Math
						.toRadians(90-this.direction))));
		if (cornerCrash(nextPoint,GameEngine.tankWidth)){//if the tank would crash into a corner...
			nextPoint = new Point(this.coordinates.getxCoord()//try moving it horizontally
					+ (speed * Math.cos(Math.toRadians(90-this.direction))),
					this.coordinates.getyCoord());
			if(cornerCrash(nextPoint,GameEngine.tankWidth)||wallCrashVertical(nextPoint,GameEngine.tankWidth)||wallCrashHorizontal(nextPoint,GameEngine.tankWidth)){
				nextPoint = new Point(this.coordinates.getxCoord(),//if moving it horizontally would cause a crash, try moving it vertically
						this.coordinates.getyCoord()
								- (speed * Math.sin(Math.toRadians(90-this.direction))));
				if(cornerCrash(nextPoint,GameEngine.tankWidth)||wallCrashVertical(nextPoint,GameEngine.tankWidth)||wallCrashHorizontal(nextPoint,GameEngine.tankWidth)){
					nextPoint= new Point(this.coordinates.getxCoord(),//if moving it vertically would also cause a crash, just don't move it
							this.coordinates.getyCoord());//TODO stop it going through T walls
				}
			}
		}else {
			if (wallCrashVertical(nextPoint,GameEngine.tankWidth)&&wallCrashHorizontal(nextPoint,GameEngine.tankWidth)){
				nextPoint= new Point(this.coordinates.getxCoord(),//if moving it at all would cause a crash, just don't move it
						this.coordinates.getyCoord());
			}
			if (wallCrashVertical(nextPoint, GameEngine.tankWidth)){ //if the tank would go over a vertical wall...

				nextPoint = new Point(this.coordinates.getxCoord(),//just move it vertically
						this.coordinates.getyCoord()
								- (speed * Math.sin(Math.toRadians(90-this.direction))));
			}
			if(wallCrashHorizontal(nextPoint,GameEngine.tankWidth)){//if the tank would go over a horizontal wall...
				System.out.println("test");
				nextPoint = new Point(this.coordinates.getxCoord()//just move it horizontally
						+ (speed * Math.cos(Math.toRadians(90-this.direction))),
						this.coordinates.getyCoord());
			}
		}
		this.coordinates=nextPoint;
		// move the coordinates of the tank so that it travels a distance of
		// forwardSpeed in direction direction
	}


}



