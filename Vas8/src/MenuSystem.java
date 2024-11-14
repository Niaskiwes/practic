import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class MenuSystem {
    public static void main(String[] args) {
        String menuDescription = "Первое (a) Второе (b) Третье (c)";
        JFrame frame = new JFrame("Menu Example");
        TNeatMenu menu = new TNeatMenu(menuDescription);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
        menu.showMenu(frame);
    }
}
class TMenu {
    protected String[] menuItems;
    protected char[] returnValues;
    protected int selectedIndex = 0;
    public TMenu(String menuDescription) {
        String[] parts = menuDescription.split(" ");
        menuItems = new String[parts.length];
        returnValues = new char[parts.length];
        for (int i = 0; i < parts.length; i++) {
            menuItems[i] = parts[i];
            // Если есть обозначение буквы в скобках
            if (parts[i].contains("(")) {
                int start = parts[i].indexOf("(");
                int end = parts[i].indexOf(")");
                returnValues[i] = parts[i].charAt(start + 1);
                menuItems[i] = parts[i].substring(0, start).trim();
            } else {
                returnValues[i] = menuItems[i].charAt(0);
            }
        }
    }
    public char select() {
        return returnValues[selectedIndex];
    }
    public void draw(JPanel panel) {
        panel.removeAll();
        for (int i = 0; i < menuItems.length; i++) {
            JLabel label = new JLabel(menuItems[i]);
            if (i == selectedIndex) {
                label.setForeground(Color.RED);
                label.setFont(new Font("Arial", Font.BOLD, 16));
            }
            panel.add(label);
        }
        panel.revalidate();
        panel.repaint();
    }
    public void moveSelection(int delta) {
        selectedIndex = (selectedIndex + delta + menuItems.length) % menuItems.length;
    }
    public char ESC() {
        return (char) 27; // ASCII Escape
    }
    public int whatSel() {
        return selectedIndex;
    }
}
class TNeatMenu extends TMenu {
    private BufferedImage store;

    public TNeatMenu(String menuDescription) {
        super(menuDescription);
    }
    public void init(JFrame frame) {
        store = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = store.getGraphics();
        frame.paint(g);
        g.dispose();
    }
    public void done(JFrame frame) {
        Graphics g = frame.getGraphics();
        g.drawImage(store, 0, 0, null);
        g.dispose();
    }
    public void showMenu(JFrame frame) {
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        draw(panel);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    moveSelection(-1);
                    draw(panel);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    moveSelection(1);
                    draw(panel);
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    char selected = select();
                    JOptionPane.showMessageDialog(frame, "Вы выбрали: " + selected);
                    done(frame); // Возврат к предыдущему виду
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    char esc = ESC();
                    JOptionPane.showMessageDialog(frame, "Отказ от выбора: " + esc);
                    done(frame);
                }
            }
        });
        init(frame);
    }
}
