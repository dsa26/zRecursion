import java.awt.*;
import javax.swing.*;

public class MyGraphics extends Canvas {
    public void paint(Graphics g) {
        super.paint(g);

//        for(int x = 0; x < 650; x++) {
//            for(int y = 0; y < 650; y++) {
                calculatePoints(g, 650 * 0.5, 650 * 0.5, 250, 1);
//            }
//        }
    }

    public static void calculatePoints(Graphics g, double x, double y, double w, int it) {
        int xd = (int) Math.round(x - w * 0.5);
        int yd = (int) Math.round(y - w * 0.5);
        int wd = (int) Math.round(w);
        if (it < 5) {
            calculatePoints(g, x + w * 0.45, y + w * 0.45, w * 0.4, it + 1);
            calculatePoints(g, x - w * 0.45, y + w * 0.45, w * 0.4, it + 1);
            calculatePoints(g, x + w * 0.45, y - w * 0.45, w * 0.4, it + 1);
            calculatePoints(g, x - w * 0.45, y - w * 0.45, w * 0.4, it + 1);
        }
        g.setColor(new Color(255, 0, 0));
        g.fillOval(xd, yd, wd, wd);
        g.setColor(new Color(0, 255, 0));
        g.fillOval(xd + 2, yd + 2, wd - 4, wd - 4);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MyGraphics());
        frame.setSize(650, 650);
        frame.setVisible(true);
    }
}