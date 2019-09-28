package Lab1.Arr_of_pts;

import java.util.Arrays;

public class One_dim extends  AbstractArrayOfPoints{
    private double[] pts;

   One_dim(int num){
       pts = new double[num * 2];
   }

    public void setPoint(int i, double x, double y){
        pts[i * 2] = x;
        pts[i*2 + 1] = y;
    }

    // Getting X of the i-th point:
    public double getX(int i){
       return pts[i*2];
    }

    // Getting Y of the i-th point:
    public double getY(int i){
       return pts[i*2 + 1];
    }

    // Getting the number of points:
    public int count(){
       return pts.length / 2;
    }

    // Adding a point to the end of an array:
    public void addPoint(double x, double y){
        pts = Arrays.copyOf(pts, pts.length + 2);
        pts[pts.length - 2] = x;
        pts[pts.length - 1] = y;
    }

    // Deleting the last point:
    public void removeLast()
    {
        pts = Arrays.copyOf(pts, pts.length - 2);
    }

    // The sortByY() function can be implemented in the similar way
}
