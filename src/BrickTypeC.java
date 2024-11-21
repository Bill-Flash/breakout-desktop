import java.awt.Color;
import java.awt.Graphics;

public class BrickTypeC extends Brick{

	public BrickTypeC(int i, int j, int k, int l) {
		super(i, j, k, l);
		w = k;
		h = l;
	}
	
	public void draw(Graphics g) {
		if(visible) {
			g.setColor(Color.RED);
			g.fill3DRect(x, y, w, h,true);	
		}
	}
}
