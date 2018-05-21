
public class Bomb {
	int x;
	int y;
	int life;
	boolean isAlive;
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		this.life = 5;
		this.isAlive = true;
	}
	
	public void lifeDecrease() {
		if (life > 0) {
			life--;
		}else {
			this.isAlive = false;
		}
	}

}
