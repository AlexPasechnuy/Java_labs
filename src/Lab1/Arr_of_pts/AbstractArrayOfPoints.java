package Lab1.Arr_of_pts;

public abstract class AbstractArrayOfPoints {
    // Recording of new point coordinates:
    public abstract void setPoint(int i, double x, double y);

    // Getting X of the i-th point:
    public abstract double getX(int i);

    // Getting Y of the i-th point:
    public abstract double getY(int i);

    // Getting the number of points:
    public abstract int count();

    // Adding a point to the end of an array:
    public abstract void addPoint(double x, double y);

    // Deleting the last point:
    public abstract void removeLast();

    // Sorting by X:
    public void sortByX() {
        boolean mustSort; // repeat while
        // the mustSort is true
        do {
            mustSort = false;
            for (int i = 0; i < count() - 1; i++) {
                if (getX(i) > getX(i + 1)) {
                    // exchange items:
                    double x = getX(i);
                    double y = getY(i);
                    setPoint(i, getX(i + 1), getY(i + 1));
                    setPoint(i + 1, x, y);
                    mustSort = true;
                }
            }
        }
        while (mustSort);
    }

    // The sortByY() function can be implemented in the similar way

    // Getting a string representation:
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < count(); i++) {
            s += "x = " + getX(i) + " \ty = " + getY(i) + "\n";
        }
        return s + "\n";
    }

    // Testing sorting on four points:
    public void test() {
        addPoint(22, 45);
        addPoint(4, 11);
        addPoint(30, 5.5);
        addPoint(-2, 48);
        sortByX();
        System.out.println(this);
        removeLast();
        System.out.println(this);
    }

}
