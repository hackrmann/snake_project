package game1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Point;

public class Snake implements ActionListener, KeyListener {
	
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	public boolean walls;
	public int gar;
	
	public static Snake snake;

	public JFrame jframe;
	public RenderPanel renderPanel;
	public Toolkit toolkit;
	public Timer timer = new Timer(20, this);

	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	public int ticks = 0, direction = DOWN, score = 0, tailLength = 3;
	public Point head, cherry;
	public Random random;
	public Dimension dim;
	public boolean over = false,pause = false;

	public Snake(int ch) {
		gar = ch;
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(805, 800);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		jframe.add(renderPanel = new RenderPanel());
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		startGame();
	}

	public void startGame() {
		pause = false;
		over = false;
		score = 0;
		tailLength = 3;
		direction = DOWN;
		
		random = new Random();
		snakeParts.clear();
		cherry = new Point(random.nextInt(75),random.nextInt(75));
		head = new Point(0, 0);
		
		timer.start();
	}
	public void actionPerformed(ActionEvent x) {

		this.initializeWalls(gar);
		renderPanel.repaint();
		ticks++;
		//Random mee = new Random();
		if (ticks % 2 == 0 && head != null && !over ) {

			snakeParts.add( (Point)head.clone() );
			if (direction == DOWN) {
				if(noTail(head.x,head.y+1)) {
					if (head.y + 1 < 76)
						head = (new Point(head.x, head.y + 1));
					else {
						if (!walls) over = true;
						else	head.y = 0;
					}
				}
				else over = true;
			}
			if (direction == UP) {
				if(noTail(head.x,head.y-1)) {
					if (head.y  > 0)
						head = (new Point(head.x, head.y - 1));
					else {
						if(!walls) over = true;
						else	head.y = 75;
					}
				}
				else over = true;	
			}
			if (direction == LEFT) {
				if(noTail(head.x-1,head.y)) {
					if (head.x > 0)
						head = (new Point(head.x - 1, head.y));
					else {
						if(!walls) over = true;
						else	head.x = 78;
					}
				}
				else over = true;
			}
			if (direction == RIGHT) {
				if(noTail(head.x+1,head.y)) {
					if (head.x + 1 < 79)
						head = (new Point(head.x + 1, head.y));
					else {
						if(!walls) over = true;
						else	head.x = 0;
					}
				}
				else over = true;	
			}
			if(snakeParts.size() > tailLength) {
				snakeParts.remove(0);
			}
			if (cherry != null) {
				if (head.equals(cherry)) {
					score++;
					tailLength++;
					cherry.setLocation(random.nextInt(75),random.nextInt(75));
				}
			}
			System.out.println(head.x+" "+head.y);
		}

	}
	
	private boolean noTail(int x, int y) {
		// TODO Auto-generated method stub
		for(Point point : snakeParts) {
			if(point.equals(new Point(x,y))) {
				return false;
			}
		}
		return true;
	}

	public void initializeWalls(int flag) {
		if(flag==1) {
			walls = true;
		}
		if(flag==2) {
			walls = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int i = e.getKeyCode();
		if(i == KeyEvent.VK_SPACE&& !pause) {
			pause = false;
		}
		if (i == KeyEvent.VK_LEFT && direction != RIGHT) {
			direction = LEFT;
		}
		if (i == KeyEvent.VK_DOWN && direction != UP) {
			direction = DOWN;
		}
		if (i == KeyEvent.VK_RIGHT && direction != LEFT) {
			direction = RIGHT;
		}
		if (i == KeyEvent.VK_UP && direction != DOWN) {
			direction = UP;
		}
		if(i == KeyEvent.VK_SPACE) {
			if(over) startGame();
			else pause = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//Not using
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//Not using
	}
	
	public static void main(String[] args) {
		System.out.println("Hello! Welcome to the snake game. There are 2 modes:\n1. Can walk through walls\n2. Die if you walk through walls. Which mode would you like to play on ");
		Scanner in = new Scanner(System.in);
		int ch = in .nextInt();
		snake = new Snake(ch);
		
	}

}
