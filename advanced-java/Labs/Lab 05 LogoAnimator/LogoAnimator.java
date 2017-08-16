// Fig. 21.4: LogoAnimatorJPanel.java
// Animating a series of images.
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

class LogoAnimatorJPanel extends JPanel
{

	private final String deitel = "deitel";
   	protected ImageIcon images[];
   	private int currentImage = 0;
   	private int animationDelay;
   	private int width;
   	private int height;

   	private Timer animationTimer; // Timer drives animation

   	// constructor initializes LogoAnimatorJPanel by loading images
   	LogoAnimatorJPanel() {
    	try {
			// create array to store ImageIcon references
			images = new ImageIcon[30]; 

			// load the selected images
			for ( int count = 0; count < images.length; count++ ){
				images[count] = new ImageIcon(getClass().getResource(String.format("images/%s%02d.gif",deitel,count)));
			} // end for
	
			setAnimation(500);
			width = images[ 0 ].getIconWidth();
			height = images[ 0 ].getIconHeight();
      } // end try
      catch( Exception e ){
         e.printStackTrace();
      } // end catch
   } // end LogoAnimatorJPanel constructor

   // display current image 
   public void paintComponent( Graphics g ){
      super.paintComponent( g );

      images[ currentImage ].paintIcon( this, g, 0, 0 );
      
      // set next image to be drawn only if Timer is running
      if (animationTimer.isRunning()){
         currentImage = ( currentImage + 1 ) % images.length;
         }
   } // end method paintComponent
    
    public void setAnimation(int anim) {
        this.animationDelay = anim;
    }
    
    public int getAnimation() {
        return this.animationDelay;
    }

   // start animation
   public void startAnimation(){
		currentImage = 0;
       
		animationTimer = 
		new Timer( getAnimation(), new TimerHandler() );
        animationTimer.setCoalesce(false);

		animationTimer.start(); // start Timer
   } // end method startAnimation

   // stop animation Timer
   public void stopAnimation(){
      	animationTimer.stop();
   } // end method stopAnimation

   // return minimum size of animation
   public Dimension getMinimumSize(){ 
      	return getPreferredSize(); 
   } // end method getMinimumSize

   // return preferred size of animation
   public Dimension getPreferredSize(){
      	return new Dimension( width, height );
   } // end method getPreferredSize

   // inner class to handle action events from Timer
   private class TimerHandler implements ActionListener {
      // respond to Timer's event
      public void actionPerformed( ActionEvent actionEvent ){
         repaint();
      } // end method actionPerformed
   } // end class TimerHandler
    
}

public class LogoAnimator extends JFrame {
    
    private JButton startButton,stopButton;
    private JSlider slider;
    private LogoAnimatorJPanel animation;
    
    public LogoAnimator() {
        setTitle("Deitel Animator");
        setLayout(new FlowLayout());
        setSize(200,200);
       	setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo(null);
        
        animation = new LogoAnimatorJPanel();
       	animation.setBounds(0, 60, 160, 100);
       	add(animation);
        
       	startButton = new JButton("Start");
       	startButton.setBounds(10, 5, 64, 30);
       	add(startButton);
        
       	stopButton = new JButton("Stop");
        stopButton.setBounds(90, 5, 64, 30);
        add(stopButton);
        
       	slider = new JSlider(JSlider.HORIZONTAL,0,50,1);
       	slider.setBounds(8, 30, 150, 30);
       	slider.setMajorTickSpacing(10);
       	slider.setPaintTicks(true);
       	add(slider);
       	
        
        animation.startAnimation();
        
       	// Actions
       	startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                animation.startAnimation();
            }
       	});
        
       	stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                animation.stopAnimation();
            }
       	});
        
       	slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                animation.stopAnimation();
                double dValue = 50/(double)slider.getValue();
                int iValue = 10 * (int)dValue;
                animation.setAnimation(iValue);
                animation.startAnimation();
                
                
                if (slider.getValue() == 0) {
                    animation.stopAnimation();
                    startButton.setEnabled(false);
                    stopButton.setEnabled(false);
                } else {
                    startButton.setEnabled(true);
                    stopButton.setEnabled(true);
                }
            }
       	});
        
       	setVisible( true );
    }
    
    public static void main(String[] args) {
        
        new LogoAnimator();
    }
}
