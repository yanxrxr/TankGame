
public class Enemy extends Tank implements Runnable {

	public Enemy(int x, int y) {
		super(x, y);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			direction = (int) (Math.random() * 4);

			switch (direction) {
			case 0:
				for (int i = 0; i < 30; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (y > 20) {
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
					if (this.x < 380) {
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

			if (!isAlive) {
				break;
			}
		}
	}

}
