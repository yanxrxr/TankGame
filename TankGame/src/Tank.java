import java.awt.*;
import javax.swing.*;

public class Tank {
	int x;
	int y;
	int direction = 2;
	int speed = 1;
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
		this.direction = 0;
		this.speed = 1;
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
