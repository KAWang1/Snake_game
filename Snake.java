import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Snake implements KeyListener{
	public static final int UP=0, RIGHT=1, DOWN=2, LEFT=3;
	
	//variables you will need
	BodySegment head;
	BodySegment tail;
	int dir;
	int size=0;
	// 1) pointer to the first and/or last piece of the snake
	// 2) a variable to keep track of what direction this snake is facing
	
	public Snake(){
		//go ahead and give him 1 BodySegment
//		int x= ((int)((Math.random()*200)+1));
//		int y= ((int)((Math.random()*200) +1));
		int d= ((int)((Math.random()*3) +1));
		head = new BodySegment(150, 150);
		tail = head;
		dir = d;				//direction
		size++;
		//tell him what direction he is moving		
	}
															//link list = snake    node = BodySegment
	public int size(){
		return size;
	}
	
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public BodySegment getHead() {
		return head;
	}
	public void setHead(BodySegment head) {
		this.head = head;
	}
	public BodySegment getTail() {
		return tail;
	}
	public void setTail(BodySegment tail) {
		this.tail = tail;
	}
	
	public void addFirst(){
		int newX = head.getxPos();
		int newY = head.getyPos();
		if(dir == UP) {
			newY -= 15;
		}
		if(dir == DOWN) {
			newY += 15;
		}
		if(dir == LEFT) {
			newX -= 15;
		}
		if(dir == RIGHT) {
			newX += 15;
		}
		
		BodySegment newHead = new BodySegment(newX, newY);
		newHead.setNext(head);
		head.setPrev(newHead);
		head = newHead;
		size++;
	}
	
	public void removeLast(){
		tail = tail.getPrev();
		tail.setNext(null);
		size--;
	}
	
	
	public void draw(Graphics g){
		//tell EACH segment to draw (and pass it g)
		BodySegment curr = head;
		while(curr.getNext() != null) {
			curr.draw(g);
			curr = curr.getNext();
		}
	}
	
	
	@Override
	//make the snake respond to key presses
	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode() == KeyEvent.VK_UP && dir != DOWN) {
			System.out.println("User pressed up");
			dir = UP;
			}
		if(e.getKeyCode() == KeyEvent.VK_DOWN && dir != UP) {
			System.out.println("User pressed down");
			dir = DOWN;
			}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && dir != RIGHT) {
			System.out.println("User pressed left");
			dir = LEFT;
			}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && dir != LEFT) {
			System.out.println("User pressed right");
			dir = RIGHT;
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	
}
