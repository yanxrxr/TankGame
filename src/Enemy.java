
public class Enemy extends Tank implements Runnable {

	public Enemy(int x, int y) {
		super(x, y);
		// super.shoot();
	}

	@Override
	public void run() {
		while (true) {
			direction = (int) (Math.random() * 4);

			switch (direction) {
			case 0:
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (y > 15) {
						this.moveUp();
					}
				}
				break;
			case 1:
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (this.x < 385) {
						this.moveRight();
					}
				}
				break;
			case 2:
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (this.y < 285) {
						this.moveDown();
					}
				}

				break;
			case 3:
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (this.x > 15) {
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
