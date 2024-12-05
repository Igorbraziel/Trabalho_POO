package application.swingInterface;

import javax.swing.*;
import java.awt.*;

public class ListFrame extends JFrame {
    public ListFrame(String title){
        super(title);
        setSize(1400, 1000);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }
}
