package mazeducks;
import java.awt.Color;

import javax.swing.JPanel;

public class TimedPlayer extends JPanel{
	int x, y;
	
    public TimedPlayer() {
    	this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
    	this.setSize(TimedMaze.panelSize, TimedMaze.panelSize);
    }

    public void moveLeft() {
    	if((x > 0) && (TimedMaze.map[x-1][y] == 1 || TimedMaze.map[x-1][y] == 3 || TimedMaze.map[x-1][y] == 2)){                  
	    	this.setLocation(this.getX()-25, this.getY());                
	    	x--;
    	}
    }

    public void moveRight() {
    	if(x < TimedMaze.columns-1 && (TimedMaze.map[x+1][y] == 1 || TimedMaze.map[x+1][y] == 3 || TimedMaze.map[x+1][y] == 2)){                
	    	this.setLocation(this.getX()+25, this.getY());
	    	x++;                
    	}
    }

    public void moveUp() {
    	if(y > 0 && (TimedMaze.map[x][y-1] == 1 || TimedMaze.map[x][y-1] == 3 || TimedMaze.map[x][y-1] == 2)){
	    	this.setLocation(this.getX(), this.getY()-25);
	    	y--;               
    	}
    }

    public void moveDown() {
    	if(y < TimedMaze.rows-1 && (TimedMaze.map[x][y+1] == 1 || TimedMaze.map[x][y+1] == 3 || TimedMaze.map[x][y+1] == 2)){
	    	this.setLocation(this.getX(), this.getY()+25);
	    	y++;                
    	}
    }
}
