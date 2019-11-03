
package mazeducks.TimedTunnel;

import mazeducks.TimedTunnel.*;
import java.awt.Color;
import javax.swing.JPanel;
import static mazeducks.TimedMaze.columns;
import static mazeducks.TimedMaze.panelSize;
import static mazeducks.TimedMaze.rows;

public class TimedTunnelLeft extends JPanel {
    int xsize;
    public TimedTunnelLeft()
    {
        this.setBackground(Color.BLACK);
        this.setSize(0, ((rows)*panelSize)); //width, height
    }
    public void moveLeft() {    
                xsize = this.getWidth();
                this.setSize(xsize - 25, ((rows)*panelSize));                	    	
    }

    public void moveRight() {            	
                xsize = this.getWidth();
                this.setSize(xsize + 25, ((rows)*panelSize));                   
    }
    
}
