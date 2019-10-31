package mazeducks;
import java.awt.Color;

import javax.swing.JPanel;


public class Player extends JPanel{
	int x, y;
	
    public Player() {
    	this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
    	this.setSize(ClassicMaze.panelSize, ClassicMaze.panelSize);
    }

    public void moveLeft() {
    	if((x > 0) && (ClassicMaze.map[x-1][y] == 1 || ClassicMaze.map[x-1][y] == 3 || ClassicMaze.map[x-1][y] == 2)){
	    	this.setLocation(this.getX()-25, this.getY());
	    	x--;
    	}
    }

    public void moveRight() {
    	if(x < ClassicMaze.columns-1 && (ClassicMaze.map[x+1][y] == 1 || ClassicMaze.map[x+1][y] == 3 || ClassicMaze.map[x+1][y] == 2)){
	    	this.setLocation(this.getX()+25, this.getY());
	    	x++;
    	}
    }

    public void moveUp() {
    	if(y > 0 && (ClassicMaze.map[x][y-1] == 1 || ClassicMaze.map[x][y-1] == 3 || ClassicMaze.map[x][y-1] == 2)){
	    	this.setLocation(this.getX(), this.getY()-25);
	    	y--;
    	}
    }

    public void moveDown() {
    	if(y < ClassicMaze.rows-1 && (ClassicMaze.map[x][y+1] == 1 || ClassicMaze.map[x][y+1] == 3 || ClassicMaze.map[x][y+1] == 2)){
	    	this.setLocation(this.getX(), this.getY()+25);
	    	y++;
    	}
    }
}
