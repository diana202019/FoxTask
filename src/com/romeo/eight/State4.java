package com.romeo.eight;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class State4 implements State {

    private Automata automata;

    public State4(Automata automata) {
        this.automata = automata;
    }

    @Override
    public void setBodyPosition(LinkedList<Line2D> bodyShapes) {
        {Point2D newP7  = bodyShapes.get(7).getP1();
        Point2D newP10 = bodyShapes.get(7).getP2();
        Point2D newP11 = bodyShapes.get(8).getP2();
        Point2D newP8  = bodyShapes.get(9).getP2();
        Point2D newP9  = bodyShapes.get(10).getP2();

        bodyShapes.get(7).setLine(newP7.getX(), newP7.getY(), newP10.getX() + 5, newP10.getY());
        bodyShapes.get(8).setLine(newP10.getX() + 5, newP10.getY(), newP11.getX() + 7, newP11.getY());
        bodyShapes.get(9).setLine(newP7.getX(), newP7.getY(), newP8.getX() - 5, newP8.getY());
        bodyShapes.get(10).setLine(newP8.getX() - 5, newP8.getY(), newP9.getX() - 7, newP9.getY());}

        Point2D newP0  = bodyShapes.get(0).getP1();
        Point2D newP1  = bodyShapes.get(0).getP2();
        Point2D newP2  = bodyShapes.get(2).getP1();
        Point2D newP3  = bodyShapes.get(2).getP2();
        Point2D newP4  = bodyShapes.get(3).getP2();
        Point2D newP5  = bodyShapes.get(5).getP1();
        Point2D newP6  = bodyShapes.get(5).getP2();

        bodyShapes.get(1).setLine(newP1.getX(), newP1.getY(), newP2.getX(), newP2.getY() + 1);
        bodyShapes.get(2).setLine(newP2.getX(), newP2.getY() + 1, newP3.getX(), newP3.getY() + 1);
        bodyShapes.get(3).setLine(newP0.getX(), newP0.getY(), newP4.getX() - 6, newP4.getY());
        bodyShapes.get(4).setLine(newP4.getX() - 6, newP4.getY(), newP5.getX() - 8, newP5.getY() - 3);
        bodyShapes.get(5).setLine(newP5.getX() - 8, newP5.getY() - 3, newP6.getX() - 8, newP6.getY());

        automata.setCurrentState(new State5(automata));
    }
}
