
package mazeducks.ClassicTunnel;

import java.awt.Color;
import javax.swing.JPanel;
import static mazeducks.ClassicMaze.columns;
import static mazeducks.ClassicMaze.panelSize;
import static mazeducks.ClassicMaze.rows;

public class ClassicTunnelDown extends JPanel {
    int ysize = (rows-4)*panelSize;
    public ClassicTunnelDown() 
    {
        this.setBackground(Color.BLACK);
        this.setSize((columns)*panelSize,ysize); //width, height
    }
    public void moveUp() {
    	this.setSize((columns)*panelSize,ysize + (25*6) - this.getY());
	this.setLocation(this.getX(), this.getY()-25);
    }

    public void moveDown() {    	
        this.setSize((columns)*panelSize,ysize + (25*4) - this.getY());
        this.setLocation(this.getX(),this.getY() + 25);
    }
    
}
