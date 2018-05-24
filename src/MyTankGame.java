
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyTankGame extends JFrame implements ActionListener, KeyListener {
	GamePanel gamePanel;
	StartPanel startPanel;

	JMenuBar menuBar;
	JMenu gameMenu;
	JMenuItem startGame;
	JMenuItem quitGame;

	public static void main(String[] args) {
		MyTankGame game = new MyTankGame();
	}

	public MyTankGame() {
		startPanel = new StartPanel();
		startPanel.setBackground(Color.BLACK);
		startPanel.setSize(400, 300);

		Thread t = new Thread(startPanel);
		t.start();

		menuBar = new JMenuBar();

		gameMenu = new JMenu("Game");
		gameMenu.setMnemonic('g');

		startGame = new JMenuItem("Start");
		startGame.setMnemonic(KeyEvent.VK_S);
		startGame.addActionListener(this);
		startGame.setActionCommand("start");

		quitGame = new JMenuItem("Quit");
		quitGame.setMnemonic('q');

		this.add(startPanel);

		menuBar.add(gameMenu);
		gameMenu.add(startGame);
		gameMenu.add(quitGame);
		this.setJMenuBar(menuBar);

		this.setLayout(null);
		this.addKeyListener(this);

		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("start")) {
			if (startPanel != null) {
				this.remove(startPanel);
			}
			if (gamePanel != null) {
				this.remove(gamePanel);
			}

			gamePanel = new GamePanel();
			gamePanel.setBackground(Color.BLACK);
			gamePanel.setSize(400, 300);

			Thread t = new Thread(gamePanel);
			t.start();

			this.add(gamePanel);
			this.addKeyListener(gamePanel);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (startPanel != null) {
			if (e.getKeyChar() == ' ') {
				this.remove(startPanel);
				startPanel = null;

				gamePanel = new GamePanel();
				gamePanel.setBackground(Color.BLACK);
				gamePanel.setSize(400, 300);

				Thread t = new Thread(gamePanel);
				t.start();

				this.add(gamePanel);
				this.addKeyListener(gamePanel);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
