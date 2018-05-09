
public class Bullet implements Runnable {
	int x;
	int y;
	int speed = 2;
	int direction;
	boolean isAlive = true;
	
	public Bullet(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(100);
			} catch(Exception e) {
				
			}
			
			switch (direction) {
			case 0:
				this.y -= speed;
				break;
			case 1:
				this.x += speed;
				break;
			case 2:
				this.y += speed;
				break;
			case 3:
				this.x -= speed;
				break;
			}
			if (this.x < 0 || this.x > 400 || this.y < 0 || this.y > 300) {
				isAlive = false;
				break;
			}
		}
	}

}
