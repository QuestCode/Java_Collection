import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class BalanceBetFrame extends JPanel {
    final static double MIN_BALANCE = 50.00;
    final static double MIN_BET = 1.00;
    
    private double balance,bet;
    
    private boolean ready;
    
    private JLabel balanceLabel;
    private JLabel betLabel;
    private JLabel sliderTitle;
    private JSlider slider;
    private JButton addBalanceButton;
    private JTextField textfield;
    
    BalanceBetFrame() {
        setLayout(new GridBagLayout());
        this.setBackground(new Color(229,228,215));
        
        balance = 0.00;
        bet = 0.00;
        ready = false;
        
        balanceLabel = new JLabel(String.format("Balance : $%.2f",balance));
        balanceLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        betLabel = new JLabel(String.format("Bet : $%.2f",bet));
        betLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        sliderTitle = new JLabel("Increase or Decrease Bet");
        sliderTitle.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        
        slider = new JSlider(JSlider.HORIZONTAL,1,50,1);
        slider.addChangeListener(
                                 new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                bet = (double)slider.getValue();
                slider.setMaximum((int)balance);
                betLabel.setText(String.format("Bet : $%,.2f",bet));
                
                if(balance > 1.00 && bet > 1.00) {
                    ready = true;
                } else {
                    ready = false;
                }
            }
        });
        
        Color greenish = new Color(146,205,0);
        Color yellowish = new Color(255,207,121);
        
        addBalanceButton = new JButton("Add Balance");
        addBalanceButton.setBackground(yellowish);
        addBalanceButton.setForeground(greenish);
        addBalanceButton.setPreferredSize(new Dimension(130, 20));
        addBalanceButton.setEnabled(false);
        addBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                balance = balance + Double.parseDouble(textfield.getText());
                
                if (balance > 1.00) {
                    bet = 1.00;
                } else {
                	balance = balance * -1;
                }
                
                balanceLabel.setText(String.format("Balance : $%,.2f",balance));
                textfield.setText("");
            }
        });
        
        textfield = new JTextField(6);
        textfield.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
            public void removeUpdate(DocumentEvent e) {
                changed();
            }
            public void insertUpdate(DocumentEvent e) {
                changed();
            }
            
            public void changed() {
                if (textfield.getText().equals("")){
                    addBalanceButton.setEnabled(false);
                }
                else {
                    addBalanceButton.setEnabled(true);
                }
                
            }
        });
        
        // Constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(balanceLabel,gbc);
        gbc.gridy++;
        this.add(betLabel,gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy++;
        this.add(textfield,gbc);
        gbc.gridy++;
        this.add(addBalanceButton,gbc);
        gbc.gridy++;
        this.add(sliderTitle,gbc);
        gbc.gridy++;
        this.add(slider,gbc);
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void setBet(double bet) {
        this.bet = bet;
    }
    
    public void resetBet() {
        this.bet = MIN_BET;
    }
    
    public double getBalance() {
        return this.balance;
    }
    
    public double getBet() {
        return this.bet;
    }
    
    public boolean isReady() {
        return this.ready;
    }
    
    public void gameInProgress(boolean game) {
    	slider.setEnabled(!game);
    }
    
    public void updateLabel() {
        betLabel.setText(String.format("Bet : $%,.2f",getBet()));
        slider.setValue((int)getBet());
        balanceLabel.setText(String.format("Balance : $%,.2f",getBalance()));
    }
}