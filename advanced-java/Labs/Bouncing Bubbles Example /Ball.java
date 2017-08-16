import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ball extends JApplet implements MouseListener {
   
   final private static int MAX_BALL_COUNT = 20;
   private MyThread blueBall[] = new MyThread[MAX_BALL_COUNT];
   private boolean bouncing = true; 
   int ballcount = 0;


   //init is called by the browser to inform the applet that Ball has been loaded
   public void init()
   {
        addMouseListener( this );

   }


   public void mousePressed( MouseEvent e )
   {
      if ( ballcount < MAX_BALL_COUNT ) {

         blueBall[ballcount] = new MyThread();
         blueBall[ballcount].x = e.getX(); //PB: get the X position
         blueBall[ballcount].y = e.getY(); //PB: get the Y position
         Thread temp = new Thread(blueBall[ballcount]);
         temp.start();
         ballcount++;
      }
   }

   public void paint( Graphics g )
   {
      super.paint( g );

      if ( bouncing ) {
         g.setColor( Color.blue );

         for(MyThread element : blueBall){
			if(element != null)
         	   g.fillOval( element.x, element.y, 10, 10 );
		 }
      }
   }

   public class MyThread implements Runnable
   {
	   public int x = 0, y = 0;
	   private int xDx = 1, yDy = 1;
	   private boolean xUp =false, yUp = false;
	   public boolean running = true;

	   public void run()
	   {
		  while ( running ) {

			 try {
				Thread.sleep( 20 );
			 }
			 // PB: process exception during sleep
			 catch ( Exception e ) {
				System.err.println( "Exception: " + e.toString() );
			 }


			 if ( xUp == true )
				x += xDx;
			 else
				x -= xDx;

			 if ( yUp == true )
				y += yDy;
			 else
				y -= yDy;

			 if ( y <= 0 ) {
				yUp = true;
				yDy = ( int ) ( Math.random() * 5 + 2 );
			}
			 else if ( y >= 190 ) {
				yDy = ( int ) ( Math.random() * 5 + 2 );
				yUp = false;
			 }

			 if ( x <= 0 ) {
				xUp = true;
				xDx = ( int ) ( Math.random() * 5 + 2 );
			 }
			 else if ( x >= 190 ) {
				xUp = false;
				xDx = ( int ) ( Math.random() * 5 + 2 );
			 }

			repaint();
		  }
	   }
	}

   public void mouseExited( MouseEvent e ) {}
   public void mouseClicked( MouseEvent e ) {}
   public void mouseReleased( MouseEvent e ) {}
   public void mouseEntered( MouseEvent e ) {}
}