package com.romeo.second;

//import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;



public class Task2 extends JFrame {

    public Task2(){
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setSize(720,480);

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

//        Cube3D old = new Cube3D(
//                new Point3D(0 + 100,0+ 100,0+ 100),
//                new Point3D(100+ 100, 0+ 100, 0+ 100),
//                new Point3D(100+ 100, 100+ 100, 0+ 100),
//                new Point3D(0+ 100, 100+ 100,0+ 100),
//
//                new Point3D(100 + 100, 0+ 100, 50+ 100),
//                new Point3D(0+ 100,0+ 100,100+ 100),
//                new Point3D(50+ 100, 0+ 100, 100+ 100),
//                new Point3D(100+ 100, 50+ 100, 100+ 100),
//
//                new Point3D(100+ 100, 100+ 100, 100+ 100),
//                new Point3D(0+ 100, 100+ 100, 100+ 100)
//        );

        Cube3D rotated = cube.rotateAboutArbitraryLine(
                new Point3D(0, 0, 0),
                new Point3D(10, 20, 30),
                Math.toRadians(45));

        Cube3D rotated2 = cube.rotateAboutArbitraryLine(
                new Point3D(250, 50, 30),
                new Point3D(-40, 150, -300),
                Math.toRadians(0));
//        System.out.println(rotated2)

        Cube3D shiftedRotated = rotated.shift(new Point3D(400, 300, 0));
//        System.out.println(shiftedRotated);

//        Cube3D shiftedRotated2 = rotated2.shift(new Point3D(550, 300, 0));
//        System.out.println(shiftedRotated);

        ProjectedCube projectedCubeRotated = new ProjectedCube(cube.project());
        projectedCubeRotated.paint(g);

        ProjectedCube projectedCubeShiftedRotated = new ProjectedCube(shiftedRotated.project());
        g.setColor(Color.BLUE);
        projectedCubeShiftedRotated.paint(g);

//        ProjectedCube projectedCubeShiftedRotated2 = new ProjectedCube(shiftedRotated2.project());
//        g.setColor(Color.RED);
//        projectedCubeShiftedRotated2.paint(g);

//        Cube3D rotated = cube.rotate(3.5, 4, 0);
//        System.out.println(rotated);
//
//        Cube3D rotatedSecond = cube.rotate(-.7, -0.3, 0.3);
//
//        Cube3D shifted = rotated.shift(new Point3D(200, 200, 0));
//        Cube3D shiftedSecond = rotatedSecond.shift(new Point3D(350, 200, 0));
//
//        ProjectedCube projectedCube = new ProjectedCube(shifted.project());
//        projectedCube.paint(g);
//        ProjectedCube projectedSecond = new ProjectedCube(shiftedSecond.project());
//        projectedSecond.paint(g);

    }

    public static void main(String[] args){
        Task2 s = new Task2();
        s.setVisible(true);
    }

}
