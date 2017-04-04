import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DiceTester {

    public static void main(String[] args) {
        DiceTester p = new DiceTester();
        p.go();
    }

    public void go() {
        JFrame myWindow = new JFrame("Dice");
        myWindow.setSize(640, 480);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setLayout(new FlowLayout());
        JPanel DPanel = new JPanel();
        DPanel.setPreferredSize(new Dimension(300, 180));
        Dice daDice = new Dice();  
        int rollTotal = daDice.roll();
        DPanel.add(daDice);
        daDice.setPreferredSize(new Dimension(300, 180));
        myWindow.add(DPanel);
        myWindow.add(new JButton("Roll"));
        
        //myWindow.add(daDice);

        myWindow.setVisible(true);
    }

}
