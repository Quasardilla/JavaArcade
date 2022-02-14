import java.awt.Color;

public class Brick {
    //Variables for the class's objects (Instance Variables)
    public int x;
    public int y;
    public int width;
    public int height;
    public int dx;
    public int dy;
    public Color color;
    
    public Brick(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = x;
        this.width = width;
        this.height = height;
    }

    public Brick(int x, int y, int width, int height, int dx, int dy, Color color) //Another constructor with optional values
    {
        this.x = x;
        this.y = x;
        this.width = width;
        this.height = height;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    public static void main(String[] args) {
        Brick brick1 = new Brick(5, 5, 10, 20);
        System.out.println(brick1.x);
        Brick brick2 = new Brick(3, 6, 13, 21, 3, 1, Color.BLUE);
        System.out.println(brick2.x);
        Brick brick3 = new Brick(7, 4, 43, 26, 2, 9, Color.BLUE);
        System.out.println(brick3.x);
    }
}
