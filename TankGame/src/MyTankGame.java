import java.awt.*;
import javax.swing.*;

public class MyTankGame extends JFrame {
	MyPanel myPanel;
	public static void main(String[] args) {
		MyTankGame game = new MyTankGame();
	}
	
	public MyTankGame() {
		myPanel = new MyPanel();
		
		Thread t = new Thread(myPanel);
		t.start();
		
		this.add(myPanel);
		this.addKeyListener(myPanel);
		
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
