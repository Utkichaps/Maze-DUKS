package mazeducks;
import java.awt.Color;

import javax.swing.JPanel;


public class ArcadePlayer extends JPanel{
	int x, y;
	
    public ArcadePlayer() {
    	this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
    	this.setSize(ArcadeMaze.panelSize, ArcadeMaze.panelSize);
    }

    public void moveLeft() {
    	if((x > 0) && (ArcadeMaze.map[x-1][y] == 1 || ArcadeMaze.map[x-1][y] == 3 || ArcadeMaze.map[x-1][y] == 2 || ArcadeMaze.map[x-1][y] == 4)){
	    	this.setLocation(this.getX()-25, this.getY());
	    	x--;
    	}
    }

    public void moveRight() {
    	if(x < ArcadeMaze.columns-1 && (ArcadeMaze.map[x+1][y] == 1 || ArcadeMaze.map[x+1][y] == 3 || ArcadeMaze.map[x+1][y] == 2 || ArcadeMaze.map[x+1][y] == 4)){
	    	this.setLocation(this.getX()+25, this.getY());
	    	x++;
    	}
    }

    public void moveUp() {
    	if(y > 0 && (ArcadeMaze.map[x][y-1] == 1 || ArcadeMaze.map[x][y-1] == 3 || ArcadeMaze.map[x][y-1] == 2 || ArcadeMaze.map[x][y-1] == 4)){
	    	this.setLocation(this.getX(), this.getY()-25);
	    	y--;
    	}
    }

    public void moveDown() {
    	if(y < ArcadeMaze.rows-1 && (ArcadeMaze.map[x][y+1] == 1 || ArcadeMaze.map[x][y+1] == 3 || ArcadeMaze.map[x][y+1] == 2 || ArcadeMaze.map[x][y+1] == 4)){
	    	this.setLocation(this.getX(), this.getY()+25);
	    	y++;
    	}
    }
}
