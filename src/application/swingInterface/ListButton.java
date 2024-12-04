package application.swingInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;

public class ListButton extends JButton {
    public ListButton(String text){
        super(text);
        setBackground(new Color(129, 124, 124));
        setForeground(new Color(0, 0, 0));
        setFont(new Font("Arial", Font.BOLD, 18));
        setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED, new Color(3, 6, 27), new Color(21, 44, 38)));
    }
}
