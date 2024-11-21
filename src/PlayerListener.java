
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerListener implements KeyListener {
	private Picture picture;
	public PlayerListener(Picture p) {
		picture = p;
	}
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		if(!picture.isEntered()) {
			if(e.getKeyCode() == KeyEvent.VK_R&&picture.isMenu()) {
				picture.enterRank();
			}
			if(e.getKeyCode() == KeyEvent.VK_A&&picture.isMenu()) {
				picture.enterAssitance();
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_M&&!picture.ball.isAlive()) {
			picture.enterMenu();
		}
		if(picture.isMenu()) {//from the menu to the Game
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				picture.enterTheGame();
			}
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(picture.isEntered()&&picture.renew&&!picture.ball.isAlive()) {//entering the game the user wants to start the game
				picture.initialize();
				picture.startTheGame();
			}else if(!picture.ball.Gaming&&picture.ball.isAlive()) {//lose a life but not die
				picture.ball.Gaming = true;
			}else if(picture.lb.isReady()&&picture.ball.Gaming) {
				picture.lb.shoot();
			}
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(picture.paddle.x>0) {	//prevent paddle from going outside of the game window
				picture.movePaddle(-50);
			}
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) { //the same above
			if(picture.paddle.x<picture.MAX_X-picture.paddle.getW()) {
				picture.movePaddle(50);
			}
		}
		picture.repaint();
		
	}
	public void keyReleased(KeyEvent e) {}

}
