
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import db.RankingList;

public class Picture extends JComponent{
	private static final long serialVersionUID = 1L;
	
	final int MAX_X = 640;	//the X size of Window
	final int MAX_Y = 700;	//the Y size of Window
	
	private int xP = 250, yP = 670;
	Paddle paddle = new Paddle(xP,yP); //width: 50; height: 10;
	Ball ball = new Ball(paddle.centerPoint());
	boolean renew;
	private BallThread bt;
	private int level;
	private Brick[][] b;
	private Map map = new Map(b,0);
	private int bNumber, score;
	private RankingList rl;
	private String[] rank;
	private BonusBox[][] bb;
	protected LaserBall lb;
	private ArrayList<Ball> ballList= new ArrayList<Ball>();
	public Picture() {
		renew = true;
		level = -1;
		b = map.build();
		bb = map.Surprise();
		rl = new RankingList("Scores.txt");
		lb = new LaserBall(paddle.centerPoint());
		ballList.add(ball);
	}
	private void upLevel() {
		if(level<4) {
			level++;
			map = new Map(b,level);
			b = map.build();
			bb = map.Surprise();
			ball.start();
		}else {
			String name = JOptionPane.showInputDialog(null,
					"You succeed, Please Enter Your name", "Congratulations", 1);
			if(name!=null) {//in case the player doesn't enter his name
				rl.enterRank(score, name);
			}
			level = -2;//the rank part
			initialize();
		}
	}
	private void construction(Brick[][] c, Graphics g, BonusBox[][] d) {
		for(int i = 0; i < c.length; i++) {
			for(int j = 0; j < c[i].length; j++) {
				b[i][j].draw(g);
				d[i][j].draw(g);
			}
		}
	}
	public void paint(Graphics g) {

		g.fillRect(0, 0, 640, 700);//the black background
		if(!renew) {	//to start the game (when just enter or want to replay after death)
			bt = new BallThread(this);
			bt.start();
			renew = !renew; //becomes true again
			ball.Gaming = true;
			ball.start();
		}else {			//from map 0 to 4 according to the level, 5 levels in total
			if(level>=0) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, 640, 50);
				bNumber = countBrick(b);
				drawString(g, "Balls "+ball.getLife()+"  scores "+score+"  Bricks "+bNumber+" Level "+(level+1), 
					new Rectangle(MAX_X/ 3, MAX_Y / 30, MAX_X / 3, MAX_Y / 30),30);
				if(!ball.Gaming&&ball.isAlive()) {
					drawString(g, "Press SPACE to continue", new Rectangle(MAX_X/ 3, 4*MAX_Y / 7, MAX_X / 3, 2*MAX_Y / 7),16);
				}else if(!ball.isAlive()) {
					drawString(g, "Press SPACE to restart", new Rectangle(MAX_X/ 3, 4*MAX_Y / 7, MAX_X / 3, 2*MAX_Y / 7),16);					
					drawString(g, "Press M to return Menu", new Rectangle(MAX_X/ 3, 5*MAX_Y / 7 , MAX_X / 3, 3*MAX_Y / 7),16);
				}else if(lb.isReady()) {
					drawString(g, "Press SPACE to fire Laser Ray", new Rectangle(MAX_X/ 3, 4*MAX_Y / 7, MAX_X / 3, 2*MAX_Y / 7),16);
				}
				lb.draw(g);
				int size = ballList.size();
				for (int i = 0; i<size; i++) {
				    ballList.get(i).draw(g);;        
				}
				construction(b,g,bb);
				ball.draw(g);
				paddle.draw(g);
				
			}
			switch(level) {
			case -1://the main menu part of this game
				drawString(g, "Welcome to Breakout!!!!", new Rectangle(MAX_X / 3, MAX_Y / 8, MAX_X / 3, MAX_Y / 8), 24);
				drawString(g, "To play a game press SPACE", new Rectangle(MAX_X/ 3, 2 * MAX_Y / 8, MAX_X / 3, MAX_Y / 3),18);
				drawString(g, "To see the Ranking list press R",new Rectangle(MAX_X / 3, 3 * MAX_Y / 8, MAX_X / 3, MAX_Y / 3), 18);
				drawString(g, "For help press A", new Rectangle(MAX_X / 3, 4 * MAX_Y / 8, MAX_X / 3, MAX_Y / 3), 18);
				break;

			case -2://ranking list part
				rank = rl.outputRank();
				drawString(g, "Hall of Fame", new Rectangle(MAX_X/ 3, MAX_Y / 20, MAX_X / 3, MAX_Y / 20),40);		
				for(int i = 0; i < rank.length; i++) {
					drawString(g, rank[i], new Rectangle( MAX_X/ 3,70+i* MAX_Y / 22, MAX_X / 3, 70+i* MAX_Y / 22),30);
				}
				drawString(g, "Return Menu press M", new Rectangle(MAX_X/ 3,11*MAX_Y/20, MAX_X / 3, 11*MAX_Y/20),20);

				break;
			case -3://assistance part
				drawString(g, "Breakout Controls", new Rectangle(MAX_X/ 3, MAX_Y / 20, MAX_X / 3, MAX_Y / 20),40);
				drawString(g, "Move Left       left arrow", new Rectangle( MAX_X/ 3,70+ MAX_Y / 22, MAX_X / 3, 70+ MAX_Y / 22),30);
				drawString(g, "Move Right         right arrow", new Rectangle( MAX_X/ 3,70+2* MAX_Y / 22, MAX_X / 3, 70+2* MAX_Y / 22),30);
				drawString(g, "Play/Continue/Fire Laser      space bar", new Rectangle( MAX_X/ 3,70+3* MAX_Y / 22, MAX_X / 3, 70+3* MAX_Y / 22),30);
				drawString(g, "The Bonus Box Introduction", new Rectangle( MAX_X/ 3,70+5* MAX_Y / 22, MAX_X / 3, 70+5* MAX_Y / 22),40);
				drawString(g, "Cyan Box is Double Length", new Rectangle( MAX_X/ 3,70+7* MAX_Y / 22, MAX_X / 3, 70+7* MAX_Y / 22),30);
				drawString(g, "Orange Box is sticky paddle", new Rectangle( MAX_X/ 3,70+8* MAX_Y / 22, MAX_X / 3, 70+8* MAX_Y / 22),30);
				drawString(g, "Magenta Box is shooting Laser ball", new Rectangle( MAX_X/ 3,70+9* MAX_Y / 22, MAX_X / 3, 70+9* MAX_Y / 22),30);
				drawString(g, "Yellow Box is multiple balls", new Rectangle( MAX_X/ 3,70+10* MAX_Y / 22, MAX_X / 3, 70+10* MAX_Y / 22),30);
				drawString(g, "Return Menu press M", new Rectangle(MAX_X/ 3,12*MAX_Y/20, MAX_X / 3, 12*MAX_Y/20),20);
				break;
				}
			}
	}
	private void drawString(Graphics g, String text, Rectangle rect, int size) {
		    Graphics2D g2d = (Graphics2D) g.create();
		    Font font = new Font("Calibri", Font.BOLD, size);
		    g2d.setFont(font);
		    FontMetrics metrics = g2d.getFontMetrics();
		    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

		    g2d.setColor(Color.RED);
		    g2d.drawString(text, x, y);
	}

	public void movePaddle(int x) {
		paddle.move(x);
		paddle.rect.setLocation(paddle.x,paddle.y);
		paddle.lR.setLocation(paddle.x,paddle.y);
		paddle.rR.setLocation(paddle.x+2*(paddle.getW()/3),paddle.y);
		if(isEntered()&&!ball.Gaming) {//when enter the game but not begin, ball is following or it lose a life but not dies
			ball.connectWith(paddle);
		}
		if(lb.isReady()) {
			lb.connectWith(paddle);
		}
	}
	private int countBrick(Brick[][] a) {
		int c = 0;
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				if(a[i][j].visible) {
					c++;
				}
			}
		}
		return c;
	}
	private boolean checkVisible(Brick[][] a) {
		if(countBrick(a)==0) {
			return false;
		}
		return true;
	}
	protected void initialize() {
		ballList.clear();
		ball.connectWith(paddle);
		map = new Map(b,0);
		b = map.build();
		bb = map.Surprise();
		score = 0;
		lb = new LaserBall(paddle.centerPoint());
		ballList.add(ball);
	}
	public void bouncingBall() {
			while(ball.Gaming&&ball.isAlive()) {//start the game when ball is alive
				try{
					Thread.currentThread();
					Thread.sleep(10);
				}
				catch(InterruptedException e){
					System.err.println(e.toString());
				}
				 
				int size = ballList.size();

				for (int i=0; i<size; i++) {
					
					ballList.get(i).Hit(paddle);
					ballList.get(i).move();
					for(int k = 0; k < b.length; k++) {
						for(int j = 0; j < b[k].length; j++) {
							if(ballList.get(i).isHit(b[k][j].rect)&&b[k][j].visible) {
								b[k][j].visible = false;
								ballList.get(i).bounce(b[k][j].rect);
								if((int)(Math.random()*25)==0) { //approximately 4% chance, one percent for each
									bb[k][j].makeVisible();
								}
								score+=100;
								repaint();
							}
							if(lb.isHit(b[k][j].rect)&&b[k][j].visible&&lb.visible()) {
								b[k][j].visible = false;
								if((int)(Math.random()*25)==0) { //approximately 4% chance, one percent for each
									bb[k][j].makeVisible();
								}
								score+=100;
								repaint();
							}
						}			
					}
				}
				for(int i = 0; i < bb.length; i++) {
					for(int j = 0; j < bb[i].length; j++) {
						if(paddle.isHit(bb[i][j].rect)&&bb[i][j].visible) {
							bb[i][j].makeInvisible();
							int t = bb[i][j].getBonus();
							if(t==0) {
								paddle.doubleLength();
							}
							if(t==1) {
								ball.Sticky = true;
							}
							if(t==2) {
								lb = new LaserBall(paddle.centerPoint());
								lb.getReady();
							}
							if(t==3) {
								ballList.add(new Ball(ball));
								ballList.add(new Ball(ball));
								ballList.add(new Ball(ball));
							}
						}
					}			
				}
				if(!checkVisible(b)) {
					ball.Gaming = false;//it should move to the next level. level++;
					ball.newLevel();//make it life to 0 
					lb = new LaserBall(paddle.centerPoint());
					upLevel();
					ballList.clear();
					ballList.add(ball);
				}
				while(!ball.Gaming&&ball.isAlive()) {//lose a life but not die OR level up
					ball.connectWith(paddle);
					repaint();
				}
				if(!ball.isAlive()) {//after dead, if score reach the rank, should input, if not ask whether restart
					rl = new RankingList("Scores.txt");
					if(score > rl.getLowestScore()) {
						String name = JOptionPane.showInputDialog(null, "New High Score, Please Enter Your name", "Congratulations", 1);
						if(name!=null) {//in case the player doesn't enter his name
							rl.enterRank(score, name);
						}
						level = -2;
					}
				}
				Iterator<Ball> iterator = ballList.iterator(); 
				while (iterator.hasNext()){
				    Ball b = iterator.next();
				    if(b.y>=MAX_Y) {
					    iterator.remove(); 
				    }
				}
				repaint();			
			}
		}
	public void enterAssitance() {
		level = -3;		
	}
	public void enterMenu() {
		level = -1;
	}

	public void enterTheGame() {//enter the game screen
		level=0;
		score=0;
	}
	public boolean isEntered() {
		if(level<=-1) {
			return false;
		}
		return true;
	}
	public boolean isMenu() {
		if(level==-1) {
			return true;
		}
		return false;
	}
	public void enterRank() {
		level = -2;
	}
	public void startTheGame() {
		renew = false;
	}

}
