// name          : Joe Turner
// username      : jeturner
// description   : Does art via recursion 
public class Art // To define recursion, we must first define recursion.
{
    public static void filledCircle(double x, double y, double r) // method to draw a triangle
    {        
        StdDraw.circle(x, y, r); // draw the new triangle
    }
    public static void art(int n, double x, double y, double r) // recursive method for adding new triangles 
    {
        if (n == 0) return;
        filledCircle(x, y ,r/2);
        art(n-1, x, y+(r/2), r/2);    
        art(n-1, x+(r/2), y, r/2);
        art(n-1, x-(r/2), y, r/2);
        art(n-1, x, y-(r/2), r/2);
    }
    public static void main(String[] args)
    {
        int depth = Integer.parseInt(args[0]); // get the argument for the depth of the Sierpinski Triangle
        art(depth, .5, .5, .5);
    }
}
