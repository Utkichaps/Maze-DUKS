package mazeducks;
import java.awt.Color;

import javax.swing.JPanel;
import static mazeducks.ClassicMaze.leftcheck;
import static mazeducks.ClassicMaze.upcheck;


public class ClassicPlayer extends JPanel{
	int x, y;
	
    public ClassicPlayer() {
    	this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
    	this.setSize(ClassicMaze.panelSize, ClassicMaze.panelSize);
    }

    public void moveLeft() {
    	if((x > 0) && (ClassicMaze.map[x-1][y] == 1 || ClassicMaze.map[x-1][y] == 3 || ClassicMaze.map[x-1][y] == 2)){                  
	    	this.setLocation(this.getX()-25, this.getY());                
	    	x--;
                ClassicMaze.tr.moveLeft();
                ClassicMaze.tl.moveLeft();
    	}
    }

    public void moveRight() {
    	if(x < ClassicMaze.columns-1 && (ClassicMaze.map[x+1][y] == 1 || ClassicMaze.map[x+1][y] == 3 || ClassicMaze.map[x+1][y] == 2)){                
	    	this.setLocation(this.getX()+25, this.getY());
	    	x++;
                ClassicMaze.tr.moveRight();
                if(!leftcheck)
                {
                    ClassicMaze.tl.moveRight();
                }
    	}
    }

    public void moveUp() {
    	if(y > 0 && (ClassicMaze.map[x][y-1] == 1 || ClassicMaze.map[x][y-1] == 3 || ClassicMaze.map[x][y-1] == 2)){
	    	this.setLocation(this.getX(), this.getY()-25);
	    	y--;
                ClassicMaze.td.moveUp();
                ClassicMaze.tu.moveUp();                                
    	}
    }

    public void moveDown() {
    	if(y < ClassicMaze.rows-1 && (ClassicMaze.map[x][y+1] == 1 || ClassicMaze.map[x][y+1] == 3 || ClassicMaze.map[x][y+1] == 2)){
	    	this.setLocation(this.getX(), this.getY()+25);
	    	y++;
                ClassicMaze.td.moveDown();
                if(!upcheck)
                {
                    ClassicMaze.tu.moveDown();
                }
    	}
    }
}
