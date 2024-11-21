import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BonusBox extends Abstract2DDrawing{
	double yDy;
	boolean visible;
	private int type;
	public BonusBox(int i, int j) {
		super(i, j);
	}
	public BonusBox(Brick b) {
		super(b.x,b.y);
		yDy = 2;
		visible = false;
		rect = new Rectangle(b.x,b.y,30,30);
		type = (int)(Math.random()*4);
	}
	public void makeVisible() {
		visible = true;
	}
	public void makeInvisible() {
		visible = false;
	}
	
	@Override
	public void draw(Graphics g) {
		if(visible) {
			move();
			if(type==0) {//double paddle
				g.setColor(Color.cyan);		
			}
			if(type==1) {//Sticky paddle
				g.setColor(Color.yellow);	
			}
			if(type==2) {//Laser
				g.setColor(Color.magenta);	
			}
			if(type==3) {//Multiple-ball
				g.setColor(Color.orange);		
			}
			g.fill3DRect(x, y, 30, 30, true);
		}
		
	}
	public int getBonus() {
		return type;
	}
	public void move() {
		y+=yDy;
		rect.setLocation(x,y);
		if(y>MAX_Y) {
			makeInvisible();
		}
	}
}
