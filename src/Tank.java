import java.util.Vector;

public class Tank {
	int x;
	int y;
	int direction;
	int speed;
	boolean isAlive;
	Vector<Bullet> bullets;
	Bullet bullet;


	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
		this.isAlive = true;
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

	public void moveUp() {
		y -= speed;
	}

	public void moveRight() {
		x += speed;
	}

	public void moveDown() {
		y += speed;
	}

	public void moveLeft() {
		x -= speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
