import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class GamePanel extends JPanel {
    private JLabel outcomeLabel;
    private JLabel pointLabel;
    private JLabel rollLabel;
    private JLabel dice1Panel;
    private JLabel dice2Panel;
    
    private JButton rollButton;
    private JButton quitButton;
    
    
    GamePanel() {
        setLayout(null);
        rollButton = new JButton("Roll");
        quitButton = new JButton("Quit");
        
        outcomeLabel = new JLabel();
        outcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outcomeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        outcomeLabel.setBounds(0, 0, 390, 29);
        this.add(outcomeLabel);
        
        dice1Panel = new JLabel();
        dice1Panel.setBounds(100, 85, 64, 64);
        this.add(dice1Panel);
        
        dice2Panel = new JLabel();
        dice2Panel.setBounds(200, 85, 64, 64);
        this.add(dice2Panel);
        
        pointLabel = new JLabel();
        pointLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pointLabel.setBounds(299, 110, 95, 16);
        this.add(pointLabel);
        
        rollLabel = new JLabel();
        rollLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rollLabel.setBounds(299, 130, 95, 16);
        this.add(rollLabel);
        
        
        Color greenish = new Color(146,205,0);
        
        quitButton.setBounds(6, 190, 117, 29);
        quitButton.setForeground(greenish);
        this.add(quitButton);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        rollButton.setBounds(277, 190, 117, 29);
        rollButton.setForeground(greenish);
        this.add(rollButton);
        
    }
    
    public void setDiceIcon1(ImageIcon icon) {
        dice1Panel.setIcon(icon);
    }
    
    public void setDiceIcon2(ImageIcon icon) {
        dice2Panel.setIcon(icon);
    }
    
    public void updateLabels(String outcome,String point,String roll) {
        outcomeLabel.setText(outcome);
        pointLabel.setText(point);
        rollLabel.setText(roll);
    }
    
    public void hideDicePanels(boolean hide) {
        if (hide) {
            dice1Panel.setVisible(false);
            dice2Panel.setVisible(false);
        } else {
            dice1Panel.setVisible(true);
            dice2Panel.setVisible(true);
        }
      
    }
    
    public void hideQuitButton(boolean hide) {
    	quitButton.setVisible(!hide);
    }
    
    public void playButtonAction(ActionListener al) {
        rollButton.addActionListener(al);
    }
}
