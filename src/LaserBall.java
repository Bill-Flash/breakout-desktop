import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class LaserBall extends Ball {
	private boolean ready, visible;
	public LaserBall(Point p) {
		super(p);
		yDy = -2;
		xDx = 0;
		isUp = true;
		ready = false;
		visible = false;
	}
	public boolean isReady() {
		if(ready) {
			return true;
		}
		return false;
	}
	public void getReady() {
		ready = true;
	}
	public void shoot() {
		visible = true;
		ready = false;
	}
	public void invisible() {
		visible = false;
	}
	public boolean visible() {
		if(visible) {
			return true;
		}
		return false;
	}
	public void draw(Graphics g) {
		if(ready) {
			g.setColor(Color.MAGENTA);
			g.fillOval(x,y,width,height);
		}
		if(y<=50) {
			visible = false;
		}
		if(visible) {
			move();
			g.setColor(Color.MAGENTA);
			g.fillOval(x,y,width,height);
		}
	}
}
