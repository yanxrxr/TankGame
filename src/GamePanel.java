import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GamePanel extends JPanel implements KeyListener, Runnable {
	Hero hero;
	Vector<Enemy> enemies = new Vector<Enemy>();
	int enemyNum = 3;
	int x;
	int y;
	Image explode1;
	Image explode2;
	Image explode3;
	Image explode4;
	Image explode5;
	Vector<Bomb> bombs;

	public GamePanel() {
		this.hero = new Hero(50, 200);
		hero.setSpeed(2);
		for (int i = 0; i < enemyNum; i++) {
			Enemy enemy = new Enemy(50 * (i + 1), 30);
			enemy.direction = 2;
			enemy.setSpeed(2);
			Thread t = new Thread(enemy);
			t.start();
			enemy.shoot();
			enemies.add(enemy);
		}
		try {
			explode1 = ImageIO.read(new File("img/explosion1.jpg"));
			explode2 = ImageIO.read(new File("img/explosion2.jpg"));
			explode3 = ImageIO.read(new File("img/explosion3.jpg"));
			explode4 = ImageIO.read(new File("img/explosion4.jpg"));
			explode5 = ImageIO.read(new File("img/explosion5.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bombs = new Vector<Bomb>();
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (hero.isAlive) {
			this.drawTank(hero.x, hero.y, g, hero.direction, 0);
		}

		for (int i = 0; i < hero.bullets.size(); i++) {
			if (hero.bullets.get(i) != null && hero.bullets.get(i).isAlive == true) {
				g.drawRect(hero.bullets.get(i).x, hero.bullets.get(i).y, 1, 1);
			}
			if (hero.bullets.get(i).isAlive == false) {
				hero.bullets.remove(i);
			}
		}

		for (int i = 0; i < bombs.size(); i++) {
			if (bombs.get(i).life > 4) {
				g.drawImage(explode1, bombs.get(i).x, bombs.get(i).y, 30, 30, this);
			} else if (bombs.get(i).life > 3) {
				g.drawImage(explode2, bombs.get(i).x, bombs.get(i).y, 30, 30, this);
			} else if (bombs.get(i).life > 2) {
				g.drawImage(explode3, bombs.get(i).x, bombs.get(i).y, 30, 30, this);
			} else if (bombs.get(i).life > 1) {
				g.drawImage(explode4, bombs.get(i).x, bombs.get(i).y, 30, 30, this);
			} else {
				g.drawImage(explode5, bombs.get(i).x, bombs.get(i).y, 30, 30, this);
			}
			bombs.get(i).lifeDecrease();
			if (bombs.get(i).life == 0) {
				bombs.remove(i);
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			if (enemy.isAlive) {
				this.drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirection(), 1);
				for (int j = 0; j < enemy.bullets.size(); j++) {
					if (enemy.bullets.get(j) != null && enemy.bullets.get(j).isAlive) {
						g.drawRect(enemy.bullets.get(j).x, enemy.bullets.get(j).y, 1, 1);
					}
					if (!enemy.bullets.get(j).isAlive) {
						enemy.bullets.remove(j);
					}
				}
			}
		}
	}

	public void drawTank(int x, int y, Graphics g, int direction, int type) {
		switch (type) {
		case 0:
			g.setColor(Color.BLUE);
			break;
		case 1:
			g.setColor(Color.RED);
			break;
		}

		switch (direction) {
		case 0:
			g.fill3DRect(x - 10, y - 15, 5, 30, false);
			g.fill3DRect(x + 5, y - 15, 5, 30, false);
			g.fill3DRect(x - 5, y - 10, 10, 20, false);
			g.fillOval(x - 5, y - 4, 8, 8);
			g.fill3DRect(x - 1, y - 15, 2, 10, true);
			break;
		case 1:
			g.fill3DRect(x - 15, y - 10, 30, 5, false);
			g.fill3DRect(x - 15, y + 5, 30, 5, false);
			g.fill3DRect(x - 10, y - 5, 20, 10, false);
			g.fillOval(x - 4, y - 4, 8, 8);
			g.fill3DRect(x + 5, y - 1, 10, 2, true);
			break;
		case 2:
			g.fill3DRect(x - 10, y - 15, 5, 30, false);
			g.fill3DRect(x + 5, y - 15, 5, 30, false);
			g.fill3DRect(x - 5, y - 10, 10, 20, false);
			g.fillOval(x - 5, y - 4, 8, 8);
			g.fill3DRect(x - 1, y + 5, 2, 10, true);
			break;
		case 3:
			g.fill3DRect(x - 15, y - 10, 30, 5, false);
			g.fill3DRect(x - 15, y + 5, 30, 5, false);
			g.fill3DRect(x - 10, y - 5, 20, 10, false);
			g.fillOval(x - 4, y - 4, 8, 8);
			g.fill3DRect(x - 15, y - 1, 10, 2, true);
			break;
		}

	}

	public void hitTank(Bullet bullet, Tank tank) {
		switch (tank.direction) {
		case 0:
		case 2:
			if (bullet.x < tank.x + 10 && bullet.x > tank.x - 10 && bullet.y < tank.y + 15 && bullet.y > tank.y - 15) {
				tank.isAlive = false;
				bullet.isAlive = false;
				bombs.add(new Bomb(tank.x - 10, tank.y - 15));
			}
			break;
		case 1:
		case 3:
			if (bullet.x < tank.x + 15 && bullet.x > tank.x - 15 && bullet.y < tank.y + 10 && bullet.y > tank.y - 10) {
				tank.isAlive = false;
				bullet.isAlive = false;
				bombs.add(new Bomb(tank.x - 15, tank.y - 10));
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'w':
			hero.setDirection(0);
			if (hero.y > 15) {
				hero.moveUp();
			}
			break;
		case 'd':
			hero.setDirection(1);
			if (hero.x < 385) {
				this.hero.moveRight();
			}
			break;
		case 's':
			hero.setDirection(2);
			if (hero.y < 285) {
				hero.moveDown();
			}
			break;
		case 'a':
			hero.setDirection(3);
			if (hero.x > 15) {
				hero.moveLeft();
			}
			break;
		}
		if (e.getKeyChar() == ' ') {
			if (hero.bullets.size() < 5) {
				hero.shoot();
			}
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {

			}
			for (int i = 0; i < hero.bullets.size(); i++) {
				for (int j = 0; j < enemies.size(); j++) {
					if (hero.bullets.get(i).isAlive && enemies.get(j).isAlive) {
						hitTank(hero.bullets.get(i), enemies.get(j));
					}
				}
			}
			for (int i = 0; i < enemies.size(); i++) {
				for (int j = 0; j < enemies.get(i).bullets.size(); j++) {
					if (hero.isAlive && enemies.get(i).bullets.get(j).isAlive) {
						hitTank(enemies.get(i).bullets.get(j), hero);
					}
				}
			}

			this.repaint();
		}
	}
}
