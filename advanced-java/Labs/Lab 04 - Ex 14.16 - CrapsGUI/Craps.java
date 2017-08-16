enum Status { WON,LOST,CONTINUE };

class Craps {
    
    
    final static int SNAKE_EYES = 2;
    final static int TREY = 3;
    final static int SEVEN = 7;
    final static int ELEVEN = 11;
    final static int BOX_CARS = 12;
    
    private int sumOfDice, point;
    private static int dice1;
    private static int dice2;
    
    
    Craps() {
        point = 0;
        sumOfDice = 0;
    }
    
    public void resetPoint() {
        this.point = 0;
    }
    
    public void resetSumOfDice() {
        this.sumOfDice = 0;
    }
    
    public int getPoint() {
        return this.point;
    }
    
    public int getSumOfDice() {
        return sumOfDice;
    }
    
    public int getDice1() {
        return dice1;
    }
    
    public int getDice2() {
        return dice2;
    }
    
    public Status play() {
        Status gameStatus = Status.CONTINUE;
        
        if (point == 0) {
            sumOfDice = roll();
            
            switch (sumOfDice) {
                case SEVEN:
                case ELEVEN:
                    gameStatus = Status.WON;
                    break;
                case SNAKE_EYES:
                case TREY:
                case BOX_CARS:
                    gameStatus = Status.LOST;
                    break;
                default:
                    gameStatus = Status.CONTINUE;
                    point = sumOfDice;
            }// End switch
        } else {
            sumOfDice = roll();
            if (sumOfDice == SEVEN) {
                gameStatus = Status.LOST;
            } else if (sumOfDice == point) {
                gameStatus = Status.WON;
            }
        }// End else
        return gameStatus;
    }
    
    public static int roll(){
        dice1 = 1 + (int)(6.0*Math.random());
        dice2 = 1 + (int)(6.0*Math.random());
        return (dice1 + dice2);
    }
    
}