
import java.awt.*;
import javax.swing.*;

public class MyTankGame extends JFrame {
	GamePanel gamePanel;
	StartPanel startPanel;
	
	public static void main(String[] args) {
		MyTankGame game = new MyTankGame();
	}
	
	public MyTankGame() {
		/*startPanel = new StartPanel();
		startPanel.setBackground(Color.BLACK);
		startPanel.setSize(400, 300);
		
		this.setLayout(null);
		this.add(startPanel);*/
		
		gamePanel = new GamePanel();
		gamePanel.setBackground(Color.BLACK);
		gamePanel.setSize(400, 300);
		
		Thread t = new Thread(gamePanel);
		t.start();
		
		this.add(gamePanel);
		this.addKeyListener(gamePanel);
		
		this.setLayout(null);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
