/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeducks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import static mazeducks.TimedMaze.min;
import static mazeducks.TimedMaze.sec;
import static mazeducks.TimedMaze.hour;

class TimedTimer {
    
    private final ClockListener cl = new ClockListener();
    private final Timer t = new Timer(1000, cl);    

    public TimedTimer() 
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
                      
            sec--;
            if(sec == -1)
            {
                min--;
                sec = 59;
                if(min == -1)
                {
                    hour--;
                    min = 59;
                }
            }            
        }
    } 
}
