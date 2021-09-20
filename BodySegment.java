import java.awt.Color;
import java.awt.Graphics;

public class BodySegment {
	//this BodySegment will act like a NODE
	//  he will point to the next BodySegment in the Snake
	//  You will need a variable for this
	public static final int SIZE=15;
	public Color hue = Color.green;
	public static int nextID = 0;
	public int myID;
	private int xPos, yPos;
	BodySegment next;
	BodySegment prev;
	
	public int getxPos() {
		return xPos;
	}



	public void setxPos(int xPos) {
		this.xPos = xPos;
	}



	public int getyPos() {
		return yPos;
	}



	public void setyPos(int yPos) {
		this.yPos = yPos;
	}



	public BodySegment getNext() {
		return next;
	}



	public void setNext(BodySegment next) {
		this.next = next;
	}



	public BodySegment getPrev() {
		return prev;
	}



	public void setPrev(BodySegment prev) {
		this.prev = prev;
	}

//	private Object value;
	
	
	public BodySegment(int x, int y){
		xPos = x;
		yPos = y;
		
		myID = nextID++;
	}
	//Mutators

	//~~~~~~~~~~~~~~~~accessors and mutators~~~~~~~~~~~~~~~~~~~~~~
	
	
	
	public boolean isTouching( BodySegment sp){
/*		int xt= Math.abs(sp.getxPos()-this.getxPos());
		int yt= Math.abs(sp.getyPos()-this.getyPos());
		if(xt<15 && yt<15) {
			return true;
		}
		if(yt<15 && xt<15) {
		return false;
		}
		else
		return false;*/
		int x = sp.getxPos();
		int y = sp.getyPos();
		if(this.getxPos() == x && this.getyPos() == y) {
			return true;		
		}
		else {
			return false;
		}
	}
	
	//each BodySegment knows how to draw himself :)
	public void draw(Graphics g){
		g.setColor(hue);
		g.fillOval(xPos, yPos, SIZE, SIZE);
		//just for testing purposes, you can take this out later
		g.setColor(Color.BLACK);
		g.drawString(""+myID, xPos+SIZE/4, yPos+SIZE);
	}
}
