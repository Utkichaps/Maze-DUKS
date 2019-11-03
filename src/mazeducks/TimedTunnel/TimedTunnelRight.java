
package mazeducks.TimedTunnel;

import mazeducks.TimedTunnel.*;
import java.awt.Color;
import javax.swing.JPanel;
import static mazeducks.TimedMaze.columns;
import static mazeducks.TimedMaze.panelSize;
import static mazeducks.TimedMaze.rows;

public class TimedTunnelRight extends JPanel{
    //int x, y;
    int xsize = (columns-4)*panelSize;
    public TimedTunnelRight()
    {
        this.setBackground(Color.BLACK);
        this.setSize(xsize, ((rows)*panelSize)); //width, height
    }
    public void moveLeft() {                 
                this.setSize(xsize + (25*6) - this.getX(), ((rows)*panelSize));                
	    	this.setLocation(this.getX()-25, this.getY());	 
                //System.out.println(this.getX() - (23*6));
    }

    public void moveRight() {            	
                this.setSize(xsize + (25*4) - this.getX(), ((rows)*panelSize));                
	    	this.setLocation(this.getX()+25, this.getY());
                //System.out.println("right: " + (((columns-6)*panelSize) + (25*6) - this.getX());
    }
}
