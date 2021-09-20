import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyGame extends SnakeGame{
	//things you inherited from SnakeGame
	//protected Snake player;
	//protected BodySegment food;
	//protected double waitSeconds;
	long ST = System.currentTimeMillis();
	public MyGame(){
		super();
		setBG(new Color(100,100,0));
		setGridColor( new Color(0,255,255,100));
		
		playGame();
	}
	
	public void gameFrame(){	
		int x = (int)(Math.random()*30)*15;
		int y = (int)(Math.random()*30)*15;
		if(player.getHead().isTouching(food)) {
			player.addFirst();
			food.setxPos(x);
			food.setyPos(y);
		}
		long CT = System.currentTimeMillis() - ST;
		
					player.addFirst();
					player.removeLast();
					score.setText("Score: "+String.valueOf(player.size()-1));
					timer.setText("Time: "+String.valueOf(CT/1000));
					System.out.println(player.size());
		
			drawGame();//do this at some point			
			
			
				BodySegment curr = player.head.getNext();
				for(int i=0; i<player.size()-1; i++) {
				if(player.head.isTouching(curr)||
				player.head.getxPos() > 435 || player.head.getyPos() > 435 ||
				player.head.getxPos() < 0 || player.head.getyPos() < 0) {
					JOptionPane.showMessageDialog(this, "GAME OVER");
					System.exit(0);
				}
				curr = curr.next;
				}

	}
	
	public static void main(String[] args){new MyGame();}	
}
