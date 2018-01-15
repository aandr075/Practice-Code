import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;

/**
  * A Maze and a solution is drawn on a JPanel
  */
public class MazePanel extends JPanel {
	private int width;
	private int height;
	private Image theImage;
	
	/**
	  * Constructor
	  * @param width the width of the JPanel
	  * @param height the height of the JPanel
	  */
	public MazePanel(int width,int height) {
		super();
		setBackground(Color.white);
		setPreferredSize(new Dimension(width,height));
		this.width = width;
		this.height = height;
	}
	
	/**
	  * Draw the Maze on the JPanel
	  * @param maze the Maze to be drawn
	  */
	public void drawMaze(Maze maze) {
		if( maze == null ) return;
		theImage = this.createImage(width,height);
		if( theImage == null ) return;
		Graphics2D g = (Graphics2D)theImage.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Color.black);
		int rows = maze.getRows();
		int columns = maze.getColumns();
		int h = (getWidth()-24)/columns;
		int k = (getHeight()-18)/rows;
		int j = (this.getHeight()-k*rows)/2;
		for( int jAddress = 0; jAddress < rows; jAddress++ ) {
			int i = (this.getWidth()-h*columns)/2;
			for( int iAddress = 0; iAddress < columns; iAddress++ ) {
				if( maze.isClosed(jAddress,iAddress,maze.NORTH) ) g.drawLine(i,j,i+h,j);
				if( maze.isClosed(jAddress,iAddress,maze.WEST) ) g.drawLine(i,j,i,j+k);
				i += h;
			} // end for
			if( maze.isClosed(jAddress,columns-1,maze.EAST) )g.drawLine(i,j,i,j+k);
			j += k;
		} // end for
		int i = (this.getWidth()-h*columns)/2;
		for( int iAddress = 0; iAddress < columns; iAddress++ ) {
		if( maze.isClosed(rows-1,iAddress,maze.SOUTH) )g.drawLine(i,j,i+h,j);
			i += h;
		} // end for
		repaint();
	} // end drawMaze
	
	public void paintComponent(Graphics g) {
		if( theImage == null ) return;
		g.drawImage(theImage,0,0,width,height,null);
	}

				
	/**
	  * Draw the solution as a series of small triangles on the Maze
	  * @param maze the Maze
	  * @param A is an ArrayList of Maze.Door.
	  */
	public void drawSolution(Maze maze, ArrayList A) {
		if( maze == null ) return;
		if( A == null ) {
			JOptionPane.showMessageDialog(null,"The maze has no solution!");
			return;
		} // end if
		Graphics2D g = (Graphics2D)getGraphics();
		Graphics2D gI = (Graphics2D)theImage.getGraphics();
		int rows = maze.getRows();
		int columns = maze.getColumns();
		int h = (getWidth()-24)/columns;
		int k = (getHeight()-18)/rows;
		int y = (this.getHeight()-k*rows)/2;
		int x = (this.getWidth()-h*columns)/2;
		Maze.Door door = new Maze.Door(0,0,-1,Color.black);
		for(int l = 0; l < A.size(); l++ ) {
			Maze.Door oldDoor = door;
			door = (Maze.Door)A.get(l);
			if( door.equals(oldDoor) ) continue;
			int rowNum = door.row;
			int colNum = door.column;
			Color color = door.color;
			int j = y + k*rowNum;
			int i = x + h*colNum;
			g.setColor(Color.white);
			gI.setColor(Color.white);
			g.fillRect(i+h/4,j+k/4,5*h/8,5*k/8);
			gI.fillRect(i+h/4,j+k/4,5*h/8,5*k/8);
			if( door.wall == maze.EAST ) {
				Point p1 = new Point(i+h/4,j+k/4);
				Point p2 = new Point(i+h/4,j+3*k/4);
				Point p3 = new Point(i+3*h/4,j+k/2);
				drawTriangle(p1,p2,p3,color);
			} else if ( door.wall == maze.SOUTH ) {
				Point p1 = new Point(i+h/4,j+k/4);
				Point p2 = new Point(i+3*h/4,j+k/4);
				Point p3 = new Point(i+h/2,j+3*k/4);
				drawTriangle(p1,p2,p3,color);
			} else if ( door.wall == maze.WEST ) {
				Point p1 = new Point(i+3*h/4,j+k/4);
				Point p2 = new Point(i+3*h/4,j+3*k/4);
				Point p3 = new Point(i+h/4,j+k/2);
				drawTriangle(p1,p2,p3,color);
			} else if ( door.wall == maze.NORTH ) {
				Point p1 = new Point(i+h/4,j+3*k/4);
				Point p2 = new Point(i+3*h/4,j+3*k/4);
				Point p3 = new Point(i+h/2,j+k/4);
				drawTriangle(p1,p2,p3,color);
			} else if ( door.wall == maze.NO_DIRECTION ) {
				drawRect(door.row,door.column,maze,door.color);
			} // end if
			int s = 60000 / (rows*columns);
			s = s<10?10:s;
			s = s>100?100:s;
			try{Thread.sleep(s);}catch(Exception e){}
		} // end for
	} // end drawSolution
	
	public void drawRect(int row, int col,Maze maze, Color color) {
		int width = (getWidth()-24)/maze.getColumns();
		int height = (getHeight()-18)/maze.getRows();
		Graphics2D g = (Graphics2D)getGraphics();
		Graphics2D gI = (Graphics2D)theImage.getGraphics();
		g.setColor(color);
		gI.setColor(color);
		int h = width;
		int k = height;
		int j = (this.getHeight()-k*maze.getRows())/2;
		j += k*row;
		int i = (this.getWidth()-h*maze.getColumns())/2;
		i += h*col;
		g.fillRect(i+h/4,j+k/4,(h>6?5*h/8:3),(k>6?5*k/8:3));
		gI.fillRect(i+h/4,j+k/4,(h>6?5*h/8:3),(k>6?5*k/8:3));
	} // end drawRect
	
	public void drawTriangle(Point p1,Point p2,Point p3,Color color) {
		Graphics2D g = (Graphics2D)getGraphics();
		Graphics2D gI = (Graphics2D)theImage.getGraphics();
		g.setColor(color);
		gI.setColor(color);
		int[] xs = { p1.x,p2.x,p3.x};
		int[] ys = {p1.y,p2.y,p3.y};
		g.setColor(color);
		gI.setColor(color);
		g.fillPolygon(xs,ys,3);
		gI.fillPolygon(xs,ys,3);
	} // end drawTriangle

	
} // end MazePanel
