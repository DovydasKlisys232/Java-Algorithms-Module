
import java.util.Arrays;

class Point2 implements Comparable<Point2> {

    //...definition of members etc....
    private static Point2 ORIGIN = new Point2(0, 0);
    private double x, y;

    Point2(double xx, double yy) {
        x = xx;
        y = yy;
    }

    public double distance(Point2 p) {
        return Math.hypot(x - p.x, y - p.y);
    }

    public int compareTo(Point2 p) {
        //double pDist = p.distance(ORIGIN);
        //double dist = this.distance(ORIGIN);
        if (this.x > p.x) {
            return 1;
        } else if (this.x == p.x) {
            return 0;
        } else {
            return -1;
        }
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        Point2 p[] = {
            new Point2(1, 1),
            new Point2(0.5, 0),
            new Point2(-1, 0),
            new Point2(-0.5, -0.5),
            new Point2(0, -1)
        };

        java.util.Arrays.sort(p);

        System.out.println("After sorting...");
        System.out.println(Arrays.toString(p));
    }

}
