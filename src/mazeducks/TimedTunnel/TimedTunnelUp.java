
package mazeducks.TimedTunnel;

import mazeducks.TimedTunnel.*;
import java.awt.Color;
import javax.swing.JPanel;
import static mazeducks.TimedMaze.columns;
import static mazeducks.TimedMaze.panelSize;
import static mazeducks.TimedMaze.rows;

public class TimedTunnelUp extends JPanel {
    int ysize;
    public TimedTunnelUp() 
    {
        this.setBackground(Color.BLACK);
        this.setSize((columns)*panelSize,0); //width, height
    }
    public void moveUp() {
    	ysize = this.getHeight();
        this.setSize(columns*panelSize,ysize - 25);          
    }

    public void moveDown() {    	
        ysize = this.getHeight();
        this.setSize(columns*panelSize,ysize + 25);         
    }
    
}
