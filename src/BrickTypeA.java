import java.awt.Color;
import java.awt.Graphics;

public class BrickTypeA extends Brick{
	public BrickTypeA(int i, int j, int k, int l) {
		super(i, j, k, l);
		w = k;
		h = l;
	}
	
	public void draw(Graphics g) {
		if(visible) {
			g.setColor(Color.ORANGE);
			g.fill3DRect(x, y, w, h, true);
		}
		
	}
}
