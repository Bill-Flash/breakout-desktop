
import javax.swing.JFrame;

public class Window {
	public static void main(String[] args) {
		final int MAX_X = 640;	//the X size of Window
		final int MAX_Y = 700;	//the Y size of Window
		
		JFrame window = new JFrame();
		window.setTitle("Breakout done by Bill");
		window.setSize(MAX_X, MAX_Y);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Picture pic = new Picture();
		PlayerListener pl = new PlayerListener(pic);
		window.add(pic);
		window.addKeyListener(pl);
		window.setVisible(true);
	}

}
