/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeducks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import static mazeducks.ArcadeMaze.min;
import static mazeducks.ArcadeMaze.sec;
import static mazeducks.ArcadeMaze.hour;

class ArcadeTimer {
    
    private final ClockListener cl = new ClockListener();
    private final Timer t = new Timer(1000, cl);    

    public ArcadeTimer() 
    {
        t.setInitialDelay(0);                   
    }

    public void start() {
        t.start();
    }
    public void stop() {
        t.stop();
    }

    private class ClockListener implements ActionListener {        
        @Override
        public void actionPerformed(ActionEvent e) {
                      
            sec++;
            if(sec == 60)
            {
                min++;
                sec = 0;
                if(min == 60)
                {
                    hour++;
                    min = 0;
                }
            }            
        }
    } 
}
