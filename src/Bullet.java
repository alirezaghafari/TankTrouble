package project;

public class Bullet{

	private int timer;
	private int owner;
	private int angle;
	private Point position;
	private static final double bulletSpeed = 1.5;

	private GameEngine engine;

	public Bullet(int player, Point position, int angle, GameEngine e) {
		this.owner = player;
		this.angle = angle;
		this.position = position;
		this.timer = 1000; //set the time duration of the bullet's presence to 100 frames
		this.engine=e;
	}

	public Point getPosition() {
		return position;
	}
	public int getAngle(){
		return angle;
	}

	public void removeBullet(){
		if (this.owner==0){
			engine.player1.decreaseNumberOfBulletsFired();
		}else{
			engine.player2.decreaseNumberOfBulletsFired();
		}
		GameEngine.bulletList.remove(this);
	}

	public void moveBullet(){
		Point nextPoint = new Point(this.position.getxCoord()
				+ (bulletSpeed * Math.cos(Math.toRadians(90-this.angle))),
				this.position.getyCoord()
						- (bulletSpeed * Math.sin(Math
						.toRadians(90-this.angle))));
		if (wallCrashHorizontal(nextPoint, GameEngine.bulletWidth)){ //if the bullet would go over any horizontal walls
			flipBulletH();
			nextPoint = new Point(this.position.getxCoord()
					+ (bulletSpeed * Math.cos(Math.toRadians(90-this.angle))),
					this.position.getyCoord());
		}else if (wallCrashVertical(nextPoint, GameEngine.bulletWidth)){ // if the bullet would go over any vertical walls
			flipBulletV();
			nextPoint = new Point(this.position.getxCoord(),
					this.position.getyCoord()
							- (bulletSpeed * Math.sin(Math.toRadians(90-this.angle))));
		}else if(cornerCrash(nextPoint,GameEngine.bulletWidth)){
			if(this.currentYSquare()==(int)this.currentYSquare()){
				flipBulletH();
				nextPoint = new Point(this.position.getxCoord()
						+ (bulletSpeed * Math.cos(Math.toRadians(90-this.angle))),
						this.position.getyCoord());
			}else{
				flipBulletV();
				nextPoint = new Point(this.position.getxCoord(),
						this.position.getyCoord()
								- (bulletSpeed * Math.sin(Math.toRadians(90-this.angle))));
			}
		}
		this.position=nextPoint;
	}
}



