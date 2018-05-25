
public class Bullet implements Runnable {
	int x;
	int y;
	int speed;
	int direction;
	boolean isAlive = true;
	
	public Bullet(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.speed = 5;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			} catch(Exception e) {
				e.printStackTrace();
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
