package mazeducks;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ArcadeMaze extends JFrame{

    public static int rows = 40; 
    public static int columns = 40;
    public static int panelSize = 25;
    public static int map[][] = new int[columns][rows];
    public static int endLevelLoc,keyLocy;
    public static int endLevelLocx,keyLocx;
    public static int score, score_no = 6;
    public static boolean getKey;
    public static ScoreTile SC[] = new ScoreTile[score_no]; 
    
    ArcadePlayer p;
    
    public ArcadeMaze(String str, int b){
        this.setFocusable(true);
        rows = b;
        columns = b;
        score = 0;
        getKey = false;
        map = loadMap(b);
        this.setResizable(false);
        this.setSize((columns*panelSize)+50+150, (rows*panelSize)+70); //width , height
        this.setTitle("Arcade Maze");
        this.setLayout(null);
        
        //Maze External UI
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
                    Arcade A = new Arcade();
                    A.setVisible(true);
                    ArcadeMaze.this.dispose();
                } 
                else
                {
                    qbutton.setFocusable(false);                    
                    ArcadeMaze.this.setFocusable(true);                    
                }
            }  
         });  
        jp.add(qbutton); 
        /*JLabel scorelabel = new JLabel("Score:");
        scorelabel.setLocation(0,75);
        scorelabel.setVisible(true);
        jp.add(scorelabel,1);*/
        
        
        
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
				
				if(p.x == endLevelLocx && p.y == endLevelLoc && getKey){
					JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!", "End Game", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					Arcade A = new Arcade();
                                        A.setVisible(true);
				}
                                if(p.x == endLevelLocx && p.y == endLevelLoc && !getKey)
                                {
                                        JOptionPane.showMessageDialog(null, "Door still locked!", "Find Key", JOptionPane.WARNING_MESSAGE);
                                }
                                if(p.x == keyLocx && p.y == keyLocy)
                                {
                                    getKey = true;
                                    score += 15;
                                    System.out.println(score);//
                                    JPanel P = new JPanel();
                                    P.setSize(panelSize,panelSize);
                                    P.setLocation((keyLocx*panelSize)+23, (keyLocy*panelSize)+25);
                                    P.setBackground(Color.WHITE);
                                    P.setVisible(true);                                         
                                    ArcadeMaze.this.add(P, 3);                                                                                                            
                                }
                                for(int i = 0; i < score_no; i++)
                                {
                                    if(p.x == SC[i].x && p.y == SC[i].y && !SC[i].visited)
                                    {
                                        score += 10;
                                        SC[i].visited = true;
                                        JPanel P = new JPanel();
                                        P.setSize(panelSize,panelSize);
                                        P.setLocation((SC[i].x*panelSize)+23, (SC[i].y*panelSize)+25);
                                        P.setBackground(Color.WHITE);
                                        P.setVisible(true);                                         
                                        ArcadeMaze.this.add(P, 3);
                                        System.out.println(score);//
                                    }
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
        
        //Create player
    	p = new ArcadePlayer();
    	p.setVisible(true);
    	this.add(p,1);
        
        
        double tempy,tempx;     
        boolean check = true;
        
        //Adding key
        while(check)
        {
            tempy = Math.floor(Math.random()*columns);
            tempx = Math.floor(Math.random()*rows);
            keyLocx = (int)tempx;
            keyLocy = (int)tempy;
            if(map[keyLocx][keyLocy] == 3 || map[keyLocx][keyLocy] == 2) //start or stop position
                continue;     
            
            if(keyLocx < 10)
                keyLocx += 15;
            else if(keyLocy < 10)
                keyLocy += 15;
            
            if(map[keyLocx][keyLocy] == 0)
                continue;
            
            check = false;            
            map[keyLocx][keyLocy] = 4;
            System.out.println("End location: "+endLevelLocx + " " +endLevelLoc);
            System.out.println("Key Loc: "+keyLocx+" "+keyLocy);
        }
        //Adding score tiles
        int tx,ty;               
        for(int i = 0; i < score_no; i++)
        {
            check = true;
            while(check)
            {
                tempy = Math.floor(Math.random()*columns);
                tempx = Math.floor(Math.random()*rows);
                tx = (int)tempx;
                ty = (int)tempy;
                if(map[tx][ty] == 3 || map[tx][ty] == 2 || map[tx][ty] == 4 || map[tx][ty] == 0)
                    continue;
                check = false;
                map[tx][ty] = 5;
                SC[i] = new ScoreTile(tx,ty);
            }
            
        }
    	/*
        Map legend:
        0 - Wall
        1 - Path
        2 - Start
        3 - End
        4 - Key
        5 - ScoreTile
        */
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
                }
                else if(map[x][y] == 3)
                {
                    tile.setBackground(Color.RED);
                    tile.setWall(false);
                    endLevelLocx = x;
                    endLevelLoc = y; 
                }
                else if(map[x][y] == 4)
                {
                    tile.setBackground(Color.BLUE);
                    tile.setWall(false);
                }
                else if(map[x][y] == 5)
                {
                    tile.setBackground(Color.YELLOW);
                    tile.setWall(false);
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
        
        this.setVisible(true);
    }   
    
}


