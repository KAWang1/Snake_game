import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public abstract class SnakeGame extends JPanel{
	private BufferedImage img;
	private Graphics crayon;
	public static final int WIDTH = 30*BodySegment.SIZE;
	public static final int HEIGHT = 30*BodySegment.SIZE;
	public static final int STARTING_SIZE = 3;
	protected JPanel scoreBoard;
	protected JTextField timer, score;
	protected JFrame framey;
	
	private final int scoreBoardSZ = 100;
	private final int fillerSZ = 15;
	
	protected Color bgColor = Color.BLACK;
	protected Color gridColor = new Color(25,25,25,100);
	protected Font CSMS = new Font("Comic Snas MS", Font.BOLD, 45);
	
	protected Snake player;
	protected BodySegment food;
	protected double waitSeconds;
	public static String[] diffOptions = {"Easy", "Medium", "Hard", "Impossible"};
	
	//walls
	protected JPanel fillerH, fillerV, fillerV2;
	
	//students will write this!
	public abstract void gameFrame();
	
	public SnakeGame(){
		this(1);
	}
	
	public SnakeGame(double delay){
		super();
		waitSeconds = delay;
		
		img = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		crayon = img.getGraphics();
		
		player = new Snake();
		for(int i=0; i<STARTING_SIZE-1; i++)
			player.addFirst();;
				
		placeFood();
		
		framey = new JFrame();
		
		this.setPreferredSize( new Dimension(WIDTH, HEIGHT) );
		this.setSize(WIDTH, HEIGHT);		
		this.addKeyListener(player);
		
		//scoreBoard setup
		scoreBoard = new JPanel();
		scoreBoard.setSize(WIDTH, scoreBoardSZ);
		scoreBoard.setPreferredSize( new Dimension(WIDTH, scoreBoardSZ));
		framey.add( scoreBoard, BorderLayout.SOUTH);
		
		scoreBoard.setLayout(new GridLayout(1,2));
		timer = new JTextField();
		timer.setEditable(true);
		scoreBoard.add(timer);
		score = new JTextField();
		score.setEditable(true);
		scoreBoard.add(score);
		
		//walls & stuff
		
		fillerH = new JPanel();
		fillerH.setPreferredSize( new Dimension(WIDTH, fillerSZ));
		framey.add( fillerH, BorderLayout.NORTH);		
		fillerV = new JPanel();
		fillerV.setPreferredSize( new Dimension(fillerSZ, HEIGHT));
		framey.add( fillerV, BorderLayout.EAST);
		fillerV2 = new JPanel();
		fillerV2.setPreferredSize( new Dimension(fillerSZ, HEIGHT));
		framey.add( fillerV2, BorderLayout.WEST);
		
		//actual game window
		framey.add(this, BorderLayout.CENTER);		
		this.setFocusable(true);
		this.requestFocusInWindow();		
				
		framey.setSize(WIDTH+2*fillerSZ+15,HEIGHT+fillerSZ+scoreBoardSZ+35);
		framey.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framey.setVisible(true);

		//animate();
	}
	
	public static int difficultyPrompt(){
		
		int choice = JOptionPane.showOptionDialog(null, "Difficulty", "Choose a Difficulty Level:", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, diffOptions, diffOptions[0]);
		return choice;
	}
	
	public void setWallColor(Color c){
		fillerH.setBackground(c);
		fillerV.setBackground(c);
		fillerV2.setBackground(c);
	}
	public void setBG( Color b){bgColor = b;}
	public void setGridColor(Color g){gridColor = g;}
	
	
	public void placeFood(){
		int xMult = (int)(Math.random()* (WIDTH/BodySegment.SIZE-2))+1;
		int yMult = (int)(Math.random()* (HEIGHT/BodySegment.SIZE-2))+1;
		food = new BodySegment(xMult * BodySegment.SIZE, yMult*BodySegment.SIZE);
	}
	
	public void playGame(){
		while(true){
			gameFrame();
			pause(waitSeconds*50);
		}
	}
	

	public void drawGame(){
		crayon.clearRect(0, 0, WIDTH, HEIGHT);
		crayon.setColor(bgColor);
		crayon.fillRect(0, 0, WIDTH, HEIGHT);
		drawGrid(crayon);
		if(food!=null)
			food.draw(crayon);
		player.draw(crayon);
		this.getGraphics().drawImage(img, 0, 0, null);
	}
	
	public void drawGrid(Graphics g){
		g.setColor(gridColor);
		for(int x=0; x<=WIDTH; x+=BodySegment.SIZE)
			g.drawLine(x, 0, x, HEIGHT);
		for(int y=0; y<=HEIGHT; y+=BodySegment.SIZE)
			g.drawLine(0, y, WIDTH, y);
	}
	
	public void pause(double time){
		try{Thread.sleep( (int)Math.round(time) );}
		catch(Exception ex){ex.printStackTrace();}
	}

	
}
