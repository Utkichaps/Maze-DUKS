package mazeducks;
import java.awt.Color;

import javax.swing.JPanel;


public class Player extends JPanel{
	int x, y;
	
    public Player() {
    	this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
    	this.setSize(TestMaze.panelSize, TestMaze.panelSize);
    }

    public void moveLeft() {
    	if((x > 0) && (TestMaze.map[x-1][y] == 1 || TestMaze.map[x-1][y] == 3 || TestMaze.map[x-1][y] == 2)){
	    	this.setLocation(this.getX()-25, this.getY());
	    	x--;
    	}
    }

    public void moveRight() {
    	if(x < TestMaze.columns-1 && (TestMaze.map[x+1][y] == 1 || TestMaze.map[x+1][y] == 3 || TestMaze.map[x+1][y] == 2)){
	    	this.setLocation(this.getX()+25, this.getY());
	    	x++;
    	}
    }

    public void moveUp() {
    	if(y > 0 && (TestMaze.map[x][y-1] == 1 || TestMaze.map[x][y-1] == 3 || TestMaze.map[x][y-1] == 2)){
	    	this.setLocation(this.getX(), this.getY()-25);
	    	y--;
    	}
    }

    public void moveDown() {
    	if(y < TestMaze.rows-1 && (TestMaze.map[x][y+1] == 1 || TestMaze.map[x][y+1] == 3 || TestMaze.map[x][y+1] == 2)){
	    	this.setLocation(this.getX(), this.getY()+25);
	    	y++;
    	}
    }
}
