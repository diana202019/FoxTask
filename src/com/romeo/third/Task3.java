package com.romeo.third;

import javax.swing.*;
import java.awt.*;

public class Task3 extends JFrame {

    public Task3() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setSize(1080,560);

    }

    public void paint(Graphics g) {
        super.paint(g);

        Cube3D cube = new Cube3D(
                new Point3D(0,0,0),
                new Point3D(100, 0, 0),
                new Point3D(100, 100, 0),
                new Point3D(0, 100,0),

                new Point3D(100, 0, 50),
                new Point3D(0,0,100),
                new Point3D(50, 0, 100),
                new Point3D(100, 50, 100),

                new Point3D(100, 100, 100),
                new Point3D(0, 100, 100)
        );

        Cube3D old = new Cube3D(
                new Point3D(0 + 100,0+ 100,0+ 100),
                new Point3D(100+ 100, 0+ 100, 0+ 100),
                new Point3D(100+ 100, 100+ 100, 0+ 100),
                new Point3D(0+ 100, 100+ 100,0+ 100),

                new Point3D(100 + 100, 0+ 100, 50+ 100),
                new Point3D(0+ 100,0+ 100,100+ 100),
                new Point3D(50+ 100, 0+ 100, 100+ 100),
                new Point3D(100+ 100, 50+ 100, 100+ 100),

                new Point3D(100+ 100, 100+ 100, 100+ 100),
                new Point3D(0+ 100, 100+ 100, 100+ 100)
        );

        Cube3D rotated = old.rotate(Math.toRadians(10), Math.toRadians(10), Math.toRadians(10));
        Cube3D dimetric = rotated.axonometric(Math.toRadians(19.5), Math.toRadians(20.7));
        Cube3D cabinet  = cube.
                rotate(Math.toRadians(15), Math.toRadians(5), Math.toRadians(15)).
                cabinet(Math.toRadians(30), Math.toRadians(60)).
                shift(new Point3D(100, 100, 0));
        Cube3D trimetric = rotated
                .axonometric(Math.toRadians(15), Math.toRadians(30))
                .shift(new Point3D(150, 100, 0));
        Cube3D onePointPers = cube
                .perspective(new Point3D(35, 70, 250))
                .shift(new Point3D(50, 50, 0));
        Cube3D twoPointPers = cube
                .perspective(new Point3D(35, 70, 150), new Point3D(200, 200, 200))
                .shift(new Point3D(50, 50, 0));

        Cube3D shiftedRotated1 = rotated.shift(new Point3D(150, 0, 0));
        Cube3D shiftedRotated2 = rotated.shift(new Point3D(700, 500, 0));

        ProjectedCube projectedCube = new ProjectedCube(trimetric.projectToXYPlane());
//        projectedCube.paint(g);

        ProjectedCube projectedToXYPlane = new ProjectedCube(shiftedRotated1.projectToXYPlane());
        g.setColor(Color.BLUE);
//        projectedToXYPlane.paint(g);

        ProjectedCube projectedToYZPlane = new ProjectedCube(shiftedRotated2.projectToYZPlane());
        g.setColor(Color.RED);
//        projectedToYZPlane.paint(g);

        ProjectedCube projectedToXZPlane = new ProjectedCube(shiftedRotated2.projectToXZPlane());
        g.setColor(Color.GREEN);
//        projectedToXZPlane.paint(g);

//        -
//        ProjectedCube dimetricProjection = new ProjectedCube(dimetric.project());
//        g.setColor(Color.gray);
//        dimetricProjection.paint(g);

        ProjectedCube cabinetProjection = new ProjectedCube(cabinet.project());
        g.setColor(Color.ORANGE);
//        cabinetProjection.paint(g);

        ProjectedCube onePointProjection = new ProjectedCube(onePointPers.project());
        g.setColor(Color.MAGENTA);
//        onePointProjection.paint(g);

        ProjectedCube twoPointProjection = new ProjectedCube(twoPointPers.project());
        g.setColor(Color.BLUE);
        twoPointProjection.paint(g);


    }

    public static void main(String[] args) {
        Task3 s = new Task3();
        s.setVisible(true);
    }
}
