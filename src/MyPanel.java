import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyPanel extends JPanel implements KeyListener, Runnable {
	Hero hero;
	Vector<Enemy> enemies = new Vector<Enemy>();
	int enemyNum = 3;
	int x;
	int y;
	
	public MyPanel() {
		this.hero = new Hero(30, 100);
		hero.setSpeed(2);
		for (int i = 0; i <enemyNum; i ++) {
			Enemy enemy = new Enemy(30 * (i+1), 30);
			enemies.add(enemy);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.drawTank(hero.x, hero.y, g, hero.direction, 0);
		for (int i = 0; i < hero.bullets.size(); i++) {
			if (hero.bullets.get(i) != null && hero.bullets.get(i).isAlive == true) {
				g.drawRect(hero.bullets.get(i).x, hero.bullets.get(i).y, 1,	1);
			}
			if (hero.bullets.get(i).isAlive == false) {
				hero.bullets.remove(i);
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isAlive) {
				this.drawTank(enemies.get(i).getX(), enemies.get(i).getY(), g, 0, 1);
			}
		}
		
	}
	
	public void drawTank(int x, int y, Graphics g, int direction, int type) {
		switch (type) {
		case 0:
			g.setColor(Color.BLUE);
			break;
		case 1:
			g.setColor(Color.GREEN);
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
			g.fill3DRect(x -10, y - 5, 20, 10, false);
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
			g.fill3DRect(x -10, y - 5, 20, 10, false);
			g.fillOval(x - 4, y - 4, 8, 8);
			g.fill3DRect(x - 15, y - 1, 10, 2, true);
			break;
		}
		
	}

	public void hitEnemy(Bullet bullet, Enemy enemy) {
		switch (enemy.direction) {
		case 0:
		case 2:
			if (bullet.x < enemy.x + 10 && bullet.x > enemy.x - 10 && bullet.y < enemy.y + 15 && bullet.y > enemy.y - 15) {
				enemy.isAlive = false;
				bullet.isAlive = false;
			}
		case 1:
		case 3:
			if (bullet.x < enemy.x + 15 && bullet.x > enemy.x - 15 && bullet.y < enemy.y + 10 && bullet.y > enemy.y - 10) {
				enemy.isAlive = false;
				bullet.isAlive = false;
			} 
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub		
		switch (e.getKeyChar()) {
		case 'w':
			hero.setDirection(0);
			hero.moveUp();
			break;
		case 'd':
			hero.setDirection(1);
			this.hero.moveRight();
			break;
		case 's':
			hero.setDirection(2);
			hero.moveDown();
			break;
		case 'a':
			hero.setDirection(3);
			hero.moveLeft();
			break;
		case ' ':
			if (hero.bullets.size() < 5) {
				hero.shoot();
			}
			break;
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
						hitEnemy(hero.bullets.get(i), enemies.get(j));
					}
				}
			}
			this.repaint();
		}
	}
}

