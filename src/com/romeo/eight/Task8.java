package com.romeo.eight;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class Task8 extends JFrame implements Homosapien.Trajectory {

    private static final double epsilon = 0.5;
    private static final Point2D start = new Point2D.Double(100, 300);

    public Task8() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setSize(980,560);
        panel.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public LinkedList<Line2D> getLines(LinkedList<Point2D> points) {
        LinkedList<Line2D> lines = new LinkedList<>();
        int range = points.size() - 1;

        for (int i = 0; i < range; i++) {
            lines.add(new Line2D.Double(points.get(i), points.get(i + 1)));
        }

        return lines;
    }

    public void paint(Graphics g) {
        super.paint(g);

        Homosapien man = new Homosapien(this, 1000);
        man.shift(new Point2D.Double(200, 300));

        man.paint(g);

    }

    @Override
    public Point2D formula(int i) {
        return new Point2D.Double(
                start.getX() + epsilon * i,
                start.getY() + 1.5 * Math.sin(start.getX() + epsilon / 7 * i));
    }

    public static void main(String[] args) {
        new Task8();
    }

}
