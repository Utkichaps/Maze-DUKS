package mazeducks;

import mazeducks.ClassicTunnel.ClassicTunnelRight;
import mazeducks.ClassicTunnel.ClassicTunnelLeft;
import mazeducks.ClassicTunnel.ClassicTunnelDown;
import mazeducks.ClassicTunnel.ClassicTunnelUp;

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


public class ClassicMaze extends JFrame{

    public static int rows = 40; 
    public static int columns = 40;
    public static int panelSize = 25;
    public static int map[][] = new int[columns][rows];
    public static int endLevelLoc;
    public static int endLevelLocx;
    ClassicPlayer p;
    public static ClassicTunnelRight tr;
    public static ClassicTunnelLeft tl;
    public static ClassicTunnelDown td;
    public static ClassicTunnelUp tu;
    public JLabel clk;
    public ClassicTimer clock = new ClassicTimer();
    public static boolean leftcheck;
    public static boolean upcheck;
    public static int min,sec,hour;
    
    public ClassicMaze(int tunneltog, int b, boolean prac){
        this.setFocusable(true);
        rows = b;
        columns = b;
        min = 0;
        sec = 0;
        hour = 0;
        leftcheck = true;
        upcheck = true;
        map = loadMap(b);        
        this.setResizable(false);
        this.setSize((columns*panelSize)+50+150, (rows*panelSize)+70); //width , height
        this.setTitle("Classic Maze");
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
                opt = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?");
                if(opt == JOptionPane.YES_OPTION)
                {                    
                    clock.stop();
                    Classic C = new Classic();
                    C.setVisible(true);
                    ClassicMaze.this.dispose();
                } 
                else
                {
                    qbutton.setFocusable(false);                    
                    ClassicMaze.this.setFocusable(true);                    
                }
            }  
         });  
        jp.add(qbutton);    
                
        JLabel timelabel = new JLabel("Time:");
        timelabel.setVisible(true);
        timelabel.setPreferredSize(new Dimension(120,25));
        timelabel.setHorizontalAlignment(JLabel.CENTER);
        timelabel.setFont(timelabel.getFont().deriveFont (22.0f));  
        if(!prac)
            jp.add(timelabel);
        
        clk = new JLabel("0:0:0");
        clk.setVisible(true);
        clk.setFont(clk.getFont().deriveFont (22.0f));
        if(!prac)
            jp.add(clk);
        
        
        
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
                                    ClassicMaze.this.add(tl,1);
                                    leftcheck = false;
                                }
                                if(p.y == 3 && upcheck)
                                {
                                    tu.setLocation(23,25);
                                    ClassicMaze.this.add(tu,1);
                                    upcheck = false;
                                }
				
				if(p.x == endLevelLocx && p.y == endLevelLoc){
                                        clock.stop();
                                        if(!prac)
                                            JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!\nTime taken: "+hour+":"+min+":"+sec, "End Game", JOptionPane.INFORMATION_MESSAGE);
                                        else
                                            JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!", "End Game", JOptionPane.INFORMATION_MESSAGE);
					dispose();					
                                        Classic C = new Classic();
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
            public void windowClosing(WindowEvent e) {
                //System.out.println((columns*panelSize)+50+"-"+((rows*panelSize)+70));
                System.exit(0);
            }
        });        
        this.setLocationRelativeTo(null);                
        
        
        //Create player & opt.tunnelvision
    	p = new ClassicPlayer();
        tr = new ClassicTunnelRight();
        tl = new ClassicTunnelLeft();
        td = new ClassicTunnelDown();
        tu = new ClassicTunnelUp();
    	p.setVisible(true);        
        if(tunneltog == 1)  //toggle tunnel vision button
        {
            tr.setVisible(true);
            tl.setVisible(true);
            td.setVisible(true);
            tu.setVisible(true);
        }
        else
        {
            tr.setVisible(false);
            tl.setVisible(false);
            td.setVisible(false);
            tu.setVisible(false);
        }
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
