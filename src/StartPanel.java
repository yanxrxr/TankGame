import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartPanel extends JPanel implements Runnable {
	boolean flag = true;

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.YELLOW);
		g.setFont(new Font(null, Font.BOLD, 30));
		g.drawString("Stage 1", 150, 130);
		if (flag) {
			g.setFont(new Font(null, Font.PLAIN, 15));
			g.drawString("Press SPACE to start", 130, 180);
		}
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flag = !flag;
			this.repaint();
		}
	}

}
