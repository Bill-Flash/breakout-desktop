import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Paddle extends Abstract2DDrawing{
	Rectangle lR = null, rR = null;
	private int width = 100, height = 10, t = 0;
	private boolean isDouble;
	public Paddle(int i, int j) {
		super(i, j);
		rect = new Rectangle(x,y,width,height);
		lR = new Rectangle(x,y,width/3,height);
		rR = new Rectangle(x+2*(width/3),y,width/3,height);
		isDouble = false;
	}
	public Point centerPoint() {
		Point p = new Point(x+width/2-5,y-10);//for the ball fix
		return p;
	}
	@Override
	public void draw(Graphics g) {
		if(isDouble) t++;
		if(t==1000) shrinkLength();
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
	public void move() {}
	public void move(int x) {
		this.x+=x;
	}
	public boolean isHit(Rectangle r) {
		if(rect.intersects(r)) {
			return true;
			}
		return false;
	}
	public int getW() {
		return width;
	}
	
	public void doubleLength() {
		if(!isDouble) {//in case double and double again
			x-=width/2;
			width*=2;
			rect = new Rectangle(x,y,width,height);
			lR = new Rectangle(x,y,width/3,height);
			rR = new Rectangle(x+2*(width/3),y,width/3,height);
			isDouble = true;
		}
	}
	private void shrinkLength() {
		if(isDouble) {//with a period of time shrink back
			width/=2;
			x+=width/2;
			rect = new Rectangle(x,y,width,height);
			lR = new Rectangle(x,y,width/3,height);
			rR = new Rectangle(x+2*(width/3),y,width/3,height);
			isDouble = false;
			t = 0;
		}
	}
}
