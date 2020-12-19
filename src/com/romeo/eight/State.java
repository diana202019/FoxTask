package com.romeo.eight;

import java.awt.geom.Line2D;
import java.util.LinkedList;

public interface State {

    void setBodyPosition(LinkedList<Line2D> bodyShapes);
}
