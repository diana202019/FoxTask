package com.romeo.eight;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class State3 implements State {

    private Automata automata;

    public State3(Automata automata) {
        this.automata = automata;
    }

    @Override
    public void setBodyPosition(LinkedList<Line2D> bodyShapes) {
        {Point2D newP7  = bodyShapes.get(7).getP1();
        Point2D newP10 = bodyShapes.get(7).getP2();
        Point2D newP11 = bodyShapes.get(8).getP2();
        Point2D newP8  = bodyShapes.get(9).getP2();
        Point2D newP9  = bodyShapes.get(10).getP2();

        bodyShapes.get(7).setLine(newP7.getX(), newP7.getY(), newP10.getX() + 3, newP10.getY());
        bodyShapes.get(8).setLine(newP10.getX() + 3, newP10.getY(), newP11.getX() + 5, newP11.getY());
        bodyShapes.get(9).setLine(newP7.getX(), newP7.getY(), newP8.getX() - 3, newP8.getY());
        bodyShapes.get(10).setLine(newP8.getX() - 3, newP8.getY(), newP9.getX() - 5, newP9.getY());}

        Point2D newP0  = bodyShapes.get(0).getP1();
        Point2D newP1  = bodyShapes.get(0).getP2();
        Point2D newP2  = bodyShapes.get(2).getP1();
        Point2D newP3  = bodyShapes.get(2).getP2();
        Point2D newP4  = bodyShapes.get(3).getP2();
        Point2D newP5  = bodyShapes.get(5).getP1();
        Point2D newP6  = bodyShapes.get(5).getP2();

        bodyShapes.get(1).setLine(newP1.getX(), newP1.getY(), newP2.getX() + 8, newP2.getY());
        bodyShapes.get(2).setLine(newP2.getX() + 8, newP2.getY(), newP3.getX() + 8, newP3.getY());
        bodyShapes.get(4).setLine(newP4.getX(), newP4.getY(), newP5.getX() - 5, newP5.getY() - 3);
        bodyShapes.get(5).setLine(newP5.getX() - 5, newP5.getY() - 3, newP6.getX() - 4, newP6.getY() - 2);

        automata.setCurrentState(new State4(automata));
    }
}
