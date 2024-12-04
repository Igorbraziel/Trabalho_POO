package application.swingInterface;

import javax.swing.*;
import java.awt.*;

public class ListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (renderer instanceof JLabel) {
            JLabel label = (JLabel) renderer;
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        }

        return renderer;
    }
}
