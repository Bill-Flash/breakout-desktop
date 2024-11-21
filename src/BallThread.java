public class BallThread extends Thread {
	private Picture pic;
	BallThread(Picture a){
	        pic=a;
	}
	public void run(){
		pic.bouncingBall();
		pic.repaint();
	}
}
