
package mazeducks.TimedTunnel;

import mazeducks.TimedTunnel.*;
import java.awt.Color;
import javax.swing.JPanel;
import static mazeducks.TimedMaze.columns;
import static mazeducks.TimedMaze.panelSize;
import static mazeducks.TimedMaze.rows;

public class TimedTunnelDown extends JPanel {
    int ysize = (rows-4)*panelSize;
    public TimedTunnelDown() 
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
