/*
    Devontae Reid
    CIS 282
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class BubblePanel extends JPanel implements Runnable {

	private ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    private int outerID = 0;
	
	public BubblePanel() {
        setBackground(new Color(54,81,94));
        checkIfBubbles();
        addBallsWithClick();
	}
    
    public void addBallsWithClick() {
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e) {
                bubbles.add(new Bubble(randomColor(),e.getX(),e.getY(),60,outerID,bubbles));
                outerID++;
            }
        });
    }
    
    private void checkIfBubbles() {
        
        JLabel label = new JLabel("Press anywhere to add Bubbles");
        label.setFont(new Font("ZapfDingbats", Font.PLAIN, 18));
        label.setBounds(0,0,getWidth(),getHeight());
        add(label);
        
        if (bubbles.isEmpty()) {
            label.setVisible(true);
        } else {
            label.setVisible(false);
        }
    }
    
    public void run() {
        while (true) {
            try {
                for(Bubble bubble: bubbles) {
                    bubble.move();
                    bubble.collide();
                }
                repaint();
                Thread.sleep(5);
            } catch (Exception e) {
                System.out.println("Thread woke up from sleep prematurely");
            }
        }
    }
    
    protected Color randomColor() {
        int red = (int) Math.abs(Math.random() * 255);
        int green = (int) Math.abs(Math.random() * 255);
        int blue = (int) Math.abs(Math.random() * 255);
        
        return new Color(red, green, blue);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        for(Bubble bubble: bubbles) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g.setColor(bubble.getColor());
            g2d.fillOval((int)bubble.getX(),(int)bubble.getY(),60,60);
        }
        
    }
    
    /*----------BUBBLE_CLASS----------*/
    
    public class Bubble {
        
        private double x,y;
        private double deltaX = 1;
        private double deltaY = -1;
        private Color color;
        private ArrayList<Bubble> others;
        private double gravity = 0.03;
        private double friction = -0.9;
        private double spring = 0.04;
        private double diameter;
        private int id;
        
        public Bubble(Color color,double x,double y,double dim,int id,ArrayList<Bubble> othBubs) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.diameter = dim;
            this.id = id;
            this.others = othBubs;
        }
        
        public void setX(double x) {
            this.x = x;
        }
        
        public double getX() {
            return this.x;
        }
        
        public void setY(double y) {
            this.y = y;
        }
        
        public double getY() {
            return this.y;
        }
        
        public void setColor(Color c) {
            this.color = c;
        }
        
        public Color getColor() {
            return this.color;
        }
        
        public void move() {
            if (x + diameter > getWidth()) {
                x = getWidth() - diameter;
                deltaX *= friction;
            }
            else if (x - diameter/16 < 0) {
                x = diameter/16;
                deltaX *= friction;
            }
            if (y + diameter > getHeight()) {
                y = getHeight() - diameter;
                deltaY *= friction;
            } 
            else if (y - diameter/16 < 0) {
                y = diameter/16;
                deltaY *= friction;
            }
            deltaY += gravity;
            x += deltaX;
            y += deltaY;
        }
        
        void collide() {
            for (int i = id; i < others.size(); i++) {
                double dx = others.get(i).x - x;
                double dy = others.get(i).y - y;
                double distance = Math.sqrt(dx*dx + dy*dy);
                double minDist = others.get(i).diameter/2 + diameter/2;
                if (distance < minDist) {
                    double angle = Math.atan2(dy, dx);
                    double targetX = x + Math.cos(angle) * minDist;
                    double targetY = y + Math.sin(angle) * minDist;
                    double ax = (targetX - others.get(i).x) * spring;
                    double ay = (targetY - others.get(i).y) * spring;
                    deltaX -= ax;
                    deltaY -= ay;
                    others.get(i).deltaX += ax;
                    others.get(i).deltaY += ay;
                }
            }   
        }
    }
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Bouncing Bubbles");
		
		BubblePanel bp = new BubblePanel();
		new Thread(bp).start();
		frame.add(bp);
		
		frame.setSize(400,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}