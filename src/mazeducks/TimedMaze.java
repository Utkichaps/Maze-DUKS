package mazeducks;

import mazeducks.TimedTunnel.TimedTunnelDown;
import mazeducks.TimedTunnel.TimedTunnelUp;
import mazeducks.TimedTunnel.TimedTunnelLeft;
import mazeducks.TimedTunnel.TimedTunnelRight;

import java.util.Timer;
import java.util.TimerTask;

import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import static mazeducks.MapGenerate.loadMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class TimedMaze extends JFrame{

    public static int rows = 40; 
    public static int columns = 40;
    public static int panelSize = 25;
    public static int map[][] = new int[columns][rows];
    public static int endLevelLoc;
    public static int endLevelLocx;
    public static int count;
    TimedPlayer p;
    public static TimedTunnelRight tr;
    public static TimedTunnelLeft tl;
    public static TimedTunnelDown td;
    public static TimedTunnelUp tu;
    public JLabel clk;
    public TimedTimer clock = new TimedTimer();
    public static boolean leftcheck;
    public static boolean upcheck;
    public static int min,sec,hour;
    public TimedMaze(int timelimit)
    {
        min = timelimit;
        sec = 0;
        hour = 0; 
        count = 0;
    }
    public TimedMaze(String str, int b){
        this.setFocusable(true);
        rows = b;
        columns = b;   
        leftcheck = true;
        upcheck = true;
        map = loadMap(b);        
        this.setResizable(false);
        this.setSize((columns*panelSize)+50+150, (rows*panelSize)+70); //width , height
        this.setTitle("Timed Maze");
        this.setLayout(null);
        
        //Maze external UI:
        JPanel jp = new JPanel();
        jp.setLocation((columns*panelSize)+50,25);
        jp.setSize(120,(rows*panelSize));
        jp.setBackground(Color.red);
        jp.setVisible(true);
        this.add(jp);
        JButton qbutton = new JButton("Quit");
        qbutton.setPreferredSize(new Dimension(120,50));
        qbutton.setVisible(true);        
         qbutton.addActionListener(new ActionListener()
         {  
            public void actionPerformed(ActionEvent e)
            { 
                int opt;
                clock.stop();
                opt = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?");
                if(opt == JOptionPane.YES_OPTION)
                {                                        
                    Timed C = new Timed();
                    C.setVisible(true);
                    TimedMaze.this.dispose();
                } 
                else
                {
                    clock.start();
                    qbutton.setFocusable(false);                    
                    TimedMaze.this.setFocusable(true);                    
                }
            }  
         });  
        jp.add(qbutton);    
        
        JLabel timelabel = new JLabel("Time:");
        timelabel.setVisible(true);
        timelabel.setPreferredSize(new Dimension(120,25));
        timelabel.setHorizontalAlignment(JLabel.CENTER);
        timelabel.setFont(timelabel.getFont().deriveFont (22.0f));
        jp.add(timelabel);
        
        clk = new JLabel(hour+":"+min+":"+sec);
        clk.setVisible(true);
        clk.setPreferredSize(new Dimension(120,30));
        clk.setHorizontalAlignment(JLabel.CENTER);
        clk.setFont(clk.getFont().deriveFont (22.0f));
        jp.add(clk);
        
        JLabel cnt = new JLabel("Number: "+count);
        cnt.setHorizontalAlignment(JLabel.CENTER);
        cnt.setFont(timelabel.getFont().deriveFont (18.0f));
        cnt.setVisible(true);
        jp.add(cnt);
        
        this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();				
				revalidate();
				repaint();
				
				//Player movement
				if(key == KeyEvent.VK_UP){
					p.moveUp();
				}
				if(key == KeyEvent.VK_LEFT){
					p.moveLeft();                                        
				}
				if(key == KeyEvent.VK_DOWN){
					p.moveDown();
				}
				if(key == KeyEvent.VK_RIGHT){
					p.moveRight();                                        
				}
                                if(p.x == 3 && leftcheck)
                                {
                                    tl.setLocation(23,25);
                                    TimedMaze.this.add(tl,1);
                                    leftcheck = false;
                                }
                                if(p.y == 3 && upcheck)
                                {
                                    tu.setLocation(23,25);
                                    TimedMaze.this.add(tu,1);
                                    upcheck = false;
                                }
				
				if(p.x == endLevelLocx && p.y == endLevelLoc){
                                        clock.stop();
                                        count+=1;
                                        new TimedMaze("test",b+5);					
					dispose();					                                       
				}
                                if(hour == 0 && min == 0 && sec == 0 || hour<0)
                                {
                                        clock.stop();
					JOptionPane.showMessageDialog(null, "Number of mazes solved: "+count, "End Game", JOptionPane.INFORMATION_MESSAGE);                                        
					dispose();					
                                        Timed C = new Timed();
                                        C.setVisible(true);
                                }
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {                //System.out.println((columns*panelSize)+50+"-"+((rows*panelSize)+70));
                System.exit(0);
            }
        });        
        this.setLocationRelativeTo(null);                
        
        
        //Create player & opt.tunnelvision
    	p = new TimedPlayer();
    	p.setVisible(true);        
        tr = new TimedTunnelRight();
        tl = new TimedTunnelLeft();
        td = new TimedTunnelDown();
        tu = new TimedTunnelUp();
        tr.setVisible(true);
        tl.setVisible(true);
        td.setVisible(true);
        tu.setVisible(true);
    	this.add(p); 
        this.add(tr);
        this.add(td);
    	
        //Color map        
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
                Tile tile = new Tile(x, y);
                tile.setSize(panelSize, panelSize);
                tile.setLocation((x*panelSize)+23, (y*panelSize)+25);
                if(map[x][y] == 0)
                {
                    tile.setBackground(Color.GRAY);
                }              
                else if(map[x][y] == 2)
                {
                    tile.setBackground(Color.WHITE);
                    tile.setWall(false);
                    p.setLocation((x*panelSize)+23, (y*panelSize)+25);
                    p.y = y;   
                    tr.setLocation(((x+4)*panelSize)+23, (y*panelSize)+25);  
                    td.setLocation((x*panelSize)+23,((y+5)*panelSize));
                }
                else if(map[x][y] == 3)
                {
                    tile.setBackground(Color.RED);
                    tile.setWall(false);
                    endLevelLocx = x;
                    endLevelLoc = y; 
                }
                else
                {
                    tile.setBackground(Color.WHITE);
                    tile.setWall(false);                    
                }
                
                tile.setVisible(true);
                this.add(tile);
                //System.out.print(map[x][y]);
            }
            //System.out.println();            
        }           
        //System.out.println(map[0][0]);                                               
        this.setVisible(true);
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {                
                clock.start();
            }
        });
        
        Timer ti = new Timer();
        ti.schedule(new TimerTask() {
            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                clk.setText(hour+":"+min+":"+sec);
            }
        },0,1000);
    }   
    
}
