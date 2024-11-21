import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick extends Abstract2DDrawing{
	protected int w, h,a,b,c,d;		//width and height
	boolean visible;
	BonusBox bb;
	public Brick(int i, int j, int k, int l) {
		super(i,j);
		w = k;
		h = l;
		rect = new Rectangle(i,j,k,l);
		visible = false;
		a = (int) (Math.random()*256);
		b = (int) (Math.random()*256);
		c = (int) (Math.random()*256);
	}


	public void draw(Graphics g) {
		if(visible) {
			g.setColor(new Color(a,b,c));
			g.fill3DRect(x, y, w, h,true);	
		}	
	}


	public void move() {
	}

}
