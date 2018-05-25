import java.awt.*;
import java.util.*;

public class Enemy extends Tank implements Runnable {

	Vector<Enemy> enemies;

	public Enemy(int x, int y) {
		super(x, y);
	}

	public void setEnemies(Vector<Enemy> enemies) {
		this.enemies = enemies;
	}

	public boolean isCollided(Vector<Enemy> enemies) {
		Rectangle rect1 = null;
		Rectangle rect2 = null;
		switch (this.direction) {
		case 0:
		case 2:
			rect1 = new Rectangle(this.x - 10, this.y - 15, 20, 30);
			break;
		case 1:
		case 3:
			rect1 = new Rectangle(this.x - 15, this.y - 10, 30, 20);
			break;

		}
		for (Enemy enemy : enemies) {
			if (enemy != this) {
				switch (enemy.direction) {
				case 0:
				case 2:
					rect2 = new Rectangle(enemy.x - 10, enemy.y - 15, 20, 30);
					break;
				case 1:
				case 3:
					rect2 = new Rectangle(enemy.x - 15, enemy.y - 10, 30, 20);
					break;
				}
				if (rect1.intersects(rect2)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void run() {
		while (true) {
			direction = (int) (Math.random() * 4);

			switch (direction) {
			case 0:
				for (int i = 0; i < (int) (Math.random() * 10) + 20; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (y > 15 && !isCollided(enemies)) {
						this.moveUp();
					}
				}
				break;
			case 1:
				for (int i = 0; i < (int) (Math.random() * 10) + 20; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (this.x < 385 && !isCollided(enemies)) {
						this.moveRight();
					}
				}
				break;
			case 2:
				for (int i = 0; i < (int) (Math.random() * 10) + 20; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (this.y < 285 && !isCollided(enemies)) {
						this.moveDown();
					}
				}

				break;
			case 3:
				for (int i = 0; i < (int) (Math.random() * 10) + 20; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (this.x > 15 && !isCollided(enemies)) {
						this.moveLeft();
					}
				}

				break;
			}

			if (this.bullets.size() < 3) {
				this.shoot();
			}

			if (!isAlive) {
				break;
			}
		}
	}

}
