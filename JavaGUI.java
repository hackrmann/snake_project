package game1;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;

public class JavaGUI {
	private JFrame jframe;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setvisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}
}
