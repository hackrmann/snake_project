package game1;

import java.awt.Color;
import javafx.scene.paint.*;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		//   g.setColor(new Color(curColor%255,curColor%255,curColor%255));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 805, 800);
		Snake snake = Snake.snake;
		g.setColor(Color.ORANGE);
		for(Point point: snake.snakeParts) {
			g.fillRect(point.x * Snake.SCALE, point.y* Snake.SCALE, Snake.SCALE	, Snake.SCALE);
		}
		g.setColor(Color.RED);
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y* Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.CYAN);
		g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
	
	}
}
