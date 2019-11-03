package mazeducks;
import java.awt.Color;

import javax.swing.JPanel;
import static mazeducks.TimedMaze.leftcheck;
import static mazeducks.TimedMaze.upcheck;

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
                TimedMaze.tr.moveLeft();
                TimedMaze.tl.moveLeft();
    	}
    }

    public void moveRight() {
    	if(x < TimedMaze.columns-1 && (TimedMaze.map[x+1][y] == 1 || TimedMaze.map[x+1][y] == 3 || TimedMaze.map[x+1][y] == 2)){                
	    	this.setLocation(this.getX()+25, this.getY());
	    	x++;         
                TimedMaze.tr.moveRight();
                if(!leftcheck)
                {
                    TimedMaze.tl.moveRight();
                }
    	}
    }

    public void moveUp() {
    	if(y > 0 && (TimedMaze.map[x][y-1] == 1 || TimedMaze.map[x][y-1] == 3 || TimedMaze.map[x][y-1] == 2)){
	    	this.setLocation(this.getX(), this.getY()-25);
	    	y--;  
                TimedMaze.td.moveUp();
                TimedMaze.tu.moveUp(); 
    	}
    }

    public void moveDown() {
    	if(y < TimedMaze.rows-1 && (TimedMaze.map[x][y+1] == 1 || TimedMaze.map[x][y+1] == 3 || TimedMaze.map[x][y+1] == 2)){
	    	this.setLocation(this.getX(), this.getY()+25);
	    	y++; 
                TimedMaze.td.moveDown();
                if(!upcheck)
                {
                    TimedMaze.tu.moveDown();
                }
    	}
    }
}
