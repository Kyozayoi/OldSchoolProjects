package frca.boardgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author Anthony Shih
 */
public class Dice extends JComponent implements Runnable {

    public int die1;
    public int die2;
    public int total;
    private GameBoard board;
    private Thread diceThread;
    private int playerIndex;
    private boolean rolling = true;

    public Dice(GameBoard board) {
        this.board = board;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int w = this.getWidth();
        int h = this.getHeight();

        g2d.setColor(Color.white);
        g2d.fillRoundRect(w / 10, h / 3, w / 3, h / 3, 15, 15);
        g2d.fillRoundRect(6 * w / 10, h / 3, w / 3, h / 3, 15, 15);

        g2d.setColor(Color.black);
        g2d.drawRoundRect(w / 10, h / 3, w / 3, h / 3, 15, 15);
        g2d.drawRoundRect(6 * w / 10, h / 3, w / 3, h / 3, 15, 15);

        switch (die1) {
            case (1):
                g2d.fillOval(w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
            case (2):
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (3):
                g2d.fillOval(w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (4):
                g2d.fillOval(w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (5):
                g2d.fillOval(w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
            case (6):
                g2d.fillOval(w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
        }
        switch (die2) {
            case (1):
                g2d.fillOval(6 * w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
            case (2):
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (3):
                g2d.fillOval(6 * w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (4):
                g2d.fillOval(6 * w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (5):
                g2d.fillOval(6 * w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
            case (6):
                g2d.fillOval(6 * w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
        }
    }

    public Thread getThread() {
        return diceThread;
    }

    public boolean isRoling() {
        return rolling;
    }

    @Override
    public void run() 
    {
        int c = 0;
        while(rolling)
        {
            die1 = (int) (1 + Math.random() * 6);
            die2 = (int) (1 + Math.random() * 6);
            this.painting();
            try {
                Thread.sleep(60);
            } catch (InterruptedException ex) {
                System.out.println("Dice have stopped rolling");
            }
            c++;
            if(c == 10){
                rolling = false;
            }
        }
        total = die1 + die2;
        Object[] data = {(Object) total, (Object) playerIndex};
        board.update(data);    
        diceThread.interrupt();
    }

    public void stop() {
        if (diceThread != null) {
            diceThread.interrupt();
        }
    }

    public void roll(int playerNumber) {
        playerIndex = playerNumber;
        rolling = true;
        diceThread = new Thread(this);
        diceThread.setPriority(Thread.MAX_PRIORITY);
        diceThread.start();
    }
    
    public void painting(){
        Graphics2D g2d = (Graphics2D)this.getGraphics();
        int w = this.getWidth();
        int h = this.getHeight();

        g2d.setColor(Color.white);
        g2d.fillRoundRect(w / 10, h / 3, w / 3, h / 3, 15, 15);
        g2d.fillRoundRect(6 * w / 10, h / 3, w / 3, h / 3, 15, 15);

        g2d.setColor(Color.black);
        g2d.drawRoundRect(w / 10, h / 3, w / 3, h / 3, 15, 15);
        g2d.drawRoundRect(6 * w / 10, h / 3, w / 3, h / 3, 15, 15);

        switch (die1) {
            case (1):
                g2d.fillOval(w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
            case (2):
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (3):
                g2d.fillOval(w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (4):
                g2d.fillOval(w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (5):
                g2d.fillOval(w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
            case (6):
                g2d.fillOval(w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10, h / 3 + h / 9, w / 9, h / 9);
                g2d.fillOval(w / 10 + 2 * w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
        }
        switch (die2) {
            case (1):
                g2d.fillOval(6 * w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
            case (2):
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (3):
                g2d.fillOval(6 * w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (4):
                g2d.fillOval(6 * w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                break;
            case (5):
                g2d.fillOval(6 * w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
            case (6):
                g2d.fillOval(6 * w / 10, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3 + 2 * h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10, h / 3 + h / 9, w / 9, h / 9);
                g2d.fillOval(6 * w / 10 + 2 * w / 9, h / 3 + h / 9, w / 9, h / 9);
                break;
        }
    }
}
