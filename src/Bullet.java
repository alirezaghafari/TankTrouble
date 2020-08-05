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

	public boolean reduceTimer(){
		//count down the timer.  If time is up, remove the bullet.  Return whether the bullet was removed
		timer--;
		if (timer<0){
			this.removeBullet();
			return true;
		}
		return false;
	}

	private void flipBulletV(){
		this.angle=(-this.angle) + 360;
	}

	private void flipBulletH(){
		if (this.angle>180){
			this.angle = -this.angle+540;
		}else{
			this.angle=-this.angle+180;
		}
	}

	public void tankCollision(){
		//checks whether the bullet has collided with player1 or player2, calling the appropriate functions if so
		if (collision(engine.player1)){
			this.removeBullet();
			engine.player1.hit();
		}else if (collision(engine.player2)){
			this.removeBullet();
			engine.player2.hit();
		}

	}

	private boolean collision(Player player){
		//work out the distance between the centre of the bullet and the player
		double distance = Point.distance(player.getCoordinates(),this.position);
		//return whether they're overlapping
		return (distance<=(GameEngine.tankWidth/2+GameEngine.bulletWidth/2));
	}
	public Point(double a, double b){
		this.setxCoord(a);
		this.setyCoord(b);
	}

	//Getter and Setter methods:
	public double getxCoord() {
		return xCoord;
	}

	public void setxCoord(double xCoord) {
		this.xCoord = xCoord;
	}

	public double getyCoord() {
		return yCoord;
	}

	public void setyCoord(double yCoord) {
		this.yCoord = yCoord;
	}

	public void setCoords(double xCoord, double yCoord){
		this.xCoord=xCoord;
		this.yCoord=yCoord;
	}
}



