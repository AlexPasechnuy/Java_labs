package Lab1.Arr_of_pts;

import java.util.Arrays;

public class Two_dim extends AbstractArrayOfPoints {
    double[][] pts;

    Two_dim(int num) {
        pts = new double[num][2];
    }

    public void setPoint(int i, double x, double y) {
        pts[i][0] = x;
        pts[i][1] = y;
    }

    // Getting X of the i-th point:
    public double getX(int i) {
        return pts[i][0];
    }

    // Getting Y of the i-th point:
    public double getY(int i) {
        return pts[i][1];
    }

    // Getting the number of points:
    public int count() {
        return pts.length;
    }

    // Adding a point to the end of an array:
    public void addPoint(double x, double y) {

        //increasing array size and copying values through temporary array
        double[][] temp = new double[pts.length][2];
        for(int i = 0; i < pts.length; i++)
            temp[i] = Arrays.copyOf(pts[i], 2);
        pts = new double[temp.length + 1][2];
        for(int i = 0; i < temp.length; i++)
            pts[i] = Arrays.copyOf(temp[i], 2);

        pts[pts.length - 1][0] = x;
        pts[pts.length - 1][1] = y;
    }

    // Deleting the last point:
    public void removeLast(){
        pts = Arrays.copyOf(pts, pts.length - 1);
    }

    // The sortByY() function can be implemented in the similar way
}
