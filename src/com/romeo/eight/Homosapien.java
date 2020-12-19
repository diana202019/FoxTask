package com.romeo.eight;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class Homosapien {

    public interface Trajectory {
        Point2D formula(int iteration);
    }

    private Trajectory task;
    private LinkedList<Point2D> trajectory;
    private ArrayList<Point2D.Double> body;
    private LinkedList<Line2D>  bodyShapes;
    private Automata automata;

    private ArrayList<LinkedList<Line2D>> finalCoordinates;

    public Homosapien(Trajectory formula, int distance) {
        task = formula;
        trajectory = new LinkedList<>();
        bodyShapes = new LinkedList<>();
        body = initBody();
        automata = new Automata(bodyShapes);
        for (int i = 0; i < distance; i++) {
            trajectory.add(task.formula(i));
        }
        finalCoordinates = new ArrayList<>(trajectory.size());
        for (Point2D point2D : trajectory) {
            // automata call
            automata.changeBodyPosition();
            finalCoordinates.add(shift((Point2D.Double) point2D));

        }
    }

    public LinkedList<Line2D> shift(Point2D.Double point) {
        LinkedList<Line2D> newBody = new LinkedList<>();
        for (Line2D p : bodyShapes) {
            double x1 = p.getX1() + point.getX();
            double y1 = p.getY1() + point.getY();
            double x2 = p.getX2() + point.getX();
            double y2 = p.getY2() + point.getY();
            newBody.add(new Line2D.Double(x1, y1, x2, y2));
        }
        return newBody;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < finalCoordinates.size(); i++) {
            for (Line2D line : finalCoordinates.get(i)) {
                ((Graphics2D) g).draw(line);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            g.setColor(Color.white);

            for (Line2D line : finalCoordinates.get(i)) {
                ((Graphics2D) g).draw(line);
            }

            g.setColor(Color.gray);
        }
    }

    private ArrayList<Point2D.Double> initBody() {
        // legs
        Point2D.Double p0 = new Point2D.Double(0, 0);
        Point2D.Double p1 = new Point2D.Double(10, 35);
        Point2D.Double p2 = new Point2D.Double(1, 70);
        Point2D.Double p3 = new Point2D.Double(8, 70);
        Point2D.Double p4 = new Point2D.Double(2, 35);
        Point2D.Double p5 = new Point2D.Double(-8, 70);
        Point2D.Double p6 = new Point2D.Double(-1, 70);

        // main body
        Point2D.Double p7 = new Point2D.Double(0, -50);
        Point2D.Double p8 = new Point2D.Double(-12, -20);
        Point2D.Double p9 = new Point2D.Double(5, 10);
        Point2D.Double p10 = new Point2D.Double(-5, -20);
        Point2D.Double p11 = new Point2D.Double(11, 10);
        Point2D.Double p12 = new Point2D.Double(0, -60);
        Point2D.Double p13 = new Point2D.Double(-15, -80);
        Point2D.Double p14 = new Point2D.Double(0, -95);
        Point2D.Double p15 = new Point2D.Double(15, -80);

        ArrayList<Point2D.Double> list = new ArrayList<>(16);
        list.add(p0); list.add(p1); list.add(p2); list.add(p3);
        list.add(p4); list.add(p5); list.add(p6); list.add(p7);
        list.add(p8); list.add(p9); list.add(p10); list.add(p11);
        list.add(p12); list.add(p13); list.add(p14); list.add(p15);

        // legs
        bodyShapes.add(new Line2D.Double(p0,  p1)); // 0 +
        bodyShapes.add(new Line2D.Double(p1,  p2)); // 1 +
        bodyShapes.add(new Line2D.Double(p2,  p3)); // 2 +
        bodyShapes.add(new Line2D.Double(p0,  p4)); // 3 +
        bodyShapes.add(new Line2D.Double(p4,  p5)); // 4 +
        bodyShapes.add(new Line2D.Double(p5,  p6)); // 5 +

        // main body
        bodyShapes.add(new Line2D.Double(p0,  p7)); // 6 +
        bodyShapes.add(new Line2D.Double(p7,  p10));  // left hand 7 +
        bodyShapes.add(new Line2D.Double(p10,  p11)); // left hand 8 +

        bodyShapes.add(new Line2D.Double(p7,  p8));  // right hand 9 +
        bodyShapes.add(new Line2D.Double(p8,  p9));  // right hand 10 +

        // head
        bodyShapes.add(new Line2D.Double(p7, p12));  // 11 +
        bodyShapes.add(new Line2D.Double(p12, p13)); // 12 +
        bodyShapes.add(new Line2D.Double(p13, p14)); // 13 +
        bodyShapes.add(new Line2D.Double(p14, p15)); // 14 +
        bodyShapes.add(new Line2D.Double(p15, p12)); // 15 +

        return list;
    }


}
