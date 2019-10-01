package Lab1.Comparator;

import java.util.Comparator;

public class TriangComp implements Comparator<Triangle> {
    @Override
    public int compare(Triangle t1, Triangle t2) {
        return (int)(t1.getArea() - t2.getArea());
    }
}
