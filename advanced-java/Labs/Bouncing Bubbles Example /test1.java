import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.*;
import java.util.*;

public class test1 extends JFrame implements MouseListener  {
    

    public test1() {
    
   Panel JP = new Panel();
    
    
        JP.setBorder(BorderFactory.createLineBorder(Color.black)); 
        JP.addMouseListener(this);
        JP.setOpaque(true); //content panes must be opaque
        JP.setSize(400,400);	
        this.add(JP);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE); 
        
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
              public void run() {
                    test1 frame = new test1();
                    frame.setSize(400,400);
                    frame.setVisible(true);
              }
        });
    }

    public void mouseClicked(MouseEvent e) {
        //drawCircle(e.getX(), e.getY());
        repaint();
        ballball ball;
        ball = new ballball();
        //ball.paintComponent(Graphics g);

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        //this.mouseX=e.getX();
        //this.mouseY=e.getY();
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }    
}

class Panel extends JPanel {
		
        int shit1 = 50;
        int shit2 = 80;
        
        int crap1 = 100;
        int crap2 = 100;

		public void paintComponent(Graphics g) {
	    super.paintComponent(g); 
		System.out.println("paint");
		      
	    g.fillOval(shit1, shit2, crap1, crap2);
		shit1 += 10;
		shit2 += 15;
		
		crap1 -= 2;
		crap2 -= 5;
		}  	
		
		
}


////////////////////////////////////////////////////////////////
class ballball implements Runnable {

    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 100;
    private int squareH = 100;
    public boolean draw;

    private Vector<Object> v = new Vector<Object>();

	public ballball() {
	
	}
	
	public void run() {
	
	}
	
}