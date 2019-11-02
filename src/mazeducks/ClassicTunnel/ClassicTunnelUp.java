
package mazeducks.ClassicTunnel;

import java.awt.Color;
import javax.swing.JPanel;
import static mazeducks.ClassicMaze.columns;
import static mazeducks.ClassicMaze.panelSize;
import static mazeducks.ClassicMaze.rows;

public class ClassicTunnelUp extends JPanel {
    int ysize;
    public ClassicTunnelUp() 
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
