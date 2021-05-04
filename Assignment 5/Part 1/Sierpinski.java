// name          : Joe Turner
// username      : jeturner
// description   : Accepts one command-line argument n then draws a Sierpinski Triangle with depth n. 
public class Sierpinski // To define recursion, we must first define recursion.
{
    public static void filledTriangle(double x, double y, double s) // method to draw a triangle
    {        
        double[] pX = new double[] {x, x-(0.5*s), x+(0.5*s)}; // calculate new x coordinates and store in array
        double[] pY = new double[] {y, y+Math.sqrt((s*s)-((0.5*s)*(0.5*s))), y+Math.sqrt((s*s)-((0.5*s)*(0.5*s)))}; // calculate new y coordinates and store in array
        StdDraw.filledPolygon(pX, pY); // draw the new triangle
    }
    public static void sierpinski(int n, double x, double y, double s) // recursive method for adding new triangles 
    {
        if (n == 0) return; // stop recursing when the count reaches zero
        filledTriangle(x, y ,s); // call the method to draw a triangle
        sierpinski(n-1, x, y+Math.sqrt((s*s)-((0.5*s)*(0.5*s))), s/2); // add a triangle at the top
        sierpinski(n-1, x-(s/2), y, s/2); // bottom left
        sierpinski(n-1, x+(s/2), y, s/2); // bottom right
    }
    public static void main(String[] args)
    {
        int depth = Integer.parseInt(args[0]); // get the argument for the depth of the Sierpinski Triangle
        double[] pX = new double[] {0, 1, 0.5}; // initial x coordinates stored in an array
        double[] pY = new double[] {0, 0, Math.sqrt(3)/2}; // initial y coordinates stored in an array
        StdDraw.setPenColor(StdDraw.GRAY); // set pen color to gray for drawing the initial triangle
        StdDraw.polygon(pX, pY); // draw the initial triangle
        StdDraw.setPenColor(StdDraw.BLACK); // set pen color to black for the remaining triangles.
        sierpinski(depth, (pX[1]-pX[0])/2, (pY[1]-pY[0])/2, (pX[1]-pX[0])/2); // call the sierpinski method to draw the first inner triangle
    }
}
