package com.romeo.eight;

import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Automata {

    private LinkedList<Line2D> bodyShapes;
    private State  currentState;

    public Automata(LinkedList<Line2D>  bodyShapes) {
        this.bodyShapes = bodyShapes;
        currentState = new State2(this);
    }

    public void setCurrentState(State state) {
        this.currentState = state;
    }

    public void changeBodyPosition() {
        currentState.setBodyPosition(bodyShapes);
    }
}
