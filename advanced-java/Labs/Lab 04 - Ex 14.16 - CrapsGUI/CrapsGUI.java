/*
 Devontae Reid
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.IOException;



/*
	The main method is in here
*/
public class CrapsGUI extends JFrame {
    
    final static double MIN_BALANCE = 50.00;
    final static double MIN_BET = 1.00;
    
    private Craps craps = new Craps();
    private BalanceBetFrame bbf = new BalanceBetFrame();
    private GamePanel gp = new GamePanel();
    
    private JPanel gamePanel;
    private JLabel retryLabel;
    
    
    public static void main(String[] args) {
        new CrapsGUI();
    }
    
    public CrapsGUI() {
        super("Craps");
        getContentPane().setLayout(null);
      
        setupGame();
        
        /*--JFrame_Settings--*/
        setSize(400,400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


	private void setupGame() {
        
        Color crapsGreen = new Color(44,103,0);
        
        /*--Main_Panel_Setup--*/
        gamePanel = new JPanel();
        gamePanel.setBackground(crapsGreen);
        gamePanel.setBounds(0, 0, 400, 378);
        gamePanel.setVisible(true);
        gamePanel.setLayout(null);
        getContentPane().add(gamePanel);
        
        gp.setBounds(0,150,400,230);
        gp.setBackground(crapsGreen);
        gamePanel.add(gp);
		
        /*--Icon_For_Dice--*/
        ImageIcon diceIcon = new ImageIcon(getClass().getResource("dice-copy.png"));
        JLabel crapsIcon = new JLabel(diceIcon);
        crapsIcon.setBackground(Color.blue);
        crapsIcon.setBounds(60,30,100,61);
        crapsIcon.setVisible(true);
        gamePanel.add(crapsIcon);
        
        retryLabel = new JLabel(String.format("Press Roll to Play Again or Quit"));
        retryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        retryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        retryLabel.setBounds(0,100,210,29);
        retryLabel.setVisible(false);
        gamePanel.add(retryLabel);

	
		// Add Balance Listener
        gp.playButtonAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (bbf.getBet() > MIN_BET  && bbf.getBalance() > MIN_BALANCE) {
            		outcomeResults(craps.play());
                } else {
                	gp.updateLabels("Your balance or wager is too low!","","");
                }
            }
        });
        
        bbf.setBounds(220,0,180,140);
        gamePanel.add(bbf);
        
	}
    
    
    private void outcomeResults(Status gameStatus) {
        
        String outcome,point,roll;
        
        // Status of game
        if ( gameStatus == Status.WON ) {
        
        	// Enable slider to increase wager & show quit button
        	bbf.gameInProgress(false);
        	gp.hideQuitButton(false);
        	
        	// Update Balance and bet after win
            bbf.setBalance(bbf.getBalance() + bbf.getBet());
            
            // Update point, outcome , and roll labels
            outcome = String.format("You won!");
            point = String.format("Point : %d",craps.getPoint());
            roll = String.format("You Rolled : %d",craps.getSumOfDice());
            gp.updateLabels(outcome,point,roll);
            
            // Reset Point and Dice
            craps.resetPoint();
            craps.resetSumOfDice();
            bbf.setBet(1.00);
            bbf.updateLabel();
            
            retryLabel.setVisible(true);
        } else if (gameStatus == Status.CONTINUE) {
        	// Disable slider while playing game & hide quit button
        	bbf.gameInProgress(true);
        	gp.hideQuitButton(true);
        	
        	// Update point, outcome , and roll labels
            outcome = String.format("Continue to Roll");
            point = String.format("Point : %d",craps.getPoint());
            roll = String.format("You Rolled : %d",craps.getSumOfDice());
            gp.updateLabels(outcome,point,roll);
            retryLabel.setVisible(false);
        } else {
        	// Enable slider to increase wager & show quit button
        	bbf.gameInProgress(false);
            gp.hideQuitButton(false);
            
            // Update Balance and bet after win
            bbf.setBalance(bbf.getBalance() - bbf.getBet());
            
            // Update point, outcome , and roll labels
            outcome = String.format("Sorry, you've lost!");
            point = String.format("Point : %d",craps.getPoint());
            roll = String.format("You Rolled : %d",craps.getSumOfDice());
            gp.updateLabels(outcome,point,roll);
            
            // Reset Point and Dice
            craps.resetPoint();
            craps.resetSumOfDice();
            bbf.setBet(1.00);
            bbf.updateLabel();
            retryLabel.setVisible(true);
        }
        
        // Show image for the dice number that is called
        gp.setDiceIcon1(getDiceImage(craps.getDice1()));
        gp.setDiceIcon2(getDiceImage(craps.getDice2()));
        gp.hideDicePanels(false);
        
    }
    
    // Method that gets the image for the number needed for the dice
    public ImageIcon getDiceImage(int dice) {
        
        String filename;
        
        switch(dice) {
            case 1:
                filename = "one.png";
                break;
            case 2:
                filename = "two.png";
                break;
            case 3:
                filename = "three.png";
                break;
            case 4:
                filename = "four.png";
                break;
            case 5:
                filename = "five.png";
                break;
            case 6:
                filename = "six.png";
                break;
            default:
                filename = "";
                break;
        }// end switch
        
        ImageIcon diceImage = new ImageIcon(getClass().getResource(String.format("/Dice Images/%s",filename)));
        
        
        return diceImage;
    }
}
