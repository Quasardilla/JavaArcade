package BrickClass;
import java.awt.Color;

public class PongObject extends Brick{

    public PongObject(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }

    public PongObject(int x, int y, int width, int height, Color color) //Another constructor with optional values
    {
        super(x, y, width, height, color);
    }

    public PongObject(int x, int y, int width, int height, Color color, int dx, int dy) //Another constructor with optional values
    {
        super(x, y, width, height, color, dx, dy);
    }

    public PongObject(int x, int y, int width, int height, Color color, int dx, int dy,
     int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax);
    }

    public PongObject(int x, int y, int width, int height, Color color, double dx, double dy,
     int XMin, int XMax, int YMin, int YMax) //Another constructor with all values
    {
        super(x, y, width, height, color, dx, dy, XMin, XMax, YMin, YMax);
    }

   public boolean checkAndReactToCollisionWith(Brick r)
   {
      int xm = x + width/2; //use the center of the moving brick as a reference
      int ym = y + height/2; //use the center of the moving brick as a reference
      
      int side = getSideForIntersection(r, xm, ym); //get the moving brick in relation to the other brick
      
      if(side == 0)      //Is the moving brick above the other brick?
         return checkCollisionTopOfRectangle(r);
      else if(side == 1) //Is the moving brick to the right of the other brick?
         return checkCollisionRightSideOfRectangle(r);
      else if(side == 2) //Is the moving brick below the other brick?
         return checkCollisionBottomOfRectangle(r);
      else if(side == 3) //Is the moving brick to the left of the other brick?
         return checkCollisionLeftSideOfRectangle(r);
      
      return false;
   }
   
   /**Returns the side where a collision would occur if possible
   *    0 = top
   *    1 = right
   *    2 = bottom
   *    3 = left
   */
   private int getSideForIntersection(Brick r, int x1, int y1)
   {
      double slopeMajor = (double) r.height / r.width;         //major diagonal slope
      double slopeMinor = (double) -r.height / r.width;        //minor diagonal slope
      double bMajor = r.y - slopeMajor * r.x;         //major diagonal y-intercept
      double bMinor = r.y - slopeMinor * (r.x + r.width); //minor diagonal y-intercept
      
      boolean aboveMajor = y1 < slopeMajor * x1 + bMajor; //Is the given point above the major diagonal
      boolean aboveMinor = y1 < slopeMinor * x1 + bMinor; //Is the given point above the minor diagonal
      
      if(aboveMajor  && aboveMinor)  return 0; //The point is above the other brick
      if(aboveMajor  && !aboveMinor) return 1; //The point is to the right of the other brick
      if(!aboveMajor && !aboveMinor) return 2; //The point is below the other brick
      if(!aboveMajor && aboveMinor)  return 3; //The point is to the left of the other brick
      
      return -1;   //Should never get here since "not above" is below OR ON a diagonal
   }
   
   private boolean checkCollisionLeftSideOfRectangle(Brick r)
   {
      boolean collision = false;
      
      if(y + height > r.y && y < r.y + r.height) {
         if(x + width > r.x) {
           if (checkIfNotPlayer())
            dx = -dx;
            x = r.x - width;
            if(x <= XMin) {  //don't let the brick get bumped off the panel
               x = XMin;
               r.x = x + width;  //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
            }
            collision = true;
         }
      }
      return collision;
   }
   
   private boolean checkCollisionRightSideOfRectangle(Brick r)
   {
      boolean collision = false;
      
      if(y + height > r.y && y < r.y + r.height) {
      if(x < r.x + r.width) {
           if (checkIfNotPlayer())
            dx = -dx;
            x = r.x + r.width;
            if(x + width >= XMax) {  //don't let the brick get bumped off the panel
               x = XMax - width;
               r.x = x - r.width;       //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
            }
            collision = true;
         }
      }
      return collision;
   }
   
   private boolean checkCollisionBottomOfRectangle(Brick r)
   {
      boolean collision = false;
      
      if(x + width > r.x && x < r.x + r.width) {
         if(y < r.y + r.height) {
           if (checkIfNotPlayer())
            dy = -dy;
            y = r.y + r.height;
            if(y + height >= YMax) { //don't let the brick get bumped off the panel
               y = YMax - height;
               r.y = y - r.height;   //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
            }
            collision = true;
         }
      }
      return collision;
   }
   
   private boolean checkCollisionTopOfRectangle(Brick r)
   {
      boolean collision = false;
      
      if(x + width > r.x && x < r.x + r.width) {
         if(y + height > r.y) {
           if (checkIfNotPlayer())
            dy = -dy;
            y = r.y - height;
            if(y <= YMin) {  //don't let the brick get bumped off the panel
               y = YMin;
               r.y = y + height;  //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
            }
            collision = true;
         }
      }
      return collision;
   }


   public boolean checkAndReactToCollisionWith(PongObject r)
{
   int xm = x + width/2; //use the center of the moving brick as a reference
   int ym = y + height/2; //use the center of the moving brick as a reference
   
   int side = getSideForIntersection(r, xm, ym); //get the moving brick in relation to the other brick
   
   if(side == 0)      //Is the moving brick above the other brick?
      return checkCollisionTopOfRectangle(r);
   else if(side == 1) //Is the moving brick to the right of the other brick?
      return checkCollisionRightSideOfRectangle(r);
   else if(side == 2) //Is the moving brick below the other brick?
      return checkCollisionBottomOfRectangle(r);
   else if(side == 3) //Is the moving brick to the left of the other brick?
      return checkCollisionLeftSideOfRectangle(r);
   
   return false;
}

/**Returns the side where a collision would occur if possible
*    0 = top
*    1 = right
*    2 = bottom
*    3 = left
*/
private int getSideForIntersection(PongObject r, int x1, int y1)
{
   double slopeMajor = (double) r.height / r.width;         //major diagonal slope
   double slopeMinor = (double) -r.height / r.width;        //minor diagonal slope
   double bMajor = r.y - slopeMajor * r.x;         //major diagonal y-intercept
   double bMinor = r.y - slopeMinor * (r.x + r.width); //minor diagonal y-intercept
   
   boolean aboveMajor = y1 < slopeMajor * x1 + bMajor; //Is the given point above the major diagonal
   boolean aboveMinor = y1 < slopeMinor * x1 + bMinor; //Is the given point above the minor diagonal
   
   if(aboveMajor  && aboveMinor)  return 0; //The point is above the other brick
   if(aboveMajor  && !aboveMinor) return 1; //The point is to the right of the other brick
   if(!aboveMajor && !aboveMinor) return 2; //The point is below the other brick
   if(!aboveMajor && aboveMinor)  return 3; //The point is to the left of the other brick
   
   return -1;   //Should never get here since "not above" is below OR ON a diagonal
}

private boolean checkCollisionLeftSideOfRectangle(PongObject r)
{
   boolean collision = false;
   
   if(y + height > r.y && y < r.y + r.height) {
      if(x + width > r.x) {
        if (checkIfNotPlayer())
         dx = -dx;
         x = r.x - width;
         if(x <= XMin) {  //don't let the brick get bumped off the panel
            x = XMin;
            r.x = x + width;  //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
         }
         collision = true;
      }
   }
   return collision;
}

private boolean checkCollisionRightSideOfRectangle(PongObject r)
{
   boolean collision = false;
   
   if(y + height > r.y && y < r.y + r.height) {
   if(x < r.x + r.width) {
        if (checkIfNotPlayer())
         dx = -dx;
         x = r.x + r.width;
         if(x + width >= XMax) {  //don't let the brick get bumped off the panel
            x = XMax - width;
            r.x = x - r.width;       //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
         }
         collision = true;
      }
   }
   return collision;
}

private boolean checkCollisionBottomOfRectangle(PongObject r)
{
   boolean collision = false;
   
   if(x + width > r.x && x < r.x + r.width) {
      if(y < r.y + r.height) {
        if (checkIfNotPlayer())
         dy = -dy;
         y = r.y + r.height;
         if(y + height >= YMax) { //don't let the brick get bumped off the panel
            y = YMax - height;
            r.y = y - r.height;   //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
         }
         collision = true;
      }
   }
   return collision;
}

private boolean checkCollisionTopOfRectangle(PongObject r)
{
   boolean collision = false;
   
   if(x + width > r.x && x < r.x + r.width) {
      if(y + height > r.y) {
        if (checkIfNotPlayer())
         dy = -dy;
         y = r.y - height;
         if(y <= YMin) {  //don't let the brick get bumped off the panel
            y = YMin;
            r.y = y + height;  //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
         }
         collision = true;
      }
   }
   return collision;
}

public void update()
{
        x += dx;
        y += dy;
        if (y > YMax - height || y < YMin)
        dy = -dy;
}

public boolean checkIfNotPlayer()
{
    if (upKey > 0 || downKey > 0 || leftKey > 0 || rightKey > 0)
        return false;
    else
        return true;
}

}
