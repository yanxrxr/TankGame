import java.util.*;

public class Hero extends Tank {
	Vector<Bullet> bullets;
	Bullet bullet;
	
	public Hero(int x, int y) {
		super(x, y);
		bullets = new Vector<Bullet>();
	}
	
	public void shoot() {
		switch (direction) {
		case 0:
			bullet = new Bullet(x, y - 15, this.direction);
			bullets.add(bullet);
			break;
		case 1:
			bullet = new Bullet(x + 15, y, this.direction);
			bullets.add(bullet);
			break;
		case 2:
			bullet = new Bullet(x, y + 15, this.direction);
			bullets.add(bullet);
			break;
		case 3:
			bullet = new Bullet(x - 15, y, this.direction);
			bullets.add(bullet);
			break;
		}
		
		Thread t = new Thread(bullet);
		t.start();
	}
}
