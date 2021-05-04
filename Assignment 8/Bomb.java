// Name        : Joe Turner
// Username    : jeturner
// Description : Builds bombs. Not a terrorist.

// Import so we can use the java.awt.Color class
import java.awt.*;

public class Bomb
{
    // TBD: instance variables
    double bombX;
    double bombY;
    double bombVel;
    double bombRad;
    int initialStr;
    int currentStr;
    boolean splitter;
    // Constructor, create a new bomb located at the top of the screen
    public Bomb()
    {
        bombX = Math.random();
        bombY = 1.0;
        bombVel = StdRandom.uniform(-0.003, -0.001);
        bombRad = StdRandom.uniform(0.02, 0.05);
        initialStr = StdRandom.uniform(10, 50);
        currentStr = initialStr;
        if (Math.random() < 0.1) splitter = true;
        else splitter = false;
    }

    // Constructor, given another parent Bomb object, create a new bomblet
    // based on the parent bomb. 
    public Bomb(Bomb other, double deltaX)
    {
        this.bombX = deltaX;
        this.bombY = other.bombY;
        this.bombVel = 1.1 * other.bombVel;
        this.bombRad = 0.5 * other.bombRad;
        this.initialStr = other.initialStr;
        this.currentStr = this.initialStr;
        this.splitter = false;       
    }

    // Is this bomb a splitter bomb?
    public boolean isSplitter()
    {
        if (splitter == true) return true;
        else return false;
    }

    // Draw this bomb on the screen in the correct color based on the
    // amount of strength left for this bomb.
    public void draw()
    {
        float red = (float) 1 - currentStr/initialStr;
        float blue = (float) currentStr/initialStr;
        StdDraw.setPenColor(new Color(red, 0, blue));
        StdDraw.filledCircle(bombX, bombY, bombRad);
    }

    // Update the position of the bomb based on its velocity
    public void updatePos()
    {
        bombY += bombVel;
    }

    // Getter method for the x-position
    public double getX()
    {
        return bombX;
    }

    // Getter method for the y-position
    public double getY()
    {
        return bombY;
    }

    // Getter method for the radius
    public double getRadius()
    {
        return bombRad;
    }

    // The laser has attacked this bomb.  Reduce the strength
    // of the bomb by one and return the current amount of
    // strength remaining for this bomb.  
    public int attack()
    {
        currentStr -= 1;
        return currentStr;
    }

    // Given a mouse (x, y) coordinate, decide if that location
    // intersects this bomb.
    public boolean intersects(double mouseX, double mouseY)
    {
        double distance = Math.sqrt( Math.pow((bombX - mouseX), 2) + Math.pow((bombY - mouseY), 2) );
        if (distance <= bombRad) return true;
        else return false;
    }
}
