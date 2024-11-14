import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Line2D;
public class Vaza extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,1000,1000);
        drawHatchedRectangle(g2d, Color.GRAY, 182, 208, 115, 112);
        drawHatchedRectangle(g2d, Color.GRAY, 227, 320, 15, 100);
        drawHatchedOval(g2d, Color.GRAY, 270, 172, 40, 40);
        drawHatchedOval(g2d, Color.GRAY, 280, 165, 40, 40);
        drawHatchedOval(g2d, Color.GRAY, 152, 180, 30, 30);
        drawHatchedOval(g2d, Color.GRAY, 175, 420, 120, 20);
        drawHatchedOval(g2d, Color.GRAY, 173, 220, 20, 100);
        drawHatchedOval(g2d, Color.GRAY, 160, 195, 20, 70);
        drawHatchedOval(g2d, Color.GRAY, 290, 195, 20, 70);
        drawHatchedOval(g2d, Color.GRAY, 290, 190, 30, 30);
        drawHatchedOval(g2d, Color.GRAY, 290, 185, 20, 100);
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawOval(175,420,120,20);
        g2d.drawLine(228,420,228,320);
        g2d.drawLine(242,420,242,320);
        g2d.drawLine(180,320,295,320);
        g2d.drawLine(180,320,150,160);
        g2d.drawLine(295,320,325,160);
        g2d.drawArc(152,110,172,100,180,180);
        g2d.fillOval(165,150,75,75);
        g2d.fillOval(237,117,85,85);
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(3));
        drawHatchedOval(g2d, Color.GRAY, 175, 90, 80, 80);
    }
    private void drawHatchedOval(Graphics2D g2d, Color color, int x, int y, int width, int height) {
        g2d.setPaint(createHatchPattern(color));
        g2d.fillOval(x, y, width, height);
        g2d.setColor(Color.white);
        g2d.drawOval(x, y, width, height);
    }
    private void drawHatchedRectangle(Graphics2D g2d, Color color, int x, int y, int width, int height) {
        g2d.setPaint(createHatchPattern(color));
        g2d.fillRect(x, y, width, height);
        g2d.setColor(Color.white);
        g2d.drawRect(x, y, width, height);
    }
    private TexturePaint createHatchPattern(Color color) {
        int w = 5;
        int h = 5;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, w, h);
        g2.setColor(color);
        for (int i = 0; i <= w; i += 2) {
            g2.draw(new Line2D.Double(i, 0, 0, i));
            g2.draw(new Line2D.Double(0, w - i, i, h));
        }
        g2.dispose();
        return new TexturePaint(img, new Rectangle(0, 0, w, h));
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vaza");
        Vaza panel = new Vaza();
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
