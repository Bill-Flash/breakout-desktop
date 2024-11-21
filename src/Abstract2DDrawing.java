import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Abstract2DDrawing {
	protected int x,y;
	Rectangle rect = null;
	final int MAX_X = 640;	//the X size of Window
	final int MAX_Y = 700;	//the Y size of Window
	public Abstract2DDrawing(int i, int j) {
		x = i;
		y = j;
	}
	
	public abstract void draw(Graphics g);	//to draw it
	public abstract void move();
}
