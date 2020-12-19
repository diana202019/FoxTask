package com.romeo.eight;

import java.awt.geom.Line2D;
import java.util.LinkedList;

public class State1 implements State {

    private Automata automata;

    public State1(Automata automata) {
        this.automata = automata;
    }

    @Override
    public void setBodyPosition(LinkedList<Line2D> bodyShapes) {
        bodyShapes.get(0).setLine(0, 0, 10, 35);  bodyShapes.get(1).setLine(10, 35, 1, 70);
        bodyShapes.get(2).setLine(1, 70, 8, 70);  bodyShapes.get(3).setLine(0, 0, 2, 35);
        bodyShapes.get(4).setLine(2, 35, -8, 70);  bodyShapes.get(5).setLine(-8, 70, -1, 70);
        bodyShapes.get(6).setLine(0, 0, 0, -50);  bodyShapes.get(7).setLine(0, -50, -5, -20);
        bodyShapes.get(8).setLine(-5, -20, 11, 10);  bodyShapes.get(9).setLine(0, -50, -12, -20);
        bodyShapes.get(10).setLine(-12, -20, 5, 10); bodyShapes.get(11).setLine(0, -50, 0, -60);
        bodyShapes.get(12).setLine(0, -60, -15, -80); bodyShapes.get(13).setLine(-15, -80, 0, -95);
        bodyShapes.get(14).setLine(0, -95, 15, -80); bodyShapes.get(15).setLine(15, -80, 0, -60);

        automata.setCurrentState(new State2(automata));
    }
}
