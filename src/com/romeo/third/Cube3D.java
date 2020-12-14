package com.romeo.third;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.LinkedList;

public class Cube3D {

    private LinkedList<Point3D> points;
    private MatrixMaster master;
    private Matrix4X4 rotationMatrix;

    public Cube3D(LinkedList<Point3D> list) {
        this.points = list;
        master = new MatrixMaster();
        this.rotationMatrix = new Matrix4X4();
    }

    public Cube3D(Point3D... points) {
        this.points = new LinkedList<Point3D>();
        this.points.addAll(Arrays.asList(points));
        master = new MatrixMaster();
    }

    public Cube3D rotate(double angleX, double angleY, double angleZ) {
        Cube3D rotatedX = this.rotateX(angleX);
        Cube3D rotatedY = rotatedX.rotateY(angleY);
        return rotatedY.rotateZ(angleZ);
    }

    public Cube3D shift(Point3D vector) {
        LinkedList<Point3D> updatedList = new LinkedList<>();

        for (Point3D point : points) {
            updatedList.add(master.shift(point, vector));
        }

        return new Cube3D(updatedList);
    }

    public LinkedList<Point2D.Double> project() {
        return projectToXYPlane();
    }

    public LinkedList<Point2D.Double> projectToXYPlane() {
        LinkedList<Point2D.Double> list = new LinkedList<>();

        for (Point3D point3D : this.points) {
            list.add(projectPointZ0(point3D));
        }

        return  list;
    }

    public LinkedList<Point2D.Double> projectToXZPlane() {
        LinkedList<Point2D.Double> list = new LinkedList<>();

        for (Point3D point3D : this.points) {
            list.add(projectPointY0(point3D));
        }

        return  list;
    }

    public LinkedList<Point2D.Double> projectToYZPlane() {
        LinkedList<Point2D.Double> list = new LinkedList<>();

        for (Point3D point3D : this.points) {
            list.add(projectPointX0(point3D));
        }

        return  list;
    }

    public Cube3D rotateAboutArbitraryLine(Point3D p, Point3D pDash, double phi) {
        rotationMatrix = rotateAboutArbitraryAxis(p, pDash, phi);
        LinkedList<Point3D> updatedList = new LinkedList<>();

        for (Point3D point : points) {
            updatedList.add(rotationMatrix.multiplyByVertex(new PointForMatrix(point)));
        }

        return new Cube3D(updatedList);
    }

    public Cube3D cabinet(double phi, double theta) {
        rotationMatrix = cabinetProjection(phi, theta);
        LinkedList<Point3D> updatedList = new LinkedList<>();

        for (Point3D point : points) {
            updatedList.add(rotationMatrix.multiplyByVertex(new PointForMatrix(point)));
        }

        return new Cube3D(updatedList);
    }

    public Cube3D axonometric(double phi, double theta) {
        rotationMatrix = axonometricProjection(phi, theta);
        LinkedList<Point3D> updatedList = new LinkedList<>();

        for (Point3D point : points) {
            updatedList.add(rotationMatrix.multiplyByVertex(new PointForMatrix(point)));
        }

        return new Cube3D(updatedList);
    }

    // one point
    public Cube3D perspective(Point3D point) {
        rotationMatrix = onePointPerspectiveProjection(point);
        LinkedList<Point3D> coordinatesMatrix = new LinkedList<>();

        for (Point3D point3D : points) {
            coordinatesMatrix.add(rotationMatrix.multiplyAndNormalize(new PointForMatrix(point3D)));
        }

        return new Cube3D(coordinatesMatrix);
    }

    private Matrix4X4 axonometricProjection(double phi, double theta) {
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);
        double cosPhi   = Math.cos(phi);
        double sinPhi   = Math.sin(phi);

        Matrix4X4 M = new Matrix4X4(
                new double[] { cosPhi,    sinPhi * sinTheta,  0, 0 },
                new double[] {        0,           cosTheta,  0, 0 },
                new double[] { sinPhi, -(cosPhi) * sinTheta,  0, 0 },
                new double[] {        0,                  0,  0, 1 }
        );

        return M;
    }

    // two point
    public Cube3D perspective(Point3D point1, Point3D point2) {
        rotationMatrix = onePointPerspectiveProjection(point1, point2);
        LinkedList<Point3D> coordinatesMatrix = new LinkedList<>();

        for (Point3D point3D : points) {
            coordinatesMatrix.add(rotationMatrix.multiplyAndNormalize(new PointForMatrix(point3D)));
        }

        return new Cube3D(coordinatesMatrix);
    }

    private Matrix4X4 onePointPerspectiveProjection(Point3D p1, Point3D p2) {
        double x = p1.getX(); double xDash = p2.getX();
        double y = p1.getY(); double yDash = p2.getY();
        double z = p1.getZ(); double zDash = p2.getZ();

        Matrix4X4 M1 = new Matrix4X4(
                new double[] { 1,  0,  0,  1/x },
                new double[] { 0,  1,  0,  1/y },
                new double[] { 0,  0,  1,  0 },
                new double[] { -x,  -y,  0,  1 }
        );

        Matrix4X4 M2 = new Matrix4X4(
                new double[] { 1,  0,  0,  0 },
                new double[] { 0,  1,  0,  0 },
                new double[] { 0,  0,  0,  0 },
                new double[] { x,  y,  0,  1 }
        );

        return M1.multiply(M2);
    }

    private Matrix4X4 onePointPerspectiveProjection(Point3D distance) {
        double x = distance.getX();
        double y = distance.getY();
        double z = distance.getZ();

        Matrix4X4 M1 = new Matrix4X4(
                new double[] { 1,  0,  0,  0 },
                new double[] { 0,  1,  0,  0 },
                new double[] { 0,  0,  0,  1/z },
                new double[] { -x,  -y,  0,  1 }
        );

        Matrix4X4 M2 = new Matrix4X4(
                new double[] { 1,  0,  0,  0 },
                new double[] { 0,  1,  0,  0 },
                new double[] { 0,  0,  0,  0 },
                new double[] { x,  y,  0,  1 }
        );

        return M1.multiply(M2);
    }

    private Matrix4X4 cabinetProjection(double phi, double theta) {
        Matrix4X4 M = new Matrix4X4(
                new double[] { 1,  0,  1 / Math.tan(theta), Math.cos(phi) },
                new double[] { 0,  1,  1 / Math.tan(theta), Math.sin(phi) },
                new double[] { 0,  0,                    0,             0 },
                new double[] { 0,  0,                    0,             1 }
        );

//         free
//        double alpha = Math.cos(Math.PI / 4);
//        double betta = alpha;

//         cabinet
//        double alpha = 0.5 * Math.cos(Math.PI / 4);
//        double betta = alpha;
//
//        Matrix4X4 M = new Matrix4X4(
//                new double[] { 1,  0,  0,  0 },
//                new double[] { 0,  1,  0,  0 },
//                new double[] { -alpha,  -betta,  0,  0 },
//                new double[] { 0,  0,  0,  1 }
//        );

        return M;
    }

    private Matrix4X4 rotateAboutArbitraryAxis(Point3D p, Point3D pDash, double phi) {
        double x = p.getX();
        double y = p.getY();
        double z = p.getZ();

        double a = pDash.getX();
        double b = pDash.getY();
        double c = pDash.getZ();

        double l = Math.sqrt(a * a + b * b + c * c);
        double v = Math.sqrt(b * b + c * c);

        // 1 step, rotation matrix
        Matrix4X4 T = new Matrix4X4(
                new double[] { 1,   0,  0, 0 },
                new double[] { 0,   1,  0, 0 },
                new double[] { 0,   0,  1, 0 },
                new double[] { -x, -y, -z, 1 }
        );

        // 2 step, rotation about x-axis by thetaX
        Matrix4X4 Rx = new Matrix4X4(
                new double[] { 1,      0,   0, 0 },
                new double[] { 0,    c/v, b/v, 0 },
                new double[] { 0, -(b/v), c/v, 0 },
                new double[] { 0,      0,   0, 1 }
        );

        // 3 step, rotation about y-axis by thetaY in clock-wise direction
        Matrix4X4 Ry = new Matrix4X4(
                new double[] { v/l,  0, -(a/l),  0 },
                new double[] {   0,  1,      0,  0 },
                new double[] { a/l,  0,    v/l,  0 },
                new double[] {   0,  0,      0,  1 }
        );

        // 4 rotation about a given angle (phi)
        Matrix4X4 Rz = new Matrix4X4(
                new double[] {  Math.cos(phi),  Math.sin(phi), -(a/l),  0 },
                new double[] { -Math.sin(phi),  Math.cos(phi),      0,  0 },
                new double[] {              0,              0,      1,  0 },
                new double[] {              0,              0,      0,  1 }
        );

        // 5 Ry^(-1)
        Matrix4X4 RyMinus = new Matrix4X4(
                new double[] {    v/l,  0,  a/l,  0 },
                new double[] {      0,  1,    0,  0 },
                new double[] { -(a/l),  0,  v/l,  0 },
                new double[] {      0,  0,    0,  1 }
        );

        // 6 Rx^(-1)
        Matrix4X4 RxMinus = new Matrix4X4(
                new double[] { 1,    0,       0,  0 },
                new double[] { 0,  c/v,  -(b/v),  0 },
                new double[] { 0,  b/v,     c/v,  0 },
                new double[] { 0,    0,       0,  1 }
        );

        // 7 T^(-1)
        Matrix4X4 TMinus = new Matrix4X4(
                new double[] { 1,  0,  0,  0 },
                new double[] { 0,  1,  0,  0 },
                new double[] { 0,  0,  1,  0 },
                new double[] { x,  y,  z,  1 }
        );

        Matrix4X4 finalRotationMatrix = T.multiply(Rx);
        finalRotationMatrix = finalRotationMatrix.multiply(Ry);
        finalRotationMatrix = finalRotationMatrix.multiply(Rz);
        finalRotationMatrix = finalRotationMatrix.multiply(RyMinus);
        finalRotationMatrix = finalRotationMatrix.multiply(RxMinus);
        finalRotationMatrix = finalRotationMatrix.multiply(TMinus);

        return finalRotationMatrix;
    }

//  ----------------------------------------------------------------

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Point3D point : points) {
            result.append(point).append("\n");
        }
        result.append("-------------------------------------------------------\n");

        return new String(result);
    }

    private Point2D.Double projectPointZ0(Point3D point3D) {
        double x = point3D.getX();
        double y = point3D.getY();
        double z = point3D.getZ();

        return new Point2D.Double(x, y);
    }

    private Point2D.Double projectPointY0(Point3D point3D) {
        double x = point3D.getX();
        double y = point3D.getY();
        double z = point3D.getZ();

        return new Point2D.Double(x, z);
    }

    private Point2D.Double projectPointX0(Point3D point3D) {
        double x = point3D.getX();
        double y = point3D.getY();
        double z = point3D.getZ();

        return new Point2D.Double(y, z);
    }

    private Cube3D rotateX(double phi) {
        LinkedList<Point3D> updatedList = new LinkedList<>();

        for (Point3D point : points) {
            updatedList.add(master.multiplyMxBy(point, phi));
        }

        return new Cube3D(updatedList);
    }

    private Cube3D rotateY(double phi) {
        LinkedList<Point3D> updatedList = new LinkedList<>();

        for (Point3D point : points) {
            updatedList.add(master.multiplyMyBy(point, phi));
        }

        return new Cube3D(updatedList);
    }

    private Cube3D rotateZ(double phi) {
        LinkedList<Point3D> updatedList = new LinkedList<>();

        for (Point3D point : points) {
            updatedList.add(master.multiplyMzBy(point, phi));
        }

        return new Cube3D(updatedList);
    }
}
