import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Ball extends Abstract2DDrawing{
	protected int width = 10, height = 10;
	public double xDx = 1.5, yDy = 3;  //the moving speed of the bouncing ball
	public double v, degree;//the velocity and degree of ball 
	Boolean Gaming,Sticky,Laser;
	boolean isUp = true, isRight = true; //to ensure whether it is positive with coordinate increasing
	private int life;
	private int k;
	public Ball(Point p) {
		super(p.x, p.y);
		rect = new Rectangle(p.x,p.y,width,height);
		Gaming = false;
		Sticky = false;
		life = 0;
		v = getV();
		degree = getDegree();
		k = 0;
	}
	public Ball(Ball b) {
		super(b.x, b.y);
		rect = new Rectangle(b.x,b.y,width,height);
		Gaming = true;
		Sticky = false;
		life = 0;
		v = b.v;
		degree = b.degree;
		k = 1;
		isUp = b.isUp;
		isRight = b.isRight;
		slightlyChange();
	}
	public void start() {
		life = 3;
		xDx = 1.5;
		yDy = 3;
		v = getV();
	}
	public int newLevel() {
		int l = life;
		life = 0;
		return l;
	}
	public void draw(Graphics g) {
		if(k == 1) {
			g.setColor(Color.GRAY);
			g.fillOval(x,y,width,height);
		}if(k == 0) {
			g.setColor(Color.YELLOW);
			g.fillOval(x,y,width,height);
		}
	}
	private double getV() {
		return Math.sqrt(xDx*xDx+yDy*yDy);
	}
	private double getDegree() {
		double tgA = yDy/xDx;
		return Math.toDegrees(Math.atan(tgA));
	}
	public boolean isHit(Rectangle r) {
		if(rect.intersects(r)) {
			return true;
		}
		return false;
	}
	public boolean isSticky() {
		return Sticky;
	}
	public void Hit(Paddle p) {
		if(isHit(p.rect)) {//bouncing on the paddle
			isUp = false;
			if(Sticky) {
				Gaming = false;
				Sticky = false;
			}
			increasingSpeed();
			if(isHit(p.lR)) {
				if(isRight) {//the left one third paddle touch the ball from left
					angle(true);
				}else {
					angle(false);
				}
			}else if(isHit(p.rR)) {
				if(!isRight) {
					angle(true);
				}else {
					angle(false);
				}
			}
		}
	}
	public void bounce(Rectangle r) {
		if(r.contains(rect.x+(width/2),rect.y+height)||r.contains(rect.x+width/2,rect.y)) {
			isUp = !isUp;
		}else { //r.contains(x+width,y+height/2) || r.contains(x,y+height/2)
			isRight = !isRight;
		}
		rect.setLocation(x,y);
	}
	public void move() {
		if(x<=0) {
			x = 0;
			isRight = true;
		}else if(x>=MAX_X-width){
			x = MAX_X-width;
			isRight = false;
		}
		if(y<=50) {
			y = 50;
			isUp = true;
		}
		else if(y>=MAX_Y-20) {//touch the bottom
			if(k==0) {
				isUp = false;
				decrementlives();
			}
			Gaming = false;
		}
		if(isRight) {
			x+=xDx;
		}else {
			x-=xDx;
		}
		if(isUp) {
			y+=yDy;
		}else {
			y-=yDy;
		}
		rect.setLocation(x,y);
	}
	public void angle(boolean a) {//change the angular amount, but the scalar of the velocity doesn't change
		double minD = (double) 30;		//the minimum of the degree
		double maxD = (double) 70;		//the maximum of the degree
		degree = getDegree();
		double gama = (Math.random()*20)+(double)10;		//the interval of changing angular [10,30]
		if(a!=true) {
			degree-=gama;
		}else {
			degree+=gama;
		}
		
		if(degree<minD) { //to prevent it from exceed minD or MaxD
			degree = minD;
		}else if(degree>maxD){
			degree = maxD;
		}
		double r = Math.toRadians(degree);
		yDy = v*Math.sin(r);
		xDx = v*Math.cos(r);

	}
	public void connectWith(Paddle p) {
		x = p.centerPoint().x;
		y = p.centerPoint().y;
		rect.setLocation(this.x,this.y);

	}
	private void Continue() {		//return to the normal speed
		double r = Math.toRadians(getDegree());
		v = Math.sqrt(1.5*1.5 + 3*3);
		xDx=v*Math.sin(r);
		yDy=v*Math.cos(r);
		
	}
	private void decrementlives() {
		life--;
		Continue();
	}
	public boolean isAlive() {
		if(life>0) {
			return true;
		}
		return false;
	}
	protected int getLife() {
		return life;
	}
	private void increasingSpeed() {//increase the speed of the ball with time passing
		double r = Math.toRadians(getDegree());
		xDx=(v+0.5)*Math.sin(r);
		yDy=(v+0.5)*Math.cos(r);
		v=getV();
	}
	private void slightlyChange() {
		degree = getDegree();
		double gama = Math.random()*10;		
		
		degree+=gama;
		double r = Math.toRadians(degree);
		yDy = v*Math.sin(r);
		xDx = v*Math.cos(r);
	}
}
