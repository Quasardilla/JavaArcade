package Recursion;

import java.awt.Point;

public class PolygonArea {
    public static void main(String[] args) {
        System.out.println(triangleArea(new Point(0, 0), new Point(1, 1), new Point(0, 1)));
        System.out.println(polygonArea(new Point[]{new Point(0, 0), new Point(1, 1), new Point(0, 1), new Point(1, 0)}));
    }
    
    public static double triangleArea(Point p1, Point p2, Point p3)
    {
        //HEHEHHEHEHHEHEH KILL ME NOWWOWWW
        return Math.abs(p1.x*p2.y+p2.x*p3.y+p3.x*p1.y-p1.y*p2.x-p2.y*p3.x-p3.y*p1.x)/2.0;
    }

    public static double polygonArea(Point[] points)
    {
        if (points.length <= 2) return 0;
        Point[] newList = new Point[points.length-1];
        newList[0] = points[0];
        for (int i = 2; i < points.length; i++)
        {
            newList[i-1] = points[i];
        }
        return triangleArea(points[0], points[1], points[2])+polygonArea(newList);
    }

}
