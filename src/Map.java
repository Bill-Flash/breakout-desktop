

public class Map {
	Brick[][] b = new Brick[8][10];
	private int level;
	private int number;
	private BonusBox[][] bb = new BonusBox[8][10];
	public Map(Brick[][] c, int l) {
		level = l;
		number = 0;
		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[i].length; j++) {
				b[i][j] = new Brick(i*80,50+j*40,80,40);
				bb[i][j] = new BonusBox(b[i][j]); 
				if(j==1) {
					b[i][j] = (Brick) new BrickTypeA(i*80,50+j*40,80,40);//change the color of the brick in a row
				}
				if(j==2) {
					b[i][j] = (Brick) new BrickTypeD(i*80,50+j*40,80,40);
				}
				if(j==3) {
					b[i][j] = (Brick) new BrickTypeB(i*80,50+j*40,80,40);
				}
				if(j==4) {
					b[i][j] = (Brick) new BrickTypeC(i*80,50+j*40,80,40);
				}
			}
		}
	}
	public Brick[][] build(){//5 levels you will encounter
			switch(level) {
			case 0:
				for(int i = 0; i < b.length; i++) {
					for(int j = 0; j < b[i].length; j++) {
						if(i==j||7-i==j) {
							b[i][j].visible = true;
						}
						number++;
					}
				}
				break;
			case 4:
				for(int i = 0; i < b.length; i++) {
					for(int j = 0; j < b[i].length; j++) {
						b[i][j].visible = true;
						if(i%2==1&&j%2==1) {
							b[i][j].visible = false;
						}
					}
						number++;
					}
				break;
			case 1://1
				for(int i = 0; i < b.length; i++) {
					for(int j = 0; j < b[i].length; j++) {
						if(i>=3&&i<=5&&j>=3&&j<=6) {
							b[i][j].visible = true;
						}
						number++;
					}
				}
				break;
			case 2:
				for(int i = 0; i < b.length; i++) {
					for(int j = 0; j < 6; j++) {
						if(i<=j) {
							b[i][j].visible = true;
						}
						number++;
					}
				}
				break;
			case 3:
					for(int i = 0; i < b.length; i++) {
						for(int j = 0; j < b[i].length; j++) {
							if((i*j)%2==1||j==7) {
								b[i][j].visible = true;
							}
							number++;
						}
					}
				break;
			}
			return b;
	}
	public BonusBox[][] Surprise(){//get the bonus boxes
		return bb;
	}
	int getNum() {
		return number;
	}
}
