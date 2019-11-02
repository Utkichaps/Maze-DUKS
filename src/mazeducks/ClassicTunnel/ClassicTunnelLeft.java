
package mazeducks.ClassicTunnel;

import java.awt.Color;
import javax.swing.JPanel;
import static mazeducks.ClassicMaze.columns;
import static mazeducks.ClassicMaze.panelSize;
import static mazeducks.ClassicMaze.rows;

public class ClassicTunnelLeft extends JPanel {
    int xsize;
    public ClassicTunnelLeft()
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
